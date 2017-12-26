/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户发布信息表-TenantPublish
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantpublish.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.module.font.tenant.tenantextend.entity.GuestOrderParms;
import com.block.module.font.tenant.tenantextend.entity.GuestOrdersResult;
import com.block.module.font.tenant.tenantextend.web.UserFilterParm;
import com.block.module.font.tenant.tenantextend.web.UserFilterResult;
import com.block.module.font.tenant.tenantpublish.dao.TenantPublishDao;
import com.block.module.font.tenant.tenantpublish.entity.TenantPublish;



/**
 * 商户发布信息表dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("tenantPublishDaoTenant")
public class TenantPublishDaoImpl extends  BaseDaoImpl<TenantPublish> implements TenantPublishDao {

	@Override
	public PagerModel<UserFilterResult> listUser(UserFilterParm parm) {
		return super.selectPageList("TenantExtendDao.selectUserList","TenantExtendDao.selectUserCount",parm);
	}
	
	@Override
	public PagerModel<GuestOrdersResult> listGuestOrders(GuestOrderParms guestOrderParms){
		return super.selectPageList("TenantExtendDao.selectGuestOrderList","TenantExtendDao.selectGuestOrderCount",guestOrderParms);
	}
}
