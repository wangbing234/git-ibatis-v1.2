/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商家经营范围-TenantScope
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantscope.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.module.font.tenant.tenantscope.dao.TenantScopeDao;
import com.block.module.font.tenant.tenantscope.entity.TenantScope;



/**
 * 商家经营范围dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("tenantScopeDaoTenant")
public class TenantScopeDaoImpl extends  BaseDaoImpl<TenantScope> implements TenantScopeDao {

}
