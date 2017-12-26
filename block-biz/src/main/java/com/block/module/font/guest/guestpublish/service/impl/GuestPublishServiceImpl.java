/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 客人预定信息表-GuestPublish
 * @date 2017-11-04 15:45:20
 * @version V1.0
 **/
package com.block.module.font.guest.guestpublish.service.impl;

import org.springframework.stereotype.Service;

import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.module.font.guest.guestpublish.dao.GuestPublishDao;
import com.block.module.font.guest.guestpublish.entity.GuestPublish;
import com.block.module.font.guest.guestpublish.service.GuestPublishService;
import com.block.module.font.guest.guestpublish.web.TenantFilterParm;
import com.block.module.font.guest.guestpublish.web.TenantFilterResult;


/**
 * 客人预定信息表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("guestPublishServiceGuest")
public class GuestPublishServiceImpl extends BaseCenterServiceImpl<GuestPublish,GuestPublishDao> implements GuestPublishService {

	@Override
	public PagerModel<TenantFilterResult> listTenant(TenantFilterParm parm) {
		// TODO Auto-generated method stub
		return this.baseDao.listTenant(parm);
	}

}
