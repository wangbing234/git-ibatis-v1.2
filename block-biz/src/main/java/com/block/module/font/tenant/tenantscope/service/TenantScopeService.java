/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商家经营范围-TenantScope
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantscope.service;

import com.block.core.ibatis.service.BaseService;
import com.block.module.font.tenant.tenantextend.entity.TenantExtend;
import com.block.module.font.tenant.tenantscope.dao.TenantScopeDao;
import com.block.module.font.tenant.tenantscope.entity.TenantScope;


/**
 * 商家经营范围Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface TenantScopeService  extends BaseService<TenantScope,TenantScopeDao>{

	/**
	 * 更新用户范围
	 * @param userExtend
	 */
	public void updateTenantScope(TenantExtend userExtend);
}
