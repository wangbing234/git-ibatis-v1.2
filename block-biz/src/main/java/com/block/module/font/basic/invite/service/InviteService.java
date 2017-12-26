/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 邀请表-Invite
 * @date 2017-11-06 14:25:12
 * @version V1.0
 **/
package com.block.module.font.basic.invite.service;

import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.invite.dao.InviteDao;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.basic.invite.entity.InvitePageParms;
import com.block.module.font.basic.invite.entity.InviteResult;


/**
 * 邀请表Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface InviteService  extends BaseService<Invite,InviteDao>{
	public PagerModel<InviteResult> selectTenantInvitePageList(InvitePageParms invite);
	
	public PagerModel<InviteResult> selectUserInvitePageList(InvitePageParms invite);
}
