/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 客人预定信息表-GuestPublish
 * @date 2017-11-04 15:45:20
 * @version V1.0
 **/

package com.block.module.font.guest.guestpublish.dao;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.dao.BaseDao;
import com.block.module.font.guest.guestpublish.entity.GuestPublish;
import com.block.module.font.guest.guestpublish.web.TenantFilterParm;
import com.block.module.font.guest.guestpublish.web.TenantFilterResult;



/**
 * 客人预定信息表dao层接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface GuestPublishDao extends BaseDao<GuestPublish>{

	/**
	 * 查询附近用户
	 * @param parm
	 * @return
	 */
	public PagerModel<TenantFilterResult> listTenant(TenantFilterParm parm);
}
