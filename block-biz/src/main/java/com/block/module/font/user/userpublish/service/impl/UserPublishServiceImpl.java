/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公关发布信息表-UserPublish
 * @date 2017-11-04 15:45:10
 * @version V1.0
 **/
package com.block.module.font.user.userpublish.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.block.core.domain.FontCache;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.basic.invite.entity.InviteStatus;
import com.block.module.font.basic.invite.service.InviteService;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.basic.mebbasic.service.MebBasicService;
import com.block.module.font.tenant.tenantpublish.entity.PublishStatus;
import com.block.module.font.tenant.tenantpublish.entity.TenantPublish;
import com.block.module.font.tenant.tenantpublish.service.TenantPublishService;
import com.block.module.font.tenant.tenantusermatch.service.TenantUserMatchService;
import com.block.module.font.user.userextend.entity.UserStatus;
import com.block.module.font.user.userpublish.dao.UserPublishDao;
import com.block.module.font.user.userpublish.entity.UserPublish;
import com.block.module.font.user.userpublish.service.UserPublishService;
import com.block.module.font.user.userpublisscope.entity.UserPublisScope;
import com.block.module.font.user.userpublisscope.service.UserPublisScopeService;


/**
 * 公关发布信息表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("userPublishServiceUser")
public class UserPublishServiceImpl extends BaseCenterServiceImpl<UserPublish,UserPublishDao> implements UserPublishService {
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//注入UserPublisScopeService
	@Resource(name="userPublisScopeServiceUser")
	UserPublisScopeService userPublisScopeService;
	
	@Resource(name="mebBasicServiceBasic")
	MebBasicService mebBasicService;
	
	//匹配类
	@Resource(name="tenantUserMatchServiceTenant")
	TenantUserMatchService tenantUserMatchService;
	
	//注入TenantPublishService
	@Resource(name="inviteServiceBasic")
	InviteService inviteService;
	
	//注入TenantPublishService
	@Resource(name="tenantPublishServiceTenant")
	TenantPublishService tenantPublishService;
	
	@Override
	public UserPublish publish(UserPublish userPublish,MebBasic basic){
		Integer userid=Integer.valueOf(FontCache.getLoaclUser().getId());
		userPublish.setId(userid);
		Date currentDate=new Date();
		//其实就是发布消息的更新时间
		userPublish.setCreatetime(currentDate);
		logger.info("更新发布状态"); 
		this.baseDao.update(userPublish);
		basic.setAddress(userPublish.getAddress());
		basic.setLatitude(userPublish.getLatitude());
		basic.setLongitude(userPublish.getLongitude());
		basic.setUpdatetime(currentDate);
		basic.setStatus(UserStatus.IN_LINE);
		mebBasicService.update(basic);
		
		logger.info("删除用户范围信息"); 
		And and =new And("userId", userid,Restrictions.EQ);
		userPublisScopeService.del(and);
		
		logger.info("更新用户范围信息"); 
		String serviceTypes=userPublish.getServiceType();
		for (String servceType : serviceTypes.split(",")) {
			if(StringUtils.isNotBlank(servceType)){
				UserPublisScope uScope=new UserPublisScope();
				uScope.setScopeId(Integer.valueOf(servceType));
				uScope.setUserId(userid);
				userPublisScopeService.add(uScope);
			}
		}
		
		this.baseDao.update(userPublish);
		return userPublish;
	}
	
	@Override
	public String addUserToTenantPublish(Invite invite) {
		//查询正在招聘的当前商户
		TenantPublish tenantPublish = tenantPublishService.get(invite.getPublishId());
		if(null==tenantPublish || !PublishStatus.PUBLISH_INLINE.equals(tenantPublish.getStatus())){
			return "商户邀请已经失效或状态不正确！"; 
		}
		
		logger.info("增加匹配"); 
		tenantUserMatchService.addToMatch(invite.getUserId(), tenantPublish,true);
		
		logger.info("更新用户状态"); 
		mebBasicService.update(invite.getUserId(),"status",UserStatus.IN_LINE_MATCH);
		
		logger.info("更新邀请状态"); 
		inviteService.update(invite.getId(), "status", InviteStatus.ALREADY);
		return null;
	}

	@Override
	public int addUserPublish(UserPublish userPublish) {
		// TODO Auto-generated method stub
		return this.baseDao.add(userPublish);
	}

}
