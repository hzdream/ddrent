/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.core.service.captcha 
 * @author imart·deng
 * @date 创建时间：2018年9月25日 下午8:19:45
 * @version 1.0
 */
package com.aifeng.ddrent.core.service.captcha;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.aifeng.ddrent.common.enums.captcha.CaptchaEnum;
import com.aifeng.ddrent.common.util.data.GsonUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aifeng.ddrent.api.sms.ShortMessageService;
import com.aifeng.ddrent.common.enums.system.ErrorCodeEnum;
import com.aifeng.ddrent.common.exception.buiness.BusinessException;
import com.aifeng.ddrent.common.model.response.BaseResult;
import com.aifeng.ddrent.common.util.data.RandomUtil;
import com.aifeng.ddrent.common.util.date.DateUtil;
import com.aifeng.ddrent.common.util.system.StringUtils;
import com.aifeng.ddrent.core.dao.mapper.captcha.CaptchaMapper;
import com.aifeng.ddrent.core.dao.model.captcha.CaptchaDO;
import com.aifeng.ddrent.core.service.BaseService;

import tk.mybatis.mapper.entity.Example;

/** 
 * @ClassName: CaptchaService 
 * @Description: 验证码服务
 * @author: imart·deng
 * @date: 2018年9月25日 下午8:19:45  
 */
@Service
public class CaptchaService extends BaseService<CaptchaDO, CaptchaMapper> {
	
	//默认失效次数 5 次
	static final int DEFAULT_CHECK_TIMES = 5;
	
	//默认有效时间为创建后的10分钟
	static final int DEFAULT_VALID_TIME = 10;
	
	//默认验证码长度
	static final int DEFAULT_CAPTCHA_LENGTH = 4;
	
	//默认禁止时间 (分钟)
	static final int DEFAULT_FORBIDDEN_MINUTES = 30;
	
	@Autowired
	private ShortMessageService shortMessageService;
	
	/**
	 * 发送短信验证码, to、busiType 必填
	 * @param record		验证码对象
	 * @param format		短信format格式，例如:您正在注册xxxx,您的短信验证码为[{}]
	 * @return
	 */
	@Transactional
	public BaseResult<CaptchaDO> sendSmsCaptcha(CaptchaDO record, String format) {
		BaseResult<CaptchaDO> result = new BaseResult<>();
		try {
			//校验短信是否有效
			validateSmsSend(record);
			
			//查询当前用户是否被禁用
			Example example = new Example(CaptchaDO.class);
			example.createCriteria().andEqualTo("to", record.getTo());
			example.orderBy("updateTime").desc();
			List<CaptchaDO> captchaLis = mapper.selectByExampleAndRowBounds(example, new RowBounds(0, 1));
			if(null != captchaLis && !captchaLis.isEmpty()) {
				CaptchaDO captcha = captchaLis.get(0);
				if(captcha.getTimesCheck() <= 0) {
					Date updateTime = captcha.getUpdateTime();
					Calendar cal = Calendar.getInstance();
					Date now = cal.getTime();
					cal.setTime(updateTime);
					// 禁止时间
					cal.add(Calendar.MINUTE, captcha.getDelayMinutes());
					if(now.before(cal.getTime())) {
						result.setCode(ErrorCodeEnum.CAPTCHA_FOBIDDEN, "您被禁止发送验证码到"+ DateUtil.format(cal));
						return result;
					}
				}
			}

			// 设置默认值
			setRecordDefaultValue(record);

			// 失效所有同手机号，同类型的 有效验证码
			invalidSameCaptcha(record.getTo(), record.getBusiType());

			//captcha 入库
			addSelective(record);
			
			//发送短信验证码
			shortMessageService.sendSms(record.getTo(), System.out.format(format, record.getCaptcha()).toString());
			
			//设置请求成功
			result.setCode(ErrorCodeEnum.SUCCESS);
		} catch (BusinessException e) {
			logger.error("[发送短信验证码]服务失败， 失败原因编号:{}， 失败原因:{}", e.getCode().code(), e.getMessage());
			result.setCode(e.getCode());
		} catch(RuntimeException e) {
			logger.error("[发送短信验证码]服务失败， 失败原因:{}", e.getMessage());
			throw e;
		}
		
		return result;
	}

	/**
	 * 添加验证码
	 * @param record	需要添加的记录
	 * @return	CaptchaDO 验证码记录
	 */
	@Override
	public CaptchaDO add(CaptchaDO record) {

		try {
			// 参数校验
			validateCaptcha(record);

			// 设置默认值
			setRecordDefaultValue(record);

			// 失效所有同手机号，同类型的 有效验证码
			invalidSameCaptcha(record.getTo(), record.getBusiType());

			//captcha 入库
			addSelective(record);
			return record;
		} catch(Exception e) {
			logger.error("[添加校验码] 失败, 请求参数{}, 失败原因{}", GsonUtil.gson().toJson(record), e.getMessage());
			throw e;
		}
	}

	/**
	 * 校验验证码，每校验一次，可校验次数减1
	 * @param id		必填
	 * @param captcha	必填
	 * @param captchaTypes 选填，标志校验码类型
	 * @return 
	 */
	public BaseResult<CaptchaDO> captchaCheck(Long id, String captcha, CaptchaEnum... captchaTypes){
		BaseResult<CaptchaDO> result = new BaseResult<>();
		
		if(null != id && StringUtils.isNotBlank(captcha)) {
			
			CaptchaDO record = getById(id);
			
			if(null != record) {

				// 匹配校验码业务类型
				if(captchaTypes.length > 0){
					boolean match = false;
					for (CaptchaEnum captchaType: captchaTypes) {
						if(captchaType.name().equals(record.getBusiType())) {
							match =true;
							break;
						}
					}
					if(!match) return result.setCode(ErrorCodeEnum.CAPTCHA_NOT_EXIST);
				}

				Date now = new Date();
				Date invalidTime = record.getInvalidTime();
				if(record.getIsActive() && now.before(invalidTime)) {

					if(record.getCaptcha().equals(captcha)) {
						//校验成功
						result.setCode(ErrorCodeEnum.SUCCESS);
						record.setIsActive(Boolean.FALSE);
					}else {
						//减少一次校验次数
						record.setTimesCheck(record.getTimesCheck() - 1);
					}
					updateById(record);
				}else {
					if(!now.before(invalidTime)){
						// 如果校验码超时置失效
						record.setIsActive(Boolean.FALSE);
						updateById(record);
					}
					result.setCode(ErrorCodeEnum.CAPTCHA_INVALID);
				}
			}else {
				result.setCode(ErrorCodeEnum.CAPTCHA_NOT_EXIST);
			}
		}else {
			result.setCode(ErrorCodeEnum.PARAMS_ERROR);
		}
		
		return result;
	}

	/**
	 * 验证手机验证码请求是否有效
	 * @param record
	 * @return
	 */
	private void validateSmsSend(CaptchaDO record) {
		
		if(null == record)
			throw new BusinessException(ErrorCodeEnum.CAPTCHA_MOBILE_INVALID);
		
		if(null == record.getTo() 
				//验证是否是正确的手机号码
				|| !record.getTo().matches("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$"))
			throw new BusinessException(ErrorCodeEnum.CAPTCHA_MOBILE_INVALID);
		
		if(StringUtils.isBlank(record.getBusiType()))
			throw new BusinessException(ErrorCodeEnum.CAPTCHA_BUSITYPE_INVALID);
		
	}

	/**
	 * 校验验证码是否有效
	 * @param record
	 */
	private void validateCaptcha(CaptchaDO record){
		if(null == record || StringUtils.isBlank(record.getTo()))
			throw new BusinessException(ErrorCodeEnum.CAPTCHA_MOBILE_INVALID);
		if(StringUtils.isBlank(record.getBusiType()))
			throw new BusinessException(ErrorCodeEnum.CAPTCHA_BUSITYPE_INVALID);
	}

	/**
	 * 设置默认值
	 * @param record
	 */
	private void setRecordDefaultValue(CaptchaDO record) {
		CaptchaEnum captchaEnum = CaptchaEnum.valueOf(record.getBusiType());
		if(null != captchaEnum){
			record.setTimesCheck(captchaEnum.getCheckTimes());
			// 设置默认禁止时间
			record.setDelayMinutes(captchaEnum.getDelayMinute());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, captchaEnum.getValidMinute());
			record.setInvalidTime(cal.getTime());
		}

		//设置默认有效
		if(null == record.getIsActive()) {
			record.setIsActive(Boolean.TRUE);
		}

		//设置默认创建时间
		if(null == record.getCreateTime()) {
			record.setCreateTime(new Date());
		}

		//设置默认失效时间，10分钟之后
		if(null == record.getInvalidTime()) {
			Date createTime = record.getCreateTime();
			Calendar cal = Calendar.getInstance();
			cal.setTime(createTime);
			cal.add(Calendar.MINUTE, DEFAULT_VALID_TIME);
			record.setInvalidTime(cal.getTime());
		}

		//设置默认延时
		if(null == record.getDelayMinutes() || record.getDelayMinutes() < 0) {
			record.setDelayMinutes(DEFAULT_FORBIDDEN_MINUTES);
		}

		//设置默认验证码
		if(null == record.getCaptcha()) {
			record.setCaptcha(RandomUtil.getRandomNum(DEFAULT_CAPTCHA_LENGTH));
		}

		//设置默认可校验次数
		if(null == record.getTimesCheck()) {
			record.setTimesCheck(DEFAULT_CHECK_TIMES);
		}
	}

	/**
	 * 失效所有同接收方,同类型验证码
	 * @param to
	 * @param busiType
	 */
	private void invalidSameCaptcha(String to, String busiType) {

		if(StringUtils.isNotBlank(to)) return ;

		Example updateExample = new Example(CaptchaDO.class);
		updateExample.createCriteria()
				.andEqualTo("to", to)
				.andEqualTo("busiType", busiType)
				.andEqualTo("isActive", Boolean.TRUE);
		CaptchaDO updateRecord = new CaptchaDO();
		updateRecord.setIsActive(Boolean.FALSE);
		mapper.updateByExampleSelective(updateRecord, updateExample);
	}

}
