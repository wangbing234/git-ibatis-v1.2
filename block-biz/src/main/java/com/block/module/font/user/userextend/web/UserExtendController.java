/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公关表扩展表-UserExtend
 * @date 2017-11-04 15:45:10
 * @version V1.0
 **/
package com.block.module.font.user.userextend.web;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.block.core.beanutils.BlockBeanUtils;
import com.block.core.domain.Busi;
import com.block.core.domain.FontCache;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.redis.baseuser.JwtUserInfo;
import com.block.module.common.enums.BusiUserType;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.basic.invite.entity.InvitePageParms;
import com.block.module.font.basic.invite.entity.InviteResult;
import com.block.module.font.basic.invite.entity.InviteStatus;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.basic.mebbasic.web.MebBasicController;
import com.block.module.font.basic.socket.entity.PushMessage;
import com.block.module.font.basic.socket.websocket.MyWebSocketHandler;
import com.block.module.font.basic.tenantusermatchlog.service.TenantUserMatchLogService;
import com.block.module.font.tenant.tenantpublish.entity.PublishStatus;
import com.block.module.font.tenant.tenantpublish.entity.TenantPublish;
import com.block.module.font.tenant.tenantpublish.service.TenantPublishService;
import com.block.module.font.tenant.tenantusermatch.entity.TenantMatchStatus;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;
import com.block.module.font.tenant.tenantusermatch.service.TenantUserMatchService;
import com.block.module.font.user.userextend.entity.UserStatus;
import com.block.module.font.user.userpublish.entity.UserPublish;
import com.block.module.font.user.userpublish.service.UserPublishService;


/**
 * 公关表扩展表Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/user")//域/模块
public class UserExtendController  extends MebBasicController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	//注入UserPublishService
	@Resource(name="userPublishServiceUser")
	UserPublishService userPublishService;
	
	
	//匹配类
	@Resource(name="tenantUserMatchServiceTenant")
	TenantUserMatchService tenantUserMatchService;
	
	//注入TenantPublishService
	@Resource(name="tenantPublishServiceTenant")
	TenantPublishService tenantPublishService;
	
	//注入TenantUserMatchService
	@Resource(name="tenantUserMatchLogServiceBasic")
	TenantUserMatchLogService tenantUserMatchLogService;
	
	//消息推送类
	@Resource
	MyWebSocketHandler handler;
//	/**
//	 * 用户登录
//	 * @param userExtend UserExtend对象
//	 * @return
//	 */
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	private ResultBean register(@RequestBody  @Valid UserExtend userExtend,BindingResult bindResult) {
//		//字段规则校验
//		if(bindResult.hasErrors()){
//			return fail(bindResult.getFieldError().getDefaultMessage());
//		}
//		
//		ResultBean rb =null;
//		try {
//			rb =mebBasicService.addUser(userExtend);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return fail("注册失败，用户名已存在");
//		}
//		logger.info("注册用户成功！");
//		return rb;
//	}
//	
//	@RequestMapping(value = "/yzm/{phone}", method = RequestMethod.POST)
//	private ResultBean yzm(@PathVariable(value = "phone") String phone) {
//		return UserCenterClient.yzm(phone, BusiUserType.USER+"");
//	}
//	/**
//	 * 用户注册
//	 * @param userExtendId 主键id
//	 * @return
//	 */
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	private ResultBean login(@RequestBody  @Valid UserExtend userExtend,BindingResult bindResult) {
//		//字段规则校验
//		if(bindResult.hasFieldErrors("phone") || bindResult.hasFieldErrors("password")){
//			return fail(bindResult.getFieldError().getDefaultMessage());
//		}
//		ResultBean result = UserCenterClient.login(userExtend.getPhone(), userExtend.getPassword(), userExtend.getType()+"",null);
//		return result;
//	}
	
	
	/**
	 * 更新用户信息
	 * @param userExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private ResultBean update(@RequestBody  MebBasic userExtend) {
		userExtend.setId(Integer.valueOf(FontCache.getLoaclUser().getId()));
		userExtend.setUpdatetime(new Date());
		int flag = mebBasicService.update(userExtend);
		return success(flag);
	}
	
	/**
	 * 发布上班信息
	 * @param guestsExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	private ResultBean publish(@RequestBody @Valid UserPublish userPublish,BindingResult bindResult) {
		//字段规则校验
		if(bindResult.hasErrors()){
			return fail(bindResult.getFieldError().getDefaultMessage());
		}
		
		logger.info("查询个人信息成功");
		Integer userid=Integer.valueOf(FontCache.getLoaclUser().getId());
		MebBasic basic = mebBasicService.get(userid);
		if(UserStatus.FORBID.toString().equals(basic.getStatus())){
			return fail("账号已经被禁用，请您联系管理员！");
		}
		if(userPublish.getLatitude()==null || userPublish.getLatitude()==null){
			return fail("无法获取您的位置信息，请重新定位！");
		}
		UserPublish _userPublish=userPublishService.publish(userPublish,basic);
		return userPublish!=null?success(_userPublish):fail("发布信息失败！"); 
	}
	
	/**
	 * 查询当前发布信息对应的商家
	 * @param guestsExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/ordermatch",method = RequestMethod.GET)
	private ResultBean getTenantByMatchId() {
		JwtUserInfo localUser = FontCache.getLoaclUser();
		Integer userId=localUser.getId();
		UserPublish userPublish = userPublishService.get(userId);
		 MebBasic userBasic = mebBasicService.get(userId);
		if(userPublish!=null && userPublish.getTenantPublishId()!=null && UserStatus.isMatch(userBasic.getStatus())){
			Integer matchId=userPublish.getTenantPublishId();
			TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
			if(null!=tenantUserMatch) {
				TenantUserMatchOrgResult tenantUserMatchOrgResult=new TenantUserMatchOrgResult();
				BlockBeanUtils.copyProperties(tenantUserMatch, tenantUserMatchOrgResult);
				MebBasic tenantMeb = mebBasicService.get(tenantUserMatch.getTenantId());
				tenantUserMatchOrgResult.setAddress(tenantMeb.getAddress());
				tenantUserMatchOrgResult.setUsername(tenantMeb.getUsername());
				return success(tenantUserMatchOrgResult);
			}
			
		}
			return fail("无法找到匹配的订单！"); 
	}
	
//	/**
//	 * 查询当前发布信息对应的商家
//	 * @param guestsExtendId 主键id
//	 * @return
//	 */
//	@RequestMapping(value = "/tenant/{matchId}", method = RequestMethod.POST)
//	private ResultBean getPublicByMatchId(@PathVariable(value = "matchId") Integer matchId) {
//		logger.info("获取匹配的id");
//		TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
//		MebBasic guestsExtend = mebBasicService.get(tenantUserMatch.getTenantId());
//		return success(guestsExtend);
//	}
	
	/**
	 * 下班
	 * @param guestsExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/down", method = RequestMethod.POST)
	private ResultBean down() {
		logger.info("下班");
		Integer userId = FontCache.getLoaclUser().getId();
//		UserPublish userPublish = userPublishService.get(userId);
//		if(userPublish==null){
//			logger.info("没有发布匹配的信息，直接下班");  
//		}
//		else{
//			Integer matchId=userPublish.getTenantPublishId();
//			TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
//			if(tenantUserMatch!=null){
//				Integer status=tenantUserMatch.getStatus();
//					if (status.equals(TenantMatchStatus.USER_ACCEPT)||status.equals(TenantMatchStatus.TENANT_ACCEPT)||
//							status.equals(TenantMatchStatus.USER_ARRIVE_NOTICE)||status.equals(TenantMatchStatus.TENANT_ARRIVE_NOTICE)) {
//						return fail("您有未完成的订单，请取消后下班！"); 
//					}
//				return fail("找不到取消的订单！"); 
//			}
//		}
		mebBasicService.update(userId, "status", UserStatus.OUT_LINE);
		return success(UserStatus.OUT_LINE); 
	}
	
	
	/**
	 * 查看邀请
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/invite/view/{id}", method = RequestMethod.GET)
	protected ResultBean viewInvite(@PathVariable("id") Integer id) { 
		Invite invite = inviteService.get(id);
		//客户主动发起，用户查看
		if(BusiUserType.TENANT.equals(invite.getType())){
			inviteService.update(id, "view", Busi.YES);
		}
		return success("查看成功"); 
	}
	
	/**
	 * 发送邀请
	 * @param guestsExtendId 商户的发布信息的主键
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/invite/{publishId}", method = RequestMethod.POST)
	private ResultBean invite(@PathVariable(value = "publishId") Integer publishId) throws IOException {
		logger.info("用户发送邀请");
		JwtUserInfo localUser = FontCache.getLoaclUser();
		Integer userid=localUser.getId();
//		MebBasic basic = mebBasicService.get(userid);
//		if(!UserStatus.IN_LINE.toString().equals(basic.getStatus())){
//			return fail("账号已经被禁用，请您联系管理员！");
//		}
		TenantPublish tenantPublish = tenantPublishService.get(publishId);
		if(tenantPublish==null){
			return fail("无法找到招聘信息！"); 
		}
		if(PublishStatus.PUBLISH_FINISH==tenantPublish.getStatus()){
			return fail("此招聘信息已经完成！"); 
		}
		if(PublishStatus.PUBLISH_CANCAL==tenantPublish.getStatus()){
			return fail("此招聘信息已经取消！"); 
		}
		MebBasic basic = mebBasicService.get(userid);
		//发送邀请
		Invite invite=new Invite();
		invite.setStatus(InviteStatus.NORMAL);
		invite.setUserId(userid);
		invite.setType(BusiUserType.USER);
		invite.setTenantId(tenantPublish.getTenantId());
		invite.setPublishId(tenantPublish.getId()); 
		invite.setProfile(basic.getProfile());
		invite.setCreatetime(new Date());
		invite.setName(localUser.getName());
		invite.setRemark("用户发送邀请"); 
		inviteService.add(invite);
		
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.U_INVITE, tenantPublish.getId(), localUser.getId(), localUser.getName(), "用户"+localUser.getName()+" 向您发送了邀请");
		handler.sendMessageToUser(tenantPublish.getTenantId(), msg);
		return success("您已经成功发送邀请！"); 
	}
	
	/**
	 * 用户接受邀请
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/invite/accept/{inviteId}", method = RequestMethod.POST)
	private ResultBean acceptInvite(@PathVariable(value = "inviteId") Integer inviteId) throws IOException {
		logger.info("用户接受邀请");
		//判断邀请状态
		Invite invite = inviteService.get(inviteId);
		if(invite==null || !InviteStatus.NORMAL.equals(invite.getStatus())){
			return fail("无法找到当前邀请或邀请状态不正确！"); 
		}
		
		String errorInfo=userPublishService.addUserToTenantPublish(invite);
		if(StringUtils.isNotBlank(errorInfo))
			return fail(errorInfo); 
		JwtUserInfo localUser = FontCache.getLoaclUser();
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.U_ACCEPT, invite.getPublishId(), localUser.getId(), localUser.getName(), "用户"+localUser.getName()+" 接受了您的邀请");
		handler.sendMessageToUser(invite.getTenantId(), msg);
		return success(UserStatus.IN_LINE_MATCH); 
	}
	
	/**
	 * 用户拒绝邀请
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/invite/reject/{inviteId}", method = RequestMethod.POST)
	private ResultBean rejectInvite(@PathVariable(value = "inviteId") Integer inviteId) throws IOException {
		
		logger.info("用户拒绝邀请");
		//判断邀请状态
		And inviteAnd =new And("id", inviteId,Restrictions.EQ);
		inviteAnd.add("status", InviteStatus.NORMAL, Restrictions.EQ);
		Invite invite = inviteService.get(inviteAnd);
		if(invite==null){
			return fail("无法找到当前邀请或邀请状态不正确！"); 
		}
		//更新邀请状态
		inviteService.update(inviteId, "status", InviteStatus.REJECT);
		
		// 发送推送消息
		JwtUserInfo localUser = FontCache.getLoaclUser();
		PushMessage msg=new PushMessage(PushMessage.U_REJECT, invite.getPublishId(), invite.getUserId(), localUser.getName(),"用户"+ localUser.getName()+" 拒绝了您的邀请");
		handler.sendMessageToUser(invite.getTenantId(), msg);
		return success("拒绝邀请成功！"); 
	}

	
	/**
	 * 取消匹配
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/match/cancel", method = RequestMethod.POST)
	private ResultBean cancelMatch() throws IOException {
		logger.info("用户修改状态");
		Integer userId=FontCache.getLoaclUser().getId();
		UserPublish userPublish = userPublishService.get(userId);
		if(userPublish==null || userPublish.getTenantPublishId()==null){
			return fail("无法找到匹配的订单！"); 
		}
		Integer matchId=userPublish.getTenantPublishId();
		TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
		if(tenantUserMatch==null || !userId.equals(tenantUserMatch.getUserId())){
			logger.info("找不到取消的订单,用户id"+userId+",订单id"+tenantUserMatch.getUserId());
			return fail("找不到取消的订单！"); 
		}
		int status=tenantUserMatch.getStatus();
		if(TenantMatchStatus.USER_ACCEPT==status ||   TenantMatchStatus.TENANT_ACCEPT==status || TenantMatchStatus.USER_ARRIVE_NOTICE==status 
				||TenantMatchStatus.TENANT_ARRIVE_NOTICE==status ||TenantMatchStatus.TENANT_FINISH_NOTICE==status ){
			tenantUserMatchService.removeMatch(tenantUserMatch, true);
			tenantUserMatchLogService.log(matchId,TenantMatchStatus.USER_ARRIVE_NOTICE, "取消订单");
		}
		else{
			return fail("状态异常,无法取消！"); 
		}
		// 发送推送消息
		JwtUserInfo localUser = FontCache.getLoaclUser();
		PushMessage msg=new PushMessage(PushMessage.U_CANCEL, tenantUserMatch.getId(), tenantUserMatch.getUserId(), localUser.getName(), "用户"+localUser.getName()+" 拒绝取消了您的匹配单");
		handler.sendMessageToUser(tenantUserMatch.getTenantId(), msg);
		return success(UserStatus.OUT_LINE); 
	}
	
	/**
	 * 确认到达
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/arrive", method = RequestMethod.POST)
	private ResultBean arrive() throws IOException {
		logger.info("用户确认达到");
		JwtUserInfo localUser = FontCache.getLoaclUser();
		UserPublish userPublish = userPublishService.get(localUser.getId());
		if(userPublish==null || userPublish.getTenantPublishId()==null){
			return fail("无法找到匹配的订单！"); 
		}
		Integer matchId=userPublish.getTenantPublishId();
		TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
		if(tenantUserMatch==null){
			return fail("找不到取消的订单！"); 
		}
		this.mebBasicService.update(localUser.getId(), "status", UserStatus.IN_LINE_TENANT);
		tenantUserMatchService.update(matchId, "status", TenantMatchStatus.USER_ARRIVE_NOTICE);
		tenantUserMatchLogService.log(matchId,TenantMatchStatus.USER_ARRIVE_NOTICE, "确认到达");
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.U_ARRIVE, tenantUserMatch.getId(), tenantUserMatch.getUserId(), localUser.getName(), "用户"+localUser.getName()+" 已经确认到达了您的店铺");
		handler.sendMessageToUser(tenantUserMatch.getTenantId(), msg);
		return success("确认达到成功！"); 
	}
	

	/**
	 * 获取用户的位置和手机号
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/tenantLocation", method = RequestMethod.POST)
	private ResultBean getTenantLoaction() throws IOException {
		logger.info("用户确认达到");
		JwtUserInfo localUser = FontCache.getLoaclUser();
		UserPublish userPublish = userPublishService.get(localUser.getId());
		if(userPublish==null || userPublish.getTenantPublishId()==null){
			return fail("无法找到匹配的订单！"); 
		}
		Integer matchId=userPublish.getTenantPublishId();
		TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
		if(tenantUserMatch==null){
			return fail("找不到取消的订单！"); 
		}
		MebBasic meb = this.mebBasicService.get(tenantUserMatch.getTenantId());
		return success(meb); 
	}
	
	
	/**
	 * 邀请列表
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/invite/page/{offset}/{pageSize}", method = RequestMethod.POST)
	protected ResultBean inviteList(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize,@RequestBody(required=false) InvitePageParms invite) { 
		 Integer userId=Integer.valueOf(FontCache.getLoaclUser().getId());
		 if(invite==null)
		 invite=new InvitePageParms();
		 invite.setUserId(userId);
		 invite.setPageNo(offset);
		 invite.setPageSize(pageSize);
		 PagerModel<InviteResult> data = inviteService.selectUserInvitePageList(invite);
		return success(data); 
	}

	
//	/**
//	 * 商户发布的列表查询
//	 * @return
//	 */
//	@RequestMapping(value = "/telant/page/{offset}/{pageSize}", method = RequestMethod.POST)
//	private ResultBean telantList(@RequestBody  TenantParms tenantParms) {
//		logger.info("查询商户发单的列表");
//		return success("查询列表成功！"); 
//	}
//	
}
