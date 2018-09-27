package com.aifeng.ddrent.core.service.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aifeng.ddrent.common.model.weixin.JsAPIConfig;
import com.aifeng.ddrent.common.model.weixin.QYWeixinConstant;
import com.aifeng.ddrent.common.model.weixin.WeixinMessage;
import com.aifeng.ddrent.common.model.weixin.WeixinResult;
import com.aifeng.ddrent.common.model.weixin.WeixinUser;
import com.aifeng.ddrent.common.util.data.EncryptionUtil;
import com.aifeng.ddrent.common.util.data.RandomUtil;
import com.aifeng.ddrent.common.util.http.HttpProvider;
import com.aifeng.ddrent.common.util.http.impl.HttpProviderImpl;
import com.aifeng.ddrent.core.dao.model.weixin.enterprise.WeixinAppDO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/** 
* @author dengxf E-mail:dengxf@yintong.com.cn
* @date 创建时间：2017年9月17日 下午2:45:39
* @version 1.0
* @since
* 
*/
@Service("weixinService")
public class WeixinService {
	protected static final Logger logger = LoggerFactory.getLogger(WeixinService.class);
	
	// google json util
	public final static Gson gson = new Gson();
	
	/** token缓存 */
	private static Map<String, AppTokenInfo> appTokens = new ConcurrentHashMap<String, AppTokenInfo>(32);
	
	/** js ticket 缓存 */
	private static Map<String, JsTickesInfo> jsTickes = new ConcurrentHashMap<String, JsTickesInfo>(32);
	
//	@Autowired
//	private WeixinAppDOMapper weixinAppMapper;
//	
//	@Autowired
//	private AppService appService;
	
	/**
	 * 应用授权token
	 * @author dengxf
	 *
	 */
	class AppTokenInfo{
		String accessToken;
		Date expiresAt;
		
		public AppTokenInfo(String accessToken, Date expiresAt) {
			super();
			this.accessToken = accessToken;
			this.expiresAt = expiresAt;
		}
	}
	
	/**
	 * js-sdk 授权票据信息
	 * @author dengxf
	 *
	 */
	class JsTickesInfo{
		String ticket;
		Date expiresAt;
		
		public JsTickesInfo(String ticket, Date expiresAt) {
			super();
			this.ticket = ticket;
			this.expiresAt = expiresAt;
		}
	}
	
	/**
	 * 获取微信access_token(一般有效期为7200秒)
	 * <br/>[GET]
	 * https://qyapi.weixin.qq.com/cgi-bin/gettoken
	 * @param agentId 应用编号，微信分配，在ids端管理
	 * @return String access_token 应用授权token <br/> <strong>如果没有找到agentId对应的应用信息，则返回null</strong>
	 */
	public String getAppToken(String agentId) {
		String accessToken = null;
		if(StringUtils.isNotEmpty(agentId)) {
			AppTokenInfo appToken = appTokens.get(agentId);
			if(null == appToken) {
				appToken = updateAppToken(agentId, false);
				
				//更新成功，返回token
				if(null != appToken) {
					accessToken = appToken.accessToken;
				}
			}else {
				Date now = new Date();
				Date expiresAt = appToken.expiresAt;
				
				if(now.before(expiresAt)) {
					//未过期，直接返回
					return appToken.accessToken;
				}else {
					appToken = updateAppToken(agentId, true);
					
					//更新成功，返回token
					if(null != appToken) {
						accessToken = appToken.accessToken;
					}
				}
				
			}
		}
		
		return accessToken;
	}
	
	/**
	 * 更新应用token
	 * @param agentId	应用编号
	 * @param refresh	是否强制刷新
	 * @return
	 */
	public synchronized AppTokenInfo updateAppToken(String agentId, boolean refresh) {
		
		if(!refresh) {
			AppTokenInfo appToken = appTokens.get(agentId);
			if(null != appToken) {
				Date now = new Date();
				Date expireAt = appToken.expiresAt;
				
				//没有失效则直接返回，否则重新获取
				if(now.before(expireAt)) {
					return appToken;
				}
				
			}
		}
		
		WeixinAppDO weixinApp = getWeixinAppById(agentId);
		
//		测试专用
//		WeixinAppDO weixinApp = new WeixinAppDO();
//		weixinApp.setSecret("MjyoEgoeTmtoWRWsi21GC-ICbkG6f1tisQ0xAacXGb4");
//		weixinApp.setAgentId(agentId);
		
		if(null != weixinApp) {
			Map<String, Object> params = new HashMap<>();
			HttpProvider httpProvider = HttpProviderImpl.getInstance();
			params.put(QYWeixinConstant.PARAM_CORPID, QYWeixinConstant.WEIXIN_CORP_ID);
			params.put(QYWeixinConstant.PARAM_CORP_SECRET, weixinApp.getSecret());
			String json = null;
			
			try {
				json = httpProvider.sendGet(QYWeixinConstant.GET_TOKEN, params);
				logger.info("获取token的json数据信息为："  + json);
				
				Type type = new TypeToken<Map<String, String>>(){}.getType();
				Map<String, String> results = gson.fromJson(json, type);
				WeixinResult result = new WeixinResult();
				result.setErrcode(results.get(WeixinResult.ERROR_CODE));
				result.setErrmsg(results.get(WeixinResult.ERROR_MSG));
				
				if(result.isSuccess()) {
					//获得失效时间
					int expire = Integer.valueOf((String) results.get("expires_in"));
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.SECOND, (int) expire);
					
					AppTokenInfo appTokenInfo = new AppTokenInfo(results.get(QYWeixinConstant.PARAMS_CORP_ACCESS_TOKEN), cal.getTime());
					
					//更新token
					appTokens.put(weixinApp.getAgentId(), appTokenInfo);
					
					return appTokenInfo;
				}else {
					throw new Exception("获取企业微信授权token失败：" + json);
				}
				
			} catch (Exception e) {
				logger.error("[WeixinServiceImpl.updateToken] 获取["
						+ weixinApp.toString()
						+ "] 的access_token信息时发生一个错误 :", e);
				try {
					throw new Exception("[WeixinServiceImpl.updateToken] 获取["
							+ weixinApp.toString()
							+ "] 的access_token信息时发生一个错误 :", e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		}
		return null;
		
	}

	/**
	 * 根据应用id，获取企业微信应用配置信息
	 * @param agentId
	 * @return
	 */
	public WeixinAppDO getWeixinAppById(String agentId) {
		if(StringUtils.isNotEmpty(agentId)) {
//			return weixinAppMapper.selectByPrimaryKey(agentId);
		}
		return null;
	}
	
	/**
	 * 根据应用秘钥获取应用信息
	 * @param corpsecret
	 * @return
	 */
	public WeixinAppDO getWeixinAppByCorpSecret(String corpsecret) {
		if(StringUtils.isNotEmpty(corpsecret)) {
//			Example example = new Example(WeixinAppDO.class);
//			example.createCriteria().andEqualTo("secret", corpsecret);
//			List<WeixinAppDO> lis = weixinAppMapper.selectByExample(example);
//			
//			if(null != lis && lis.size() > 0) {
//				return lis.get(0);
//			}
		}
		return null;
	}
	
	/**
	 * 根据应用秘钥获取应用信息
	 * @param appCode	应用编码
	 * @return
	 */
	public WeixinAppDO getWeixinAppByAppCode(String appCode) {
		if(StringUtils.isNotEmpty(appCode)) {
			
//			AppDO app = appService.getByCode(appCode);
//			
//			if(null != app) {
//				String corpSecret = app.getAppPrivateKey();
//				Example example = new Example(WeixinAppDO.class);
//				example.createCriteria().andEqualTo("secret", corpSecret);
//				List<WeixinAppDO> lis = weixinAppMapper.selectByExample(example);
//				
//				if(null != lis && lis.size() > 0) {
//					return lis.get(0);
//				}
//			}
			
		}
		return null;
	}
	
	/**
	 * 发送企业微信api get请求
	 * @param url		请求api地址
	 * @param agentId	当前应用id
	 * @param params	请求参数
	 * @return
	 */
	private WeixinResult doGet(String url, String agentId, Map<String, String> params) {
		
		//获取 应用token
		String accessToken = getAppToken(agentId);
		
		if(StringUtils.isNotEmpty(accessToken)) {
			String tokenUrl = url + "?" + QYWeixinConstant.PARAMS_CORP_ACCESS_TOKEN + "=" + accessToken;
			for (Entry<String, String> entry : params.entrySet()) {
				tokenUrl += ("&" + entry.getKey() + "=" + entry.getValue());
			}
			
			try {
				WeixinResult result = get(tokenUrl);
				
				if(result.isTokenInvalid()){
					//刷新token重新请求
					AppTokenInfo appToken = updateAppToken(agentId, true);
					accessToken = appToken.accessToken;
					tokenUrl = url + "?" + QYWeixinConstant.PARAMS_CORP_ACCESS_TOKEN + "=" + accessToken;
					for (Entry<String, String> entry : params.entrySet()) {
						tokenUrl += ("&" + entry.getKey() + "=" + entry.getValue());
					}
					
					return get(tokenUrl);
				}
				
				return result;
			} catch (Exception e) {
				logger.error("WeixinServiceImpl.doGet() with error -- {}", e);
			}
			return new WeixinResult(WeixinResult.NO_ACCESS_TOKEN);
		}else {
			return new WeixinResult(WeixinResult.NO_ACCESS_TOKEN);
		}
		
	}
	
	/**
	 * 发送get请求
	 * @param getUrl
	 * @return
	 * @throws Exception
	 */
	private WeixinResult get(String getUrl) throws Exception {
		String json = HttpProviderImpl.getInstance().sendGet(getUrl);
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> res = gson.fromJson(json, type);
		WeixinResult result = new WeixinResult();
		
		Double errocCode = (Double) res.get(WeixinResult.ERROR_CODE);
		
		result.setErrcode(errocCode.intValue() + "");
		result.setErrmsg(res.get(WeixinResult.ERROR_MSG).toString());
		result.setRes(res);
		return result;
	}
	
	/**
	 * 发送企业微信 post api
	 * @param url
	 * @param agentId
	 * @param params
	 * @return
	 */
	private WeixinResult doPost(String url, String agentId, Object params) {
		
		//获取 应用token
		String accessToken = getAppToken(agentId);
		
		if(StringUtils.isNotEmpty(accessToken)) {
			String tokenUrl = url + "?" + QYWeixinConstant.PARAMS_CORP_ACCESS_TOKEN + "=" + accessToken;
			
			try {
				WeixinResult result = post(tokenUrl, params);
				
				if(result.isTokenInvalid()){
					//刷新token重新请求
					AppTokenInfo appToken = updateAppToken(agentId, true);
					accessToken = appToken.accessToken;
					tokenUrl = url + "?" + QYWeixinConstant.PARAMS_CORP_ACCESS_TOKEN + "=" + accessToken;
					
					return post(tokenUrl, params);
				}
				
				return result;
				
			} catch (Exception e) {
				logger.error("WeixinServiceImpl.doGet() with error -- {}", e);
			}
			return new WeixinResult(WeixinResult.NO_ACCESS_TOKEN);
		}else {
			return new WeixinResult(WeixinResult.NO_ACCESS_TOKEN);
		}
		
	}
	
	/**
	 * 发送post请求
	 * @param tokenUrl
	 * @param params
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	private static WeixinResult post(String tokenUrl, Object params) throws UnsupportedEncodingException, IOException {
		
		String json = HttpProviderImpl.getInstance().sendPost(tokenUrl, gson.toJson(params).getBytes("UTF-8"));
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> res = gson.fromJson(json, type);
		WeixinResult result = new WeixinResult();
		Double errocCode = (Double) res.get(WeixinResult.ERROR_CODE);
		
		result.setErrcode(errocCode.intValue() + "");
//		result.setErrcode(res.get(WeixinResult.ERROR_CODE).toString());
		result.setErrmsg(res.get(WeixinResult.ERROR_MSG).toString());
		result.setRes(res);
		return result;
	}
	
	/**
	 * 验证成员信息，用当前登陆成员的code去交换成员信息。
	 * <br/>[GET]
	 * https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo
	 * @param agentId 应用编号，微信分配，在ids端管理
	 * @param code 通过成员授权获取到的code，最大为512字节。
	 * @return 用户基本信息
	 */
	public WeixinResult getUserInfoByCode(String agentId, String code) {
		
		if(StringUtils.isNotEmpty(agentId) && StringUtils.isNotEmpty(code)) {
			Map<String, String> params = new HashMap<>(2);
			params.put("code", code);
			return doGet(QYWeixinConstant.GET_USER_INFO, agentId, params);
		}
		
		return null;
	}

	/**
	 * 根据user_ticket 获取用户详情。
	 * <br/>POST
	 * https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail
	 * @param userTicket
	 * @param accessToken
	 * @return
	 */
	public WeixinResult getWeixinUserDetailByTicket(String userTicket, String agentId) {
		if(StringUtils.isNotEmpty(agentId) && StringUtils.isNotEmpty(userTicket)) {
			Map<String, Object> params = new HashMap<>();
			params.put("user_ticket", userTicket);
			return doPost(QYWeixinConstant.GET_DETAIL, agentId, params);
		}
		return null;
	}

	/**
	 * 获取微信用户基本信息
	 * <br/>[GET]
	 * https://qyapi.weixin.qq.com/cgi-bin/user/get
	 * @param code 通过成员授权获取到的code，最大为512字节。
	 * @param agentId 应用编号，微信分配，在ids端管理
	 * @return	微信用户基本信息
	 */
	public WeixinResult getWeixinUserById(String userid, String agentId) {
		
		if(StringUtils.isNotEmpty(userid) && StringUtils.isNotEmpty(agentId)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("userid", userid);
			
			return doGet(QYWeixinConstant.GET_USER, agentId, params);
			
		}
		return null;
	}
	
	/**============= 微信用户管理  start ========*/
	
	//默认通讯录id为1000000
	public final static String CONTACTS_ID = "1000000";
	/**
	 * 添加成员信息
	 * <br/>POST
	 * https://qyapi.weixin.qq.com/cgi-bin/user/create
	 * @param userInfo 职员信息
	 * @return
	 */
	public WeixinResult addWeixinUser(WeixinUser userInfo) {
		if(StringUtils.isNotEmpty(userInfo.getUserid()) && StringUtils.isNotEmpty(userInfo.getName()) && null != userInfo.getDepartment()) {
			return doPost(QYWeixinConstant.ADD_USER, CONTACTS_ID, userInfo);
		}
		return null;
	}

	/**
	 * 更新成员信息 只能更新基础信息，无法更新手机号和userid
	 * <br/>POST
	 * https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN
	 * @param userInfo 微信用户信息
	 * @return
	 */
	public WeixinResult updateWeixinUser(WeixinUser weixinInfo) {
		if(null != weixinInfo.getUserid() && null != weixinInfo.getName() && null != weixinInfo.getDepartment()) {
			return doPost(QYWeixinConstant.UPDATE_USER, CONTACTS_ID, weixinInfo);
		}
		return null;
	}

	/**
	 * 删除用户信息<br/>
	 * https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID
	 * @param userid 用户id
	 * @param access_token 调用接口凭证
	 * @return
	 */
	public WeixinResult deleteWeixinUser(String userid) {
		if(StringUtils.isNotEmpty(userid)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("userid", userid);
			
			return doGet(QYWeixinConstant.DELETE_USER, CONTACTS_ID, params);
		}
		return null;
	}

	/**
	 * 批量删除用户
	 * <br/>POST
	 * https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN
	 * @param useridlist 用户id列表，eg："useridlist": ["zhangsan", "lisi"]
	 * @return
	 */
	public WeixinResult deleteBatch(List<String> useridlist) {
		if(null != useridlist && useridlist.size() > 0) {
			Map<String, Object> params = new HashMap<>();
			params.put("useridlist", useridlist);
			doPost(QYWeixinConstant.DELETE_BATCH_USER, CONTACTS_ID, params);
		}
		return null;
	}
	
	/**
	 * 获取部门成员列表信息
	 * <br/>[GET]
	 * https://qyapi.weixin.qq.com/cgi-bin/user/simplelist
	 * @param departId
	 * @param fetchChild
	 * @return
	 */
	public WeixinResult getDepartSimpleList(String departId, boolean fetchChild) {
		if(StringUtils.isNotEmpty(departId)) {
			Map<String, String> params = new HashMap<>(2);
			params.put("department_id", departId);
			if(fetchChild) {
				params.put("fetch_child", "1");
			}
			
			return doGet(QYWeixinConstant.SIMPLE_LIST, CONTACTS_ID, params);
		}
		return null;
	}
	
	/**
	 * 获取部门成员详情信息
	 * <br/>[GET]
	 * https://qyapi.weixin.qq.com/cgi-bin/user/simplelist
	 * @param departId
	 * @param fetchChild
	 * @return
	 */
	public WeixinResult getDepartList(String departId, boolean fetchChild) {
		if(StringUtils.isNotEmpty(departId)) {
			Map<String, String> params = new HashMap<>(2);
			params.put("department_id", departId);
			if(fetchChild) {
				params.put("fetch_child", "1");
			}
			return doGet(QYWeixinConstant.LIST, CONTACTS_ID, params);
		}
		return null;
	}

	/**
	 * 获取用户openid
	 * <br/>[POST]
	 * https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid
	 * @param userid 用户id
	 * @param agentid 企业内的成员id
	 * @param access_token 调用接口凭证
	 * @return
	 */
	public WeixinResult getOpenId(String userid, String agentid) {
		
		if(StringUtils.isNotEmpty(userid) && StringUtils.isNotEmpty(agentid)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userid", userid);
			
			return doPost(QYWeixinConstant.CONVERT_TO_OPENID, agentid, params);
		}
		
		return null;
	}
	
	/**
	 * 发送用户通知
	 * @param weixinMessage
	 * @param accessToken
	 * @param agentId
	 * @return
	 * @throws IOException
	 */
	public WeixinResult sendMessage(WeixinMessage weixinMessage, String agentId) {
		
		if(null != weixinMessage && StringUtils.isNotEmpty(agentId)) {
			weixinMessage.setAgentid(agentId);
			doPost(QYWeixinConstant.SEND_MESSAGE, agentId, weixinMessage);
		}
		
		return null;
	}
	
	/**
	 * 获取js api 签名
	 * @param url
	 * @param corpsecret 应用密钥
	 * @return
	 */
	public JsAPIConfig getJsAPIConfig(String url, String agentId) {
		if(StringUtils.isNotEmpty(agentId) && StringUtils.isNotEmpty(url)) {
			
			//获取js 授权票据
			String ticket = getJsTicket(agentId, false);
			
			//生成js签名配置
			JsAPIConfig config =new JsAPIConfig();
			String noncestr = RandomUtil.getRandom(16);
			Long timestamp = (System.currentTimeMillis()/1000);
			config.setNoncestr(noncestr);
			config.setTicket(ticket);
			config.setTimestamp(timestamp);
			config.setUrl(url);
			if(null != url && null != ticket) {
				config.setSignature(EncryptionUtil.sha1(config.getParamsStr()));
			}
			return config;
		}
		
		return null;
	}

	/**
	 * 获取jsticket
	 * @param corpsecret 应用密钥
	 * @param refresh
	 * @return
	 */
	private String getJsTicket(String agentId, boolean refresh) {
		String ticket = null;
		if(StringUtils.isNotEmpty(agentId)) {
			JsTickesInfo jsTicketInfo = jsTickes.get(agentId);
			
			if(null == jsTicketInfo) {
				jsTicketInfo = updateJSticket(agentId, false);
				
				//更新成功，返回token
				if(null != jsTicketInfo) {
					ticket = jsTicketInfo.ticket;
				}
			}else {
				Date now = new Date();
				Date expiresAt = jsTicketInfo.expiresAt;
				
				if(now.before(expiresAt)) {
					//未过期，直接返回
					return jsTicketInfo.ticket;
				}else {
					jsTicketInfo = updateJSticket(agentId, true);
					
					//更新成功，返回token
					if(null != jsTicketInfo) {
						ticket = jsTicketInfo.ticket;
					}
				}
				
			}
			
		}
		
		return ticket;
	}
	
	/**
	 * 获取票据信息
	 * @param agentId	应用id
	 * @param refresh	是否强制刷新
	 * @return
	 */
	private synchronized JsTickesInfo updateJSticket(String agentId, boolean refresh) {
		if(!refresh) {
			JsTickesInfo jsTicketInfo = jsTickes.get(agentId);
			if(null != jsTicketInfo) {
				Date now = new Date();
				Date expireAt = jsTicketInfo.expiresAt;
				
				//没有失效则直接返回，否则重新获取
				if(now.before(expireAt)) {
					return jsTicketInfo;
				}
				
			}
		}
		
		WeixinAppDO weixinApp = getWeixinAppById(agentId);
		
		if(null != weixinApp) {
			Map<String, Object> params = new HashMap<>();
			HttpProvider httpProvider = HttpProviderImpl.getInstance();
			params.put(QYWeixinConstant.PARAM_CORPID, QYWeixinConstant.WEIXIN_CORP_ID);
			params.put(QYWeixinConstant.PARAM_CORP_SECRET, weixinApp.getSecret());
			String json = null;
			
			try {
				//获取js ticket
				json = httpProvider.sendGet(QYWeixinConstant.GET_JS_TICKET, params);
				logger.info("获取token的json数据信息为："  + json);
				Type type = new TypeToken<Map<String, String>>(){}.getType();
				Map<String, String> res = gson.fromJson(json, type);
				WeixinResult result = new WeixinResult();
				result.setErrcode(res.get(WeixinResult.ERROR_CODE).toString());
				result.setErrmsg(res.get(WeixinResult.ERROR_MSG).toString());
				
				if(result.isSuccess()) {
					//获得失效时间
					int expire = Integer.valueOf((String) res.get("expires_in"));
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.SECOND, (int) expire);
					JsTickesInfo jsTicketInfo = new JsTickesInfo(res.get(QYWeixinConstant.PARAMS_CORP_JS_TICKET).toString(), cal.getTime());
					
					//更新ticket
					jsTickes.put(weixinApp.getAgentId(), jsTicketInfo);
					
					return jsTicketInfo;
				}else {
					throw new Exception("获取企业微信授权token失败：" + json);
				}
				
			} catch (Exception e) {
				logger.error("[WeixinServiceImpl.updateJSticket] 获取["
						+ weixinApp.toString()
						+ "] 的js_ticket信息时发生一个错误 :", e);
				try {
					throw new Exception("[WeixinServiceImpl.updateJSticket] 获取["
							+ weixinApp.toString()
							+ "] 的js_ticket信息时发生一个错误 :", e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		}
		return null;
	}
	
	/**
	 * 创建应用群聊消息
	 * @param agentId
	 * @param owner
	 * @param name
	 * @param users
	 * @return
	 */
	public WeixinResult createAppChat(String agentId, String owner, String name, List<String> users) {
		
		if(StringUtils.isNotEmpty(owner) && StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(agentId)) {
			if(null != users && users.size() > 1) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("name", name);
				params.put("owner", owner);
				params.put("userlist", users);
				
				doPost(QYWeixinConstant.APPCHAT_CREATE, agentId, params);
			}
		}
		
		return null;
	}
	
	/**
	 * 修改群聊信息
	 * @param agentId
	 * @param owner
	 * @param name
	 * @param users
	 * @return
	 */
	public WeixinResult updateAppChat(String chatid, String agentId, String owner, String name, List<String> addUsers, List<String> deleteUsers) {
		
		if(StringUtils.isNotEmpty(chatid) && StringUtils.isNotEmpty(owner) 
				&& StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(agentId)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("chatid", chatid);
			params.put("name", name);
			params.put("owner", owner);
			params.put("add_user_list", addUsers);
			params.put("del_user_list", deleteUsers);
			
			doPost(QYWeixinConstant.APPCHAT_UPDATE, agentId, params);
		}
		
		return null;
	}
	
	/**
	 * 获取聊天信息
	 * @param chatid
	 * @param agentId
	 * @return
	 */
	public WeixinResult getAppChat(String chatid, String agentId) {
		
		if(StringUtils.isNotEmpty(chatid) && StringUtils.isNotEmpty(agentId)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("chatid", chatid);
			
			doGet(QYWeixinConstant.APPCHAT_GET, agentId, params);
		}
		
		return null;
	}
	
	/**
	 * 发送群聊消息
	 * @param weixinMessage
	 * @param chatid
	 * @param agentId
	 * @return
	 */
	public WeixinResult sendAppChatMsg(WeixinMessage weixinMessage, String chatid, String agentId) {
		
		if(null != weixinMessage && StringUtils.isNotEmpty(agentId) && StringUtils.isNotEmpty(chatid)) {
			weixinMessage.setChatid(chatid);
			doPost(QYWeixinConstant.APPCHAT_SEND, agentId, weixinMessage);
		}
		
		return null;
	}

	public static void main(String[] args) {
		
		String agentId = "1000024";
		
		//群聊消息
//		String owner = "dengxf";
//		String name = "接口调用群聊测试";
//		String chatid = "14224337948494033826";
//		List<String> users = new ArrayList<String>(Arrays.asList("kongwq", "cuimq", "dengxf", "chensy", "chenjiang"));
		
		WeixinService weixinService = new WeixinService();
		
//		weixinService.createAppChat(agentId, owner, name, users);
		WeixinMessage.Articles articles = new WeixinMessage.Articles(
				"通知", 
				"第二届全国支付机构反洗钱知识大赛开始了，拿现金大奖不拼运气只拼实力！", 
				"http://paynews.net:8083/FXQGametTwo/mobile/index.html", 
				"进入详情", 
				"https://aifeng.aifeng.com/assets/pages/img/weixinNotice/fxq.png");
//		WeixinMessage.Articles articles = new WeixinMessage.Articles("通知", "合规理念，请大家点击详情后勾选承诺选项并点击确认提交。", 
//				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww538bc0c813445599&redirect_uri=https:"
//						+ "//aifeng.aifeng.com/weixin/res/poster-hg&response_type=code&scope=snsapi_base&state=qingji"
//						+ "a#wechat_redirect", "阅读全文", "https://aifeng.aifeng.com/assets/pages/img/weixinNotice/hglnhead.jpg");
//		WeixinMessage.Articles articles = new WeixinMessage.Articles("端午节到了", "端午传情，尽情放纵。", "aifeng.aifeng.com", "阅读全文", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526554140679&di=ff9cf59e02a2f30288984420fcf2619b&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fe61190ef76c6a7ef493a670bf6faaf51f3de66fb.jpg");
		WeixinMessage message = new WeixinMessage(agentId, articles);
		message.setTouser("duyh");
		
//		WeixinMessage message = new WeixinMessage(agentId, "变更群主");
//		message.setTouser("dengxf");
		
//		weixinService.sendAppChatMsg(message, "14224337948494033826", agentId);
//		
//		weixinService.updateAppChat(chatid, agentId, owner, name, users, null);
//		
		weixinService.sendMessage(message, agentId);
//		
//		//刷新token
//		String token = getAppToken(agentId);
//		
//		System.out.println(token);
////		
////		//发送message消息
////		WeixinMessage message = new WeixinMessage(agentId, "你好邓雪锋");
////		message.setTouser("dengxf");
////		
////		try {
////			sendMessage(message, agentId);
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		WeixinMessage.Articles articles = new WeixinMessage.Articles("端午节到了", "端午传情，尽情放纵。", "aifeng.aifeng.com", "阅读全文", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526554140679&di=ff9cf59e02a2f30288984420fcf2619b&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fe61190ef76c6a7ef493a670bf6faaf51f3de66fb.jpg");
//		WeixinMessage message = new WeixinMessage(agentId, articles);
//		message.setTouser(touser);
//		
//		try {
//			sendMessage(message, agentId);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
	}
}
