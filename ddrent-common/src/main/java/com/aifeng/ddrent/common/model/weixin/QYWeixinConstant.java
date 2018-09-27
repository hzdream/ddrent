package com.aifeng.ddrent.common.model.weixin;

/**
 * 
 * @ClassName: WeixinConstant 
 * @Description: 微信接口常量
 * @author: imart·deng
 * @date: 2018年8月22日 上午2:45:39
 */
public class QYWeixinConstant {
	//微信获取token接口
		public final static String GET_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
		
		//微信获取用户信息接口
		public final static String GET_USER_INFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
		
		//微信获取用户详情接口
		public final static String GET_DETAIL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail";
		
		//微信获取用户
		public final static String GET_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/get";
		
		//添加用户
		public final static String ADD_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/create";
		
		//更新用户
		public final static String UPDATE_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/update";
		
		//删除用户
		public final static String DELETE_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/delete";
		
		//批量删除
		public final static String DELETE_BATCH_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/delete";

		//获取部门成员信息
		public final static String SIMPLE_LIST = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist";

		//获取部门成员详情
		public final static String LIST = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist";

		//创建部门
		public final static String DEPART_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/department/create";
		
		//更新部门
		public final static String DEPART_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/department/update";
		
		//删除部门
		public final static String DEPART_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/department/delete";
		
		//获取部门列表
		public final static String DEPART_LIST = "https://qyapi.weixin.qq.com/cgi-bin/department/list";

		//标签创建
		public final static String TAG_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/tag/create";
		
		//标签更新
		public final static String TAG_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/tag/update";
		
		//标签删除
		public final static String TAG_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete";
		
		//标签列表
		public final static String TAG_LIST = "https://qyapi.weixin.qq.com/cgi-bin/tag/list";
		
		//获取标签成员
		public final static String TAG_USER_LIST = "https://qyapi.weixin.qq.com/cgi-bin/tag/get";
		
		//添加标签成员
		public final static String TAG_USER_ADD = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers";
		
		//删除标签成员
		public final static String TAG_USER_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers";

		//邀请加入企业微信
		public final static String INVITE = "https://qyapi.weixin.qq.com/cgi-bin/batch/invite";
		
		//交换ip
		public final static String CONVERT_TO_OPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid";
		
		//发送微信消息
		public final static String SEND_MESSAGE = "https://qyapi.weixin.qq.com/cgi-bin/message/send";
		
		//授权认证
		public final static String AUTH = "https://open.weixin.qq.com/connect/oauth2/authorize";
		
		//获取js ticket
		public final static String GET_JS_TICKET = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket";
		
		//创建群聊
		public final static String APPCHAT_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/appchat/create";
		
		//修改群聊
		public final static String APPCHAT_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/appchat/update";
		
		//拉取群聊
		public final static String APPCHAT_GET = "https://qyapi.weixin.qq.com/cgi-bin/appchat/get";
		
		//应用推送群聊消息
		public final static String APPCHAT_SEND = "https://qyapi.weixin.qq.com/cgi-bin/appchat/send";
		
		//错误代码
		public final static String ERROR_CODE = "errcode";
		
		//微信app id
		public final static String WEIXIN_CORP_ID = "ww538bc0c813445599";
		
		//企业id名
		public final static String PARAM_CORPID = "corpid";
		
		//企业应用密钥
		public final static String PARAM_CORP_SECRET = "corpsecret";
		
		//授权token
		public final static String PARAMS_CORP_ACCESS_TOKEN = "access_token";
		
		//授权ticket
		public final static String PARAMS_CORP_JS_TICKET = "access_token";
		
		
		//分隔符
		public final static String SEPARATOR = "|";
}
