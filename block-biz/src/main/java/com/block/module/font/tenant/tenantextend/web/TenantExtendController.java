/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户扩展信息表-MebBasic
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantextend.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.block.core.domain.Busi;
import com.block.core.domain.FontCache;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.ibatis.sql.order.Order;
import com.block.core.ibatis.util.date.DateUtil;
import com.block.core.redis.baseuser.JwtUserInfo;
import com.block.core.smsinfo.service.impl.SmsInfoServiceImpl;
import com.block.module.common.CoreDBCache;
import com.block.module.common.enums.BusiUserType;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.basic.invite.entity.InvitePageParms;
import com.block.module.font.basic.invite.entity.InviteResult;
import com.block.module.font.basic.invite.entity.InviteStatus;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.basic.mebbasic.web.MebBasicController;
import com.block.module.font.basic.mebcomment.entity.MebComment;
import com.block.module.font.basic.mebcomment.entity.MebCommentType;
import com.block.module.font.basic.servicetype.entity.ServiceType;
import com.block.module.font.basic.servicetype.service.ServiceTypeService;
import com.block.module.font.basic.socket.entity.PushMessage;
import com.block.module.font.basic.socket.websocket.MyWebSocketHandler;
import com.block.module.font.basic.tenantusermatchlog.entity.TenantUserMatchLog;
import com.block.module.font.basic.tenantusermatchlog.service.TenantUserMatchLogService;
import com.block.module.font.guest.guestpublish.entity.GuestPublicStatus;
import com.block.module.font.guest.guestpublish.entity.GuestPublish;
import com.block.module.font.guest.guestpublish.service.GuestPublishService;
import com.block.module.font.tenant.tenantextend.entity.GuestOrderParms;
import com.block.module.font.tenant.tenantextend.entity.GuestOrdersResult;
import com.block.module.font.tenant.tenantextend.entity.PayResultCal;
import com.block.module.font.tenant.tenantextend.entity.TenantExtend;
import com.block.module.font.tenant.tenantpublish.entity.PayType;
import com.block.module.font.tenant.tenantpublish.entity.PublishStatus;
import com.block.module.font.tenant.tenantpublish.entity.TenantPublish;
import com.block.module.font.tenant.tenantpublish.service.TenantPublishService;
import com.block.module.font.tenant.tenantscope.entity.TenantScope;
import com.block.module.font.tenant.tenantscope.service.TenantScopeService;
import com.block.module.font.tenant.tenantusermatch.entity.TenantMatchStatus;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatchParms;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatchResult;
import com.block.module.font.tenant.tenantusermatch.service.TenantUserMatchService;
import com.block.module.font.user.userextend.entity.UserStatus;
import com.block.module.font.user.userpublish.service.UserPublishService;


/**
 * 商户扩展信息表Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/tenant")//域/模块
public class TenantExtendController  extends MebBasicController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入TenantPublishService
	@Resource(name="tenantPublishServiceTenant")
	TenantPublishService tenantPublishService;
	
	//注入TenantUserMatchService
	@Resource(name="tenantUserMatchLogServiceBasic")
	TenantUserMatchLogService tenantUserMatchLogService;
	
	//匹配类
	@Resource(name="tenantUserMatchServiceTenant")
	TenantUserMatchService tenantUserMatchService;
	
	//注入UserPublishService
	@Resource(name="userPublishServiceUser")
	UserPublishService userPublishService;
	
	@Resource(name="tenantScopeServiceTenant")
	TenantScopeService tenantScopeService;
	
	//注入GuestPublishService
	@Resource(name="guestPublishServiceGuest")
	GuestPublishService guestPublishService;
	
	@Resource(name="serviceTypeServiceBasic")
	ServiceTypeService serviceTypeService;
	
	//消息推送类
	@Resource
	MyWebSocketHandler handler;
//	/**
//	 * 插入商户扩展信息表(MebBasic)对象
//	 * @param MebBasic MebBasic对象
//	 * @return
//	 */
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	private ResultBean register(@RequestBody  @Valid TenantExtend user,BindingResult bindResult) {
//		if(bindResult.hasErrors()){
//			return fail(bindResult.getFieldError().getDefaultMessage());
//		}
//		ResultBean rb =null;
//		try {
//			rb =mebBasicService.addUser(user);
//		} catch (Exception e) {
//			return fail("注册失败，用户名已存在");
//		}
//		logger.info("商户用户成功！");
//		return rb;
//	}
	
//	@RequestMapping(value = "/yzm/{phone}", method = RequestMethod.POST)
//	private ResultBean yzm(@PathVariable(value = "phone") String phone) {
//		return UserCenterClient.yzm(phone, BusiUserType.TENANT+"");
//	}
//	
//	/**
//	 * 用户注册
//	 * @param userExtendId 主键id
//	 * @return
//	 */
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	private ResultBean login(@RequestBody  @Valid TenantExtend userExtend,BindingResult bindResult) {
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
	private ResultBean update(@RequestBody  TenantExtend userExtend) {
		if(StringUtils.isBlank(userExtend.getScope())){
			return fail("用户范围不能为空！"); 
		}
		/**  
	     * 
	     */ 
		userExtend.setId(Integer.valueOf(FontCache.getLoaclUser().getId()));
		userExtend.setUpdatetime(new Date());
		userExtend.setStatus(null);
		mebBasicService.updateTenant(userExtend);
		return success("更新信息成功");
	}
	
	/**
	 * 发布上班信息
	 * @param guestsExtendId 主键id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	private ResultBean publish(@RequestBody @Valid TenantPublish tenantPublish,BindingResult bindResult) throws IOException {
		//字段规则校验
		if(bindResult.hasErrors()){
			return fail(bindResult.getFieldError().getDefaultMessage());
		}
		Integer userid=Integer.valueOf(FontCache.getLoaclUser().getId());
		MebBasic basic = mebBasicService.get(userid);
		if(TenantStatus.FORBID.toString().equals(basic.getStatus())){
			return fail("账号已经被禁用，请您联系管理员！");
		}
		if(TenantStatus.VERIFY.toString().equals(basic.getStatus())){
			return fail("你的账号等待管理员审核，开通使用权限！");
		}
		Integer maxNumber=CoreDBCache.getInteger("max_person");
		maxNumber=(maxNumber!=null && maxNumber>0)?maxNumber:0;
		if(tenantPublish.getNeedNumber()>maxNumber || tenantPublish.getNeedNumber()<1){
			return fail("需求人数只能在1至"+maxNumber+"之间");
		}
		if(basic.getLatitude()==null || basic.getLatitude()==null){
			return fail("无法获取您的位置信息，请重新定位！");
		}
		logger.info("查询个人信息成功");
		tenantPublish.setAddress(basic.getAddress());
		tenantPublish.setLatitude(basic.getLatitude());
		tenantPublish.setLongitude(basic.getLongitude());
		tenantPublish.setNumber(0);
		tenantPublish.setCreatetime(new Date());
		TenantPublish dbTenantPublish=tenantPublishService.publish(tenantPublish);
		return dbTenantPublish!=null?success(dbTenantPublish.getId()):fail("发布信息失败！"); 
	}
	
	/**
	 * 商户取消发布信息
	 * @param guestsExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/publish/cancel/{publishId}", method = RequestMethod.POST)
	private ResultBean cancelPublish(@PathVariable(value = "publishId") Integer publishId) {
		logger.info("商户打样");
		//更新当前发布状态
		tenantPublishService.update(publishId, "status", PublishStatus.PUBLISH_CANCAL);
		return success("下线成功！"); 
	}
	
	
	/**
	 * 发送邀请
	 * @param publishId 用户发布的消息
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/invite/{publishId}/{userId}", method = RequestMethod.POST)
	private ResultBean invite(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "publishId") Integer publishId) throws IOException {
		logger.info("商户发送邀请");
		And and =new And("id", userId,Restrictions.EQ);
		and.add("status", UserStatus.IN_LINE, Restrictions.EQ);
		MebBasic mebBasic = mebBasicService.get(and);
		if(mebBasic==null){
			return fail("用户已经下班！"); 
		}
		if(hasInvite(publishId, userId)){
			return fail("已经邀请过次用户，请6小时后重试！"); 
		}
		//发送邀请
		JwtUserInfo loaclUser = FontCache.getLoaclUser();
		Invite invite=new Invite();
		invite.setStatus(InviteStatus.NORMAL);
		invite.setUserId(userId);
		invite.setType(BusiUserType.TENANT);
		invite.setTenantId(Integer.valueOf(loaclUser.getId()));
		invite.setPublishId(publishId); 
		invite.setCreatetime(new Date());
		invite.setProfile(mebBasic.getProfile());
		invite.setName(loaclUser.getName());
		invite.setRemark("商户发送邀请"); 
		inviteService.add(invite);
		
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.T_INVITE, mebBasic.getId(), invite.getTenantId(), loaclUser.getName(), "商家"+loaclUser.getName()+"向你发送了邀请");
		handler.sendMessageToUser(mebBasic.getId(), msg);
		return success("您已经成功发送邀请！"); 
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
		if(BusiUserType.USER.equals(invite.getType())){
			inviteService.update(id, "view", Busi.YES);
		}
		return success("查看成功"); 
	}
	
	
	/**
	 * 拒绝邀请
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/invite/reject/{inviteId}", method = RequestMethod.POST)
	private ResultBean rejectInvite(@PathVariable(value = "inviteId") Integer inviteId) throws IOException {
		logger.info("商户拒绝邀请");
		//判断邀请状态
		And inviteAnd =new And("id", inviteId,Restrictions.EQ);
		inviteAnd.add("status", InviteStatus.NORMAL, Restrictions.EQ);
		Invite invite = inviteService.get(inviteAnd);
		if(invite==null){
			return fail("无法找到当前邀请或邀请状态不正确！"); 
		}
		//更新邀请状态
		inviteService.update(inviteId, "status", InviteStatus.REJECT);
		JwtUserInfo loaclUser = FontCache.getLoaclUser();
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.T_REJECT, invite.getId(), invite.getTenantId(), loaclUser.getName(), "商家"+loaclUser.getName()+"拒绝了您的邀请");
		handler.sendMessageToUser(invite.getUserId(), msg);
		return success("拒绝邀请成功！"); 
	}
	
	/**
	 * 接受邀请
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/invite/accept/{inviteId}", method = RequestMethod.POST)
	private ResultBean acceptInvite(@PathVariable(value = "inviteId") Integer inviteId) throws IOException {
		logger.info("商户接受邀请");
		//查询当前请求
		Invite invite = inviteService.get(inviteId);
		if(invite==null || !invite.getStatus().equals(InviteStatus.NORMAL)){
			return fail("无法找到当前邀请或邀请状态不正确！"); 
		}
		String errorInfo=null;
		try {
			errorInfo = tenantPublishService.addUserToPublish(invite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(StringUtils.isBlank(errorInfo))
			return fail(errorInfo); 
		JwtUserInfo loaclUser = FontCache.getLoaclUser();
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.T_ACCEPT, invite.getId(), invite.getTenantId(), loaclUser.getName(), "商家"+loaclUser.getName()+"接受了您的邀请");
		handler.sendMessageToUser(invite.getUserId(), msg);
		return success("接受用户邀请成功！"); 
	}

	
	/**
	 * tenantUserMatchLogService.add(po);
	 * 开始上班，开始计费
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/arrive/{matchId}", method = RequestMethod.POST)
	private ResultBean arrive(@PathVariable(value = "matchId") Integer matchId) throws IOException {
		logger.info("用户确认达到");
		TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
		if(tenantUserMatch==null){
			return fail("找不到取消的订单！"); 
		}
		JwtUserInfo loaclUser = FontCache.getLoaclUser();
		int matchStatus=tenantUserMatch.getStatus();
		if(matchStatus==TenantMatchStatus.USER_ACCEPT|| matchStatus==TenantMatchStatus.TENANT_ACCEPT 
				||matchStatus==TenantMatchStatus.USER_ARRIVE_NOTICE){
			tenantUserMatchService.update(matchId, "status", TenantMatchStatus.TENANT_ARRIVE_NOTICE);
			tenantUserMatchLogService.log(matchId,TenantMatchStatus.TENANT_ARRIVE_NOTICE, "商家确认上班");
		}
		else{
			return fail("订单状态不正确！"); 
		}
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.T_ARRIVE, tenantUserMatch.getId(), tenantUserMatch.getTenantId(), loaclUser.getName(), "商家"+loaclUser.getName()+"确认了你上班");
		handler.sendMessageToUser(tenantUserMatch.getUserId(), msg);
		return success("确认上班成功！"); 
	}
	
	/**
	 * 计算多少钱
	 * @param tenantUserMatch
	 * @param finishDate
	 * @return
	 */
	private PayResultCal checkMoney(TenantUserMatch tenantUserMatch,Date finishDate){
		And where =new And("matchId", tenantUserMatch.getId(),Restrictions.EQ);
		where.add("status", TenantMatchStatus.TENANT_ARRIVE_NOTICE, Restrictions.EQ);
		TenantUserMatchLog beginLog=tenantUserMatchLogService.get(where);
		PayResultCal payMoney = TenantCalUtil.timeCal(beginLog.getCreatetime(), finishDate, tenantUserMatch.getPriceCal());
		return payMoney;
	}
	
	/**
	 * 付款计算
	 * @param request
	 * @param matchId
	 * @return
	 */
	@RequestMapping(value = "/totalMoney/{matchId}", method = RequestMethod.POST)
	private ResultBean getTotalMoney(HttpServletRequest request,@PathVariable(value = "matchId") Integer matchId) {
		TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
		Integer payType = tenantUserMatch.getPayType();
		float pricePad = tenantUserMatch.getPricePaid()!=null?tenantUserMatch.getPricePaid():0f;
		PayResultCal result = null;
		if (PayType.TIME.equals(payType)) {
			result = checkMoney(tenantUserMatch, tenantUserMatch.getSuretime());
			result.setMoney(result.getMoney()-pricePad);
			float floatPriceCal=tenantUserMatch.getPriceCal();
			result.setMoneyOne((int)floatPriceCal);
		} else {
			result = new PayResultCal();
			result.setMoney(tenantUserMatch.getPriceTotal() - pricePad);
		}
		result.setPayType(tenantUserMatch.getPayType());
		return success(result);
	}
	
	/**
	 * 开始下班，结束计费
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/finish/{matchId}", method = RequestMethod.POST)
	private ResultBean finish(HttpServletRequest request,@PathVariable(value = "matchId") Integer matchId) throws IOException {
		logger.info("商户确认下班，待付款");
		JwtUserInfo loaclUser = FontCache.getLoaclUser();
		TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
		if(tenantUserMatch==null){
			return fail("找不到订单！"); 
		}
		int matchStatus=tenantUserMatch.getStatus();
		float priceCal =0;
		if(matchStatus==TenantMatchStatus.USER_ACCEPT|| matchStatus==TenantMatchStatus.TENANT_ACCEPT 
				||matchStatus==TenantMatchStatus.USER_ARRIVE_NOTICE || matchStatus==TenantMatchStatus.TENANT_ARRIVE_NOTICE){
			TenantPublish tenantPublish = tenantPublishService.get(tenantUserMatch.getTenantPublishId());
			TenantUserMatchLog matchLog = tenantUserMatchLogService.log(matchId,TenantMatchStatus.TENANT_FINISH_NOTICE, "确认下班");
			//按时间收费，计算价格
			if(tenantPublish.getPayType()==PayType.TIME){
				priceCal= checkMoney(tenantUserMatch,matchLog.getCreatetime()).getMoney();
			}
			else{
				priceCal=  tenantUserMatch.getPriceTotal();
			}
			tenantUserMatch.setPriceCal(priceCal);
			String tenant_pay_inline=CoreDBCache.getString("tenant_pay_inline");
			Map<String, Object> map = null;
			Map<String, Object> mapParmsMap=new HashMap<String, Object>(); 
			if(Busi.YES_STRING.equals(tenant_pay_inline)){
				//改为代付款
				mapParmsMap.put("status", TenantMatchStatus.TENANT_FINISH_NOTICE);
				map = ordermentService.wxPayUserMatch(loaclUser.getOpenid(), request, tenantUserMatch);
			}
			else
			{
				String resultStr = this.tenantUserMatchService.payMatch(matchId);
				if(StringUtils.isNoneBlank(resultStr)){
					return fail(resultStr); 
				}
				map=new HashMap<String, Object>();
			}
			mapParmsMap.put("suretime", matchLog.getCreatetime());
			tenantUserMatchService.update(matchId, mapParmsMap);
			map.put("pay", tenant_pay_inline);
			return success(map);
		}
		else{
			return fail("订单状态不正确！"); 
		}
	}
	
	
	
	/**
	 * 开始上班，代付款付费
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/pay/{matchId}", method = RequestMethod.POST)
	private ResultBean pay(HttpServletRequest request,@PathVariable(value = "matchId") Integer matchId) throws IOException {
		logger.info("商户付款接口，待付款");
		TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
		JwtUserInfo loaclUser = FontCache.getLoaclUser();
		Map<String, Object> map = ordermentService.wxPayUserMatch(loaclUser.getOpenid(), request, tenantUserMatch);
		return success(map);
	}
	
	/**
	 * 获取未付款的订单
	 * @return
	 */
	@RequestMapping(value = "/order/{matchId}", method = RequestMethod.POST)
	private ResultBean getPayOrder(@PathVariable(value = "matchId") Integer matchId) {
		return success("获取未付款订单！"); 
	}
	
	/**
	 * 邀请列表
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/invite/page/{offset}/{pageSize}", method = RequestMethod.POST)
	protected ResultBean inviteList(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize) { 
		 Integer userId=Integer.valueOf(FontCache.getLoaclUser().getId());
		 InvitePageParms invite=new InvitePageParms();
		 invite.setTenantId(userId);
		 invite.setPageNo(offset);
		 invite.setPageSize(pageSize);
		 PagerModel<InviteResult> data = inviteService.selectTenantInvitePageList(invite);
		return success(data); 
	}
	
	
	
	
	/**
	 * 商户取消发布信息
	 * @param guestsExtendId 主键id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/match/cancel/{matchId}", method = RequestMethod.POST)
	private ResultBean cancelMatch(@PathVariable(value = "matchId") Integer matchId) throws IOException {
		logger.info("商户打样");
		TenantUserMatch tenantUserMatch = tenantUserMatchService.get(matchId);
		if(tenantUserMatch==null){
			return fail("找不到取消的订单！"); 
		}
		int status=tenantUserMatch.getStatus();
		if(TenantMatchStatus.TENANT_ACCEPT==status || TenantMatchStatus.USER_ARRIVE_NOTICE==status 
				||TenantMatchStatus.TENANT_ARRIVE_NOTICE==status ||TenantMatchStatus.TENANT_FINISH_NOTICE==status ){
			tenantUserMatchService.removeMatch(tenantUserMatch, false);
		}
		else{
			return fail("状态异常,无法取消！"); 
		}
		JwtUserInfo loaclUser = FontCache.getLoaclUser();
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.T_CANCEL, tenantUserMatch.getId(), tenantUserMatch.getTenantId(), loaclUser.getName(), "商家"+loaclUser.getName()+"取消了您的订单");
		handler.sendMessageToUser(tenantUserMatch.getUserId(), msg);
		//更新当前发布状态
		return success("下线成功！"); 
	}
	
	/**
	 * 查询用户信息
	 * @param guestsExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/userlist/page/{offset}/{pageSize}", method = RequestMethod.POST)
	private ResultBean userlist(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize,@RequestBody UserFilterParm userFilterParm) {
		logger.info("查询商户列表");
		MebBasic basicUser = mebBasicService.get(FontCache.getLoaclUser().getId());
		userFilterParm.setLongitude(basicUser.getLongitude());
		userFilterParm.setLatitude(basicUser.getLatitude());
		userFilterParm.setPageNo(offset);
		userFilterParm.setPageSize(pageSize);
		PagerModel<UserFilterResult> pageModel = tenantPublishService.listUser(userFilterParm);
		return success(pageModel); 
	}
	
	/**
	 * 商户匹配的订单
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	private ResultBean comment(@RequestBody  @Valid MebComment mebComment,BindingResult bindResult) {
		//字段规则校验
		if(bindResult.hasErrors()){
			return fail(bindResult.getFieldError().getDefaultMessage());
		}
		mebComment.setCommenter(Integer.valueOf(FontCache.getLoaclUser().getId()));
		//在service实现逻辑
		String result=tenantUserMatchService.addCommentToMatch(mebComment);
		return StringUtils.isNotBlank(result)?fail(result): success("评价成功");
	}
	
	/**
	 * 商家的经营范围列表
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/publish/detail/{id}", method = RequestMethod.POST)
	private ResultBean publishDetail(@PathVariable(value = "id") Integer id) {
		//查询订单
		TenantPublish tPublish = tenantPublishService.get(id);
		if(tPublish==null){
			return fail("找不到订单");
		}
		
		//查询匹配的记录
		TenantUserMatchParms tenantUserMatchParms=new TenantUserMatchParms();
		tenantUserMatchParms.setTenantPublishId(tPublish.getId());
		tenantUserMatchParms.setTenantId(FontCache.getLoaclUser().getId()); 
		List<TenantUserMatchResult> mathc=tenantUserMatchService.queryTenantUserMatch(tenantUserMatchParms);
		
		//查询邀请的记录
		Order order=new Order();
		order.add("id",Order.DESC);
		And where =new And("publishId", tPublish.getId(),Restrictions.EQ);
		where.add("type", BusiUserType.TENANT,Restrictions.EQ);
		List<Invite> inviteList = inviteService.list(where, order);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("publish", tPublish);
		map.put("match", mathc);
		map.put("invite", inviteList);
		return success(map);
	}
	
	/**
	 * 查询正在招聘的当前商户
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/publish/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean publishList(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize,
			@RequestParam(value = "status",required=false) String status) {
		And where =new And("tenantId", FontCache.getLoaclUser().getId(),Restrictions.EQ);
		where.add("status", status,Restrictions.EQ);
		where.add("createtime", DateUtil.offsetDate(new Date(), -1),Restrictions.GE_DATETIME);
		Order order=new Order();
		order.add("createtime",Order.DESC);
		PagerModel<TenantPublish> page = tenantPublishService.page(where, order,offset,pageSize);
		return success(page);
	} 
	
	
	/**
	 * 商家的经营范围列表
	 *@param user User对象
	 * @return
	 */
	@RequestMapping(value = "/scope/list", method = RequestMethod.POST)
	private ResultBean scopeList() {
		And where =new And("userId", FontCache.getLoaclUser().getId(),Restrictions.EQ);
		List<TenantScope> tenantScopeList = tenantScopeService.list(where, null);
		if(null==tenantScopeList)
		{
			return fail("找不到服务类型范围");
		}
		StringBuilder sb=new StringBuilder();
		for (TenantScope tenantScope : tenantScopeList) {
			sb.append(",").append(tenantScope.getScopeId());
		}
		
		And serviceTypeWhere =new And("id",sb.substring(1) ,Restrictions.IN);
		Order order=new Order("orderNo");
		List<ServiceType> list = serviceTypeService.list(serviceTypeWhere, order);
		return success(list); 
	}
	
	/**
	 * 查询客户的预定信息
	 * @param guestsExtendId 主键id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/guest/page/{offset}/{pageSize}", method = RequestMethod.POST)
	private ResultBean getGuestOrders(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize,@RequestBody(required=false) GuestOrderParms guestOrderParms) throws IOException {
		if(guestOrderParms==null){
			guestOrderParms=new GuestOrderParms();
		}
		guestOrderParms.setPageNo(offset);
		guestOrderParms.setPageSize(pageSize);
		guestOrderParms.setTenantId(FontCache.getLoaclUser().getId()); 
		PagerModel<GuestOrdersResult> page = tenantPublishService.listGuestOrders(guestOrderParms);
		return success(page);
	}
	
	/**
	 * 修改客户订单状态
	 * @param guestsExtendId 主键id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/guest/status/{id}/{status}", method = RequestMethod.POST)
	private ResultBean setGuestOrderStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) throws IOException {
		GuestPublish guestPublish = guestPublishService.get(id);
		if(guestPublish==null){
			return fail("找不到订单");
		}
		String successMsg=null;
		JwtUserInfo loaclUser = FontCache.getLoaclUser();
		//接受订单
		 if(GuestPublicStatus.ACCEPT.equals(status)){
			guestPublishService.update(id, "status", status);
			logger.info("您接受了客户的订单，id="+id); 
			successMsg="接受订单成功";
			// 发送推送消息
			PushMessage msg=new PushMessage(PushMessage.T_ACCEPT_ORDER, id, loaclUser.getId(), loaclUser.getName(), "商家接受了订单");
			handler.sendMessageToUser(guestPublish.getId(), msg);
			
			MebBasic user = mebBasicService.get(guestPublish.getGuestId());
			//通知用户取消订单
			if(null!=user.getPhone())
				smsInfoService.sendcode(loaclUser.getId(), user.getPhone(), SmsInfoServiceImpl.smsTplTenantAcceptGuest, BusiUserType.TENANT+"");
		}
		//拒绝订单
		 else if(GuestPublicStatus.REJECT.equals(status)){
			guestPublishService.update(id, "status", status);
			logger.info("您拒绝了客户的订单，id="+id); 
			
			// 发送推送消息
			successMsg="拒绝订单成功";
			PushMessage msg=new PushMessage(PushMessage.T_REJECT_ORDER, id, loaclUser.getId(), loaclUser.getName(), "商家拒绝了订单");
			handler.sendMessageToUser(guestPublish.getId(), msg);
		}
		return success(successMsg);
	}
 
}
