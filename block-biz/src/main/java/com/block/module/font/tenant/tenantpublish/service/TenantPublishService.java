/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户发布信息表-TenantPublish
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantpublish.service;

import java.io.IOException;

import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.tenant.tenantextend.entity.GuestOrderParms;
import com.block.module.font.tenant.tenantextend.entity.GuestOrdersResult;
import com.block.module.font.tenant.tenantextend.web.UserFilterParm;
import com.block.module.font.tenant.tenantextend.web.UserFilterResult;
import com.block.module.font.tenant.tenantpublish.dao.TenantPublishDao;
import com.block.module.font.tenant.tenantpublish.entity.TenantPublish;


/**
 * 商户发布信息表Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface TenantPublishService  extends BaseService<TenantPublish,TenantPublishDao>{
	
	/**
	 * 发布信息	(如果有用户并且发送邀请)
	 * @param tenantPublish 租户发布的信息
	 * @param _user			当前用户
	 * @return
	 * @throws IOException 
	 */
	public TenantPublish publish(TenantPublish tenantPublish) throws IOException;
	
	/**
	 * 增加用户到Publish
	 * @param inviteId
	 * @return
	 */
	public String addUserToPublish(Invite invite);
	
	/**
	 * 查询附近用户
	 * @param parm
	 * @return
	 */
	public PagerModel<UserFilterResult> listUser(UserFilterParm parm);
	
	/**
	 * 查询预定用户的订单
	 * @param guestOrderParms
	 * @return
	 */
	public PagerModel<GuestOrdersResult> listGuestOrders(GuestOrderParms guestOrderParms);

}
