/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配日志表-TenantUserMatchLog
 * @date 2017-11-06 14:25:13
 * @version V1.0
 **/
package com.block.module.font.basic.tenantusermatchlog.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.module.font.basic.tenantusermatchlog.dao.TenantUserMatchLogDao;
import com.block.module.font.basic.tenantusermatchlog.entity.TenantUserMatchLog;
import com.block.module.font.basic.tenantusermatchlog.service.TenantUserMatchLogService;


/**
 * 商户和用户匹配日志表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("tenantUserMatchLogServiceBasic")
public class TenantUserMatchLogServiceImpl extends BaseCenterServiceImpl<TenantUserMatchLog,TenantUserMatchLogDao> implements TenantUserMatchLogService {

	@Override
	public TenantUserMatchLog log(Integer matchId, int status, String remark) {
		TenantUserMatchLog tenantUserMatchLog=new TenantUserMatchLog();
		tenantUserMatchLog.setMatchId(matchId);
		tenantUserMatchLog.setStatus(status);
		tenantUserMatchLog.setCreatetime(new Date());
		tenantUserMatchLog.setRemark(remark);
		this.baseDao.add(tenantUserMatchLog);
		return tenantUserMatchLog;
	}

}
