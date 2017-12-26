/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配表-TenantUserMatch
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantusermatch.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.module.font.tenant.tenantusermatch.dao.TenantUserMatchDao;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;



/**
 * 商户和用户匹配表dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("tenantUserMatchDaoTenant")
public class TenantUserMatchDaoImpl extends  BaseDaoImpl<TenantUserMatch> implements TenantUserMatchDao {

}
