/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户发布信息表-TenantPublish
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/

package com.block.module.font.tenant.tenantpublish.dao;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.dao.BaseDao;
import com.block.module.font.tenant.tenantextend.entity.GuestOrderParms;
import com.block.module.font.tenant.tenantextend.entity.GuestOrdersResult;
import com.block.module.font.tenant.tenantextend.web.UserFilterParm;
import com.block.module.font.tenant.tenantextend.web.UserFilterResult;
import com.block.module.font.tenant.tenantpublish.entity.TenantPublish;



/**
 * 商户发布信息表dao层接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface TenantPublishDao extends BaseDao<TenantPublish>{

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
