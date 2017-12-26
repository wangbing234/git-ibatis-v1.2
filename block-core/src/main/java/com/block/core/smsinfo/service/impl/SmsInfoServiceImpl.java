/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 短信记录表-SmsInfo
 * @date 2017-10-31 10:20:52
 * @version V1.0
 **/
package com.block.core.smsinfo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.block.core.common.CoreParamCache;
import com.block.core.common.exception.BusiException;
import com.block.core.domain.Busi;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.smsinfo.dao.SmsInfoDao;
import com.block.core.smsinfo.entity.SmsInfo;
import com.block.core.smsinfo.service.SmsInfoService;
import com.block.core.smsinfo.utils.CheckSumBuilder;


/**
 * 短信记录表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("smsInfoServiceSystem")
public class SmsInfoServiceImpl extends BaseCenterServiceImpl<SmsInfo,SmsInfoDao> implements SmsInfoService {
	
	// 短信类型：发送验证码
	public static final String SMS_TYPE_MEMBERREG = "smsTplMemberReg";
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	// 系统通知模板
//	public static final String smsTplSystemNotice="smsTplSystemNotice";
	
	// 用户取消订单模板
	public static final String smsTplUserCancel="smsTplUserCancel";
	// 商户取消订单模板
	public static final String smsTplTenantCancel="smsTplTenantCancel";

	// 用户接受预定短信模板（编号）
	public static final String smsTplUserInvite="smsTplUserInvite";

	// 商户接受预定短信模板（编号）
	public static final String smsTplTenantInvite="smsTplTenantInvite";

//	// 商户付款短信模板
//	public static final String smsTplTenantPay="smsTplTenantPay";

//	// 客户预定定金模板
//	public static final String smsTplGuestPrepay="smsTplGuestPrepay";

	// 商户接受客户预定模板
	public static final String smsTplTenantAcceptGuest="smsTplTenantAcceptGuest";
	
	
	// 发送短信URL
	public static final String SMS_SEND_URL = "https://api.netease.im/sms/sendcode.action";
	// 校验URL
	public static final String SMS_VERIFY_URL = "https://api.netease.im/sms/verifycode.action";
	
	/**
	 * 发送短信验证码<br>
	 * 注册
	 * 
	 * @param phone
	 * @throws BusiException
	 */
	@Resource(name="coreParamCache")
	private CoreParamCache coreParamCache;
	
	/**
	 * 发送短信验证码
	 * @param phone 手机号
	 * @param type	类型
	 * @throws BusiException
	 */
	public void sendcode(Integer userId,String phone,String templateType,String systemType) throws BusiException {
		Map<String, Object> ret =null;
		String smsNo="";
		Date createTime=new Date();
		//模板
			ret=doSendcode(phone, smsNo);
		Date sendTime=new Date();
		String retCode = ret.get("code").toString();
		if (retCode.equals("200")) { //发送成功
			
		} else { // 发送失败
			if (ret.containsKey("desc") || ret.containsKey("msg")) {
				logger.info("发送短信失败：" + retCode + ret.get("desc").toString());
//				throw new BusiException("发送短信失败：" + retCode + ret.get("desc").toString());
			}  
		}
		// 记录发送记录
		SmsInfo po=new SmsInfo();
		po.setSmsPhone(phone);
		po.setSystemId(userId);
		po.setSystemType(systemType);
		po.setSmsNo(smsNo);
		po.setSmsArgs("");
		po.setSmsType(templateType);
		po.setRetCode(retCode);
		po.setCreatetime(createTime); 
		po.setSendTime(sendTime);
		this.baseDao.add(po);
	}

	/**
	 * 发送短信验证码(忘记密码)
	 * @param phone
	 * @throws BusiException
	 */
	private Map<String, Object> doSendcode(String phone,String templateid) throws BusiException {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("mobile", phone); // 目标手机号
		args.put("deviceId", phone); // 目标设备号
		args.put("templateid",templateid); // 模板编号
		args.put("codeLen", 4); // 验证码长度
		String retStr;
		try {
			retStr = CheckSumBuilder.sendSms(SMS_SEND_URL, args);
			logger.info("retStr : "+retStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusiException("发送短信失败");
		}
		// 检查发送结果
		Map<String, Object> ret = (Map<String, Object>) JSON.parse(retStr);
		return ret;
	}

	/**
	 * 校验验证码
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean verifycode(String phone, String code) {
		if(Busi.YES.equals(coreParamCache.getString("isOpenSms"))){
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("mobile", phone); // 目标手机号
			args.put("code", code); // 验证码
			String retStr;
			try {
				retStr = CheckSumBuilder.sendSms(SMS_VERIFY_URL, args);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusiException("校验验证码失败");
			}

			// 检查发送结果
			String retCode = ((Map<String, Object>) JSON.parse(retStr)).get("code").toString();
			if (retCode.equals("200")) {
				return true;
			} else {
				logger.info("短信校验错误："+retStr); 
				return false;
			}
		}
		else  if("9999".equals(code)){
			return true;
		}
		return false;
	}
	
}
