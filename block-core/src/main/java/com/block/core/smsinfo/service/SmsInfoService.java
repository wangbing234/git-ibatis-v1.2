/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 短信记录表-SmsInfo
 * @date 2017-10-31 10:20:52
 * @version V1.0
 **/
package com.block.core.smsinfo.service;

import com.block.core.ibatis.service.BaseService;
import com.block.core.smsinfo.dao.SmsInfoDao;
import com.block.core.smsinfo.entity.SmsInfo;


/**
 * 短信记录表Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface SmsInfoService  extends BaseService<SmsInfo,SmsInfoDao>{

	public void sendcode(Integer userId,String phone,String templateType,String systemType);
	
	public boolean verifycode(String phone, String code);
}
