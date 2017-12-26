/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户发布信息表-TenantPublish
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantpublish.service.impl;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.block.core.domain.FontCache;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.module.common.enums.BusiUserType;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.basic.invite.entity.InviteStatus;
import com.block.module.font.basic.invite.service.InviteService;
import com.block.module.font.basic.mebwallet.service.MebWalletService;
import com.block.module.font.basic.socket.entity.PushMessage;
import com.block.module.font.basic.socket.websocket.MyWebSocketHandler;
import com.block.module.font.tenant.tenantextend.entity.GuestOrderParms;
import com.block.module.font.tenant.tenantextend.entity.GuestOrdersResult;
import com.block.module.font.tenant.tenantextend.web.UserFilterParm;
import com.block.module.font.tenant.tenantextend.web.UserFilterResult;
import com.block.module.font.tenant.tenantpublish.dao.TenantPublishDao;
import com.block.module.font.tenant.tenantpublish.entity.PublishStatus;
import com.block.module.font.tenant.tenantpublish.entity.TenantPublish;
import com.block.module.font.tenant.tenantpublish.service.TenantPublishService;
import com.block.module.font.tenant.tenantusermatch.service.TenantUserMatchService;
import com.block.module.font.user.userextend.entity.UserStatus;
import com.block.module.font.user.userpublish.service.UserPublishService;


/**
 * 商户发布信息表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("tenantPublishServiceTenant")
public class TenantPublishServiceImpl extends BaseCenterServiceImpl<TenantPublish,TenantPublishDao> implements TenantPublishService {

	//注入UserPublishService
	@Resource(name="userPublishServiceUser")
	UserPublishService userPublishService;
		
	//注入TenantPublishService
	@Resource(name="inviteServiceBasic")
	InviteService inviteService;
	
	//注入mebWalletService
	@Resource(name="mebWalletServiceBasic")
	protected MebWalletService mebWalletService;
	
	//匹配类
	@Resource(name="tenantUserMatchServiceTenant")
	TenantUserMatchService tenantUserMatchService;
	
	//消息推送类
	@Resource
	MyWebSocketHandler handler;
	
	@Override
	public TenantPublish publish(TenantPublish tenantPublish) throws IOException {
		tenantPublish.setTenantId(Integer.valueOf(FontCache.getLoaclUser().getId()));
		tenantPublish.setStatus(PublishStatus.PUBLISH_INLINE);
		int publisId = this.baseDao.add(tenantPublish);
		tenantPublish.setId(publisId);
		if(tenantPublish.getInviteId()!=null){
			//发送邀请
			Integer userid=Integer.valueOf(FontCache.getLoaclUser().getId());
			Invite invite=new Invite();
			invite.setStatus(InviteStatus.NORMAL);
			invite.setUserId(userid);
			invite.setType(BusiUserType.TENANT);
			invite.setTenantId(tenantPublish.getTenantId());
			invite.setPublishId(publisId); 
			invite.setCreatetime(new Date());
			invite.setRemark("商户发送邀请"); 
			inviteService.add(invite);
			// 发送推送消息
			PushMessage msg=new PushMessage(PushMessage.T_INVITE, tenantPublish.getId(), invite.getTenantId(), FontCache.getLoaclUser().getName(), null);
			handler.sendMessageToUser(userid, msg);
		}
		return tenantPublish;
	}
	
	/**
	 * 
	 * @param inviteId
	 * @return
	 */
	public String addUserToPublish(Invite invite) {
		//查询正在招聘的当前商户
		TenantPublish tenantPublish = this.baseDao.get(invite.getPublishId());
		if(null==tenantPublish || !tenantPublish.getStatus().equals(PublishStatus.PUBLISH_INLINE)){
			return "商户招聘失效或已经满员！"; 
		}
		//修改匹配信息
		tenantUserMatchService.addToMatch(invite.getUserId(), tenantPublish,false);
		
		//更新用户状态
		mebWalletService.update(invite.getUserId(),"status",UserStatus.OUT_LINE);
		
		//更新邀请状态
		inviteService.update(invite.getId(), "status", InviteStatus.ALREADY);
		return null;
	}


	@Override
	public PagerModel<UserFilterResult> listUser(UserFilterParm parm) {
		// TODO Auto-generated method stub
		return this.baseDao.listUser(parm);
	}
	
	@Override
	public PagerModel<GuestOrdersResult> listGuestOrders(GuestOrderParms guestOrderParms) {
		// TODO Auto-generated method stub
		return this.baseDao.listGuestOrders(guestOrderParms);
	}
}
