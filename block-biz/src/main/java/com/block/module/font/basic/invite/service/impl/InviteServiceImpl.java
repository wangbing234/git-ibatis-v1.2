/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 邀请表-Invite
 * @date 2017-11-06 14:25:12
 * @version V1.0
 **/
package com.block.module.font.basic.invite.service.impl;

import org.springframework.stereotype.Service;

import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.module.font.basic.invite.dao.InviteDao;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.basic.invite.entity.InvitePageParms;
import com.block.module.font.basic.invite.entity.InviteResult;
import com.block.module.font.basic.invite.service.InviteService;


/**
 * 邀请表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("inviteServiceBasic")
public class InviteServiceImpl extends BaseCenterServiceImpl<Invite,InviteDao> implements InviteService {

	@Override
	public PagerModel<InviteResult> selectUserInvitePageList(InvitePageParms invite) {
		 PagerModel<InviteResult> data = this.baseDao.selectPageList("selectUserInviteResult","selectUserInviteCount", invite);
		return data;
	}
	
	@Override
	public PagerModel<InviteResult> selectTenantInvitePageList(InvitePageParms invite) {
		 PagerModel<InviteResult> data = this.baseDao.selectPageList("selectTenantInviteResult","selectTenantInviteCount", invite);
		return data;
	}

}
