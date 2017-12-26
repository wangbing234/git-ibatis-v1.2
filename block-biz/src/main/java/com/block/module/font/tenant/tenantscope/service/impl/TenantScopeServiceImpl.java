/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商家经营范围-TenantScope
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantscope.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.module.font.tenant.tenantextend.entity.TenantExtend;
import com.block.module.font.tenant.tenantscope.dao.TenantScopeDao;
import com.block.module.font.tenant.tenantscope.entity.TenantScope;
import com.block.module.font.tenant.tenantscope.service.TenantScopeService;


/**
 * 商家经营范围Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("tenantScopeServiceTenant")
public class TenantScopeServiceImpl extends BaseCenterServiceImpl<TenantScope,TenantScopeDao> implements TenantScopeService {

	public void updateTenantScope(TenantExtend userExtend){
		Integer userId=userExtend.getId();
		And where =new And("userId", userId,Restrictions.EQ);
		this.baseDao.del(where);
		if(StringUtils.isNotBlank(userExtend.getScope()))
		{
			for (String scope : userExtend.getScope().split(",")) {
				if(StringUtils.isNotBlank(scope)){
					TenantScope po=new TenantScope();
					po.setUserId(userId);
					po.setScopeId(Integer.valueOf(scope));
					this.baseDao.add(po);
				}
			}
		}
		
	}
	
	
}
