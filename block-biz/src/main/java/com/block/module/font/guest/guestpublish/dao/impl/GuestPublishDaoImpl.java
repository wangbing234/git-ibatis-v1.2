/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 客人预定信息表-GuestPublish
 * @date 2017-11-04 15:45:20
 * @version V1.0
 **/
package com.block.module.font.guest.guestpublish.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.module.font.guest.guestpublish.dao.GuestPublishDao;
import com.block.module.font.guest.guestpublish.entity.GuestPublish;
import com.block.module.font.guest.guestpublish.web.TenantFilterParm;
import com.block.module.font.guest.guestpublish.web.TenantFilterResult;



/**
 * 客人预定信息表dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("guestPublishDaoGuest")
public class GuestPublishDaoImpl extends  BaseDaoImpl<GuestPublish> implements GuestPublishDao {

	@Override
	public PagerModel<TenantFilterResult> listTenant(TenantFilterParm parm) {
		return super.selectPageList("GuestPublishDao.selectTenantList","GuestPublishDao.selectTenantCount", parm);
	}

}
