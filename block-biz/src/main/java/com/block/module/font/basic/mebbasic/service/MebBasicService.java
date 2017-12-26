/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -MebBasic
 * @date 2017-11-04 15:50:22
 * @version V1.0
 **/
package com.block.module.font.basic.mebbasic.service;

import java.util.Map;

import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.mebbasic.dao.MebBasicDao;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.tenant.tenantextend.entity.TenantExtend;


/**
 * Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface MebBasicService  extends BaseService<MebBasic,MebBasicDao>{
	 /**
     * 更新当前登录信息
     * @param po
     * @return
     */
	 public Map<String, Object> wxloginUpdate(MebBasic mebBasic) throws Exception ;
	 
	 /**
	  * 登录查询当前状态
	  * @param mebBasic
	  * @return
	  * @throws Exception
	  */
	 public Map<String, Object> wxlogin(MebBasic mebBasic) throws Exception;
	 
 	 /**
     * 更新用户包含范围
     * @param po
     * @return
     */
	 public void updateTenant(TenantExtend userExtend) ;
	 
}
