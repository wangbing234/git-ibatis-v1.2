/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配表-TenantUserMatch
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantusermatch.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.block.core.domain.Busi;
import com.block.core.domain.FontCache;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.redis.baseuser.JwtUserInfo;
import com.block.core.smsinfo.service.SmsInfoService;
import com.block.core.smsinfo.service.impl.SmsInfoServiceImpl;
import com.block.module.common.enums.BusiUserType;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.basic.mebbasic.service.MebBasicService;
import com.block.module.font.basic.mebcomment.entity.MebComment;
import com.block.module.font.basic.mebcomment.entity.MebCommentType;
import com.block.module.font.basic.mebcomment.service.MebCommentService;
import com.block.module.font.basic.socket.entity.PushMessage;
import com.block.module.font.basic.socket.websocket.MyWebSocketHandler;
import com.block.module.font.basic.tenantusermatchlog.service.TenantUserMatchLogService;
import com.block.module.font.tenant.tenantpublish.entity.PublishStatus;
import com.block.module.font.tenant.tenantpublish.entity.TenantPublish;
import com.block.module.font.tenant.tenantpublish.service.TenantPublishService;
import com.block.module.font.tenant.tenantusermatch.dao.TenantUserMatchDao;
import com.block.module.font.tenant.tenantusermatch.entity.TenantMatchStatus;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatchParms;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatchResult;
import com.block.module.font.tenant.tenantusermatch.service.TenantUserMatchService;
import com.block.module.font.user.userextend.entity.UserStatus;
import com.block.module.font.user.userpublish.service.UserPublishService;


/**
 * 商户和用户匹配表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("tenantUserMatchServiceTenant")
public class TenantUserMatchServiceImpl extends BaseCenterServiceImpl<TenantUserMatch,TenantUserMatchDao> implements TenantUserMatchService {

	//注入TenantUserMatchService
	@Resource(name="tenantUserMatchLogServiceBasic")
	TenantUserMatchLogService tenantUserMatchLogService;
	
	//注入TenantPublishService
	@Resource(name="tenantPublishServiceTenant")
	TenantPublishService tenantPublishService;
	
	
	@Resource(name="mebCommentServiceBasic")
	MebCommentService mebCommentService;
	
	//注入UserPublishService
	@Resource(name="userPublishServiceUser")
	UserPublishService userPublishService;
	
	//注入MebBasicService
	@Resource(name="mebBasicServiceBasic")
	 MebBasicService mebBasicService;
	@Resource(name="smsInfoServiceSystem")
	SmsInfoService smsInfoService;
	
	//消息推送类
	@Resource
	MyWebSocketHandler handler;
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void addToMatch(Integer userId, TenantPublish tenantPublish,Boolean isUser) {
		//更新发布状态,查询正在招聘的当前商户
		Integer itemStatus=null;
		String noticeMsg=null;
		if(isUser){
			itemStatus=TenantMatchStatus.USER_ACCEPT;
			noticeMsg="用户接受订单";
			MebBasic teant = mebBasicService.get(tenantPublish.getTenantId());
			//通知商户
			smsInfoService.sendcode(userId, teant.getPhone(), SmsInfoServiceImpl.smsTplTenantInvite, BusiUserType.USER+"");
		}
		else{
			itemStatus=TenantMatchStatus.TENANT_ACCEPT;
			noticeMsg="商户接受订单";
			//通知用户
			MebBasic user = mebBasicService.get(userId);
			if(null!=user.getPhone())
			smsInfoService.sendcode(tenantPublish.getTenantId(), user.getPhone(), SmsInfoServiceImpl.smsTplUserInvite, BusiUserType.TENANT+"");
		}
		//增加到商家和用户匹配表中
		TenantUserMatch tenantMatch=new TenantUserMatch();
		tenantMatch.setInitType(BusiUserType.TENANT);
		tenantMatch.setStatus(itemStatus);
		tenantMatch.setTenantId(tenantPublish.getTenantId());
		tenantMatch.setTenantPublishId(tenantPublish.getId());
		tenantMatch.setServiceType(tenantPublish.getServiceType());
		tenantMatch.setCreatetime(new Date());
		Integer payType=tenantPublish.getPayType();
		tenantMatch.setPayType(payType);
		//按次收费一次买断
		tenantMatch.setPriceCal(tenantPublish.getPrice());
		tenantMatch.setUserId(userId);
		
		logger.info("增加匹配记录"); 
		int matchId = this.baseDao.add(tenantMatch);
		
		
		Map<String, Object> map=new HashMap<String, Object>();
		int nowNumber=tenantPublish.getNumber()==null?0:tenantPublish.getNumber();
		int needNumber=tenantPublish.getNeedNumber();
		if(needNumber>nowNumber+1){
			map.put("status", PublishStatus.PUBLISH_INLINE);
		}
		else{
			map.put("status", PublishStatus.PUBLISH_FINISH);
		}
		map.put("number", nowNumber+1);
		logger.info("添加日志"); 
		tenantUserMatchLogService.log(matchId,itemStatus, noticeMsg);
		
		logger.info("更新租户状态"); 
		tenantPublishService.update(tenantPublish.getId(), map);
		//更新当前正在匹配的id
		logger.info("更新用户为那个匹配单"); 
		this.userPublishService.update(userId, "tenantPublishId",matchId);
	}

	@Override
	public void removeMatch(TenantUserMatch tenantUserMatch,Boolean isUser) {
		TenantPublish tenantPublish = tenantPublishService.get(tenantUserMatch.getTenantPublishId());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("number", tenantPublish.getNumber()-1); 
		if(tenantPublish.getStatus()==PublishStatus.PUBLISH_FINISH){
			map.put("status", PublishStatus.PUBLISH_INLINE); 
		}
		tenantPublishService.update(tenantUserMatch.getTenantPublishId(), map);
		
		//更新匹配状态
		this.baseDao.update(tenantUserMatch.getId(), "status", TenantMatchStatus.USER_CANCEL,"inMember",Busi.NO);
		
		//更新用户状态
		mebBasicService.update(tenantUserMatch.getUserId(),"status", UserStatus.OUT_LINE);
		
		Integer itemStatus=null;
		String noticeMsg=null;
		if(isUser){
			itemStatus=TenantMatchStatus.USER_CANCEL;
			noticeMsg="用取消受订单";
			//通知商户却取消订单
			MebBasic teant = mebBasicService.get(tenantUserMatch.getTenantId());
			if(null!=teant.getPhone())
				smsInfoService.sendcode(tenantUserMatch.getUserId(), teant.getPhone(), SmsInfoServiceImpl.smsTplTenantCancel, BusiUserType.USER+"");
		}
		else{
			itemStatus=TenantMatchStatus.TENANT_CANCEL;
			noticeMsg="商户取消订单";
			MebBasic user = mebBasicService.get(tenantUserMatch.getUserId());
			//通知用户取消订单
			if(null!=user.getPhone())
				smsInfoService.sendcode(tenantUserMatch.getTenantId(), user.getPhone(), SmsInfoServiceImpl.smsTplUserCancel, BusiUserType.TENANT+"");
		}
		
		tenantUserMatchLogService.log(tenantUserMatch.getId(),itemStatus, noticeMsg);
	}

	@Override
	public String addCommentToMatch(MebComment mebComment) {
		 TenantUserMatch tenantUserMatch = this.baseDao.get(mebComment.getBusinessId());
		 mebComment.setUserId(tenantUserMatch.getUserId());
		
		 logger.info("更新当前为评价状态"); 
		 if(MebCommentType.TENANT_ORDER_MATCH.equals(mebComment.getBusinessId())){
			 if(!TenantMatchStatus.TENANT_PAYD_NOTICE.equals(tenantUserMatch.getStatus())){
				 return "当前无法评价，订单状态不正确！";
			 }
			 this.baseDao.update(tenantUserMatch.getId(), "status", TenantMatchStatus.TENANT_COMMENT);
			 tenantUserMatchLogService.log(tenantUserMatch.getId(),TenantMatchStatus.TENANT_COMMENT, "完成评价");
		 }
		 logger.info("增加评价"); 
		 mebCommentService.addComment(mebComment);
		 return null;
	}

	@Override
	public List<TenantUserMatchResult> queryTenantUserMatch(TenantUserMatchParms tenantUserMatchParms) {
		return super.sqlSessionTemplate.selectList("TenantUserMatchDao.selectUserMatchList",tenantUserMatchParms);
	}

	@Override
	public String payMatch(Integer matchId){
		// TODO Auto-generated method stub
		TenantUserMatch tenantUserMatch = this.baseDao.get(matchId);
		if(tenantUserMatch==null){
			return "找不到订单！"; 
		}
		int matchStatus=tenantUserMatch.getStatus();
		//付款
		if(matchStatus==TenantMatchStatus.TENANT_ARRIVE_NOTICE  || matchStatus==TenantMatchStatus.TENANT_FINISH_NOTICE){
			this.baseDao.update(matchId, "status", TenantMatchStatus.TENANT_PAYD_NOTICE);
			tenantUserMatchLogService.log(matchId,TenantMatchStatus.TENANT_PAYD_NOTICE, "商户付款");
			//付款后更新用户状态，为上班状态
			mebBasicService.update(tenantUserMatch.getId(), "status", UserStatus.IN_LINE);
		}
		else{
			return "订单状态不正确，无法付款！"; 
		}
		JwtUserInfo loaclUser = FontCache.getLoaclUser();
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.T_PAY, tenantUserMatch.getId(), tenantUserMatch.getTenantId(), loaclUser.getName(), "商家"+loaclUser.getName()+"确认已经给您付款");
		try {
			handler.sendMessageToUser(tenantUserMatch.getUserId(), msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
