/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配日志表-TenantUserMatchLog
 * @date 2017-11-06 14:25:13
 * @version V1.0
 **/
package com.block.module.font.basic.tenantusermatchlog.service;

import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.tenantusermatchlog.dao.TenantUserMatchLogDao;
import com.block.module.font.basic.tenantusermatchlog.entity.TenantUserMatchLog;


/**
 * 商户和用户匹配日志表Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface TenantUserMatchLogService  extends BaseService<TenantUserMatchLog,TenantUserMatchLogDao>{
	public TenantUserMatchLog log(Integer matchId,int status,String remark);
}
