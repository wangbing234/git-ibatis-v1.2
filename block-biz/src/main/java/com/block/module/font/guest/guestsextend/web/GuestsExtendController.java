/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 客户表扩展信息表-GuestsExtend
 * @date 2017-11-04 15:45:20
 * @version V1.0
 **/
package com.block.module.font.guest.guestsextend.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.block.core.domain.Busi;
import com.block.core.domain.FontCache;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.order.Order;
import com.block.core.redis.baseuser.JwtUserInfo;
import com.block.module.common.CoreDBCache;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.basic.mebbasic.web.MebBasicController;
import com.block.module.font.basic.socket.entity.PushMessage;
import com.block.module.font.basic.socket.websocket.MyWebSocketHandler;
import com.block.module.font.guest.guestpublish.entity.GuestPublicStatus;
import com.block.module.font.guest.guestpublish.entity.GuestPublish;
import com.block.module.font.guest.guestpublish.service.GuestPublishService;
import com.block.module.font.guest.guestpublish.web.TenantFilterParm;
import com.block.module.font.guest.guestpublish.web.TenantFilterResult;
import com.block.module.font.guest.guestsextend.entity.GuestStatus;
import com.block.module.font.tenant.tenantextend.web.TenantStatus;


/**
 * 客户表扩展信息表Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/guest")//域/模块
public class GuestsExtendController  extends MebBasicController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入GuestPublishService
	@Resource(name="guestPublishServiceGuest")
	GuestPublishService guestPublishService;
	
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
//	
//	@RequestMapping(value = "/yzm/{phone}", method = RequestMethod.POST)
//	private ResultBean yzm(@PathVariable(value = "phone") String phone) {
//		return UserCenterClient.yzm(phone, BusiUserType.GUEST+"");
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
	private ResultBean update(@RequestBody  MebBasic userExtend) {
		userExtend.setId(Integer.valueOf(FontCache.getLoaclUser().getId()));
		userExtend.setUpdatetime(new Date());
		int flag = mebBasicService.update(userExtend);
		return success(flag);
	}
	
	/**
	 * 查询用户信息
	 * @param guestsExtendId 主键id
	 * @return
	 */
	@RequestMapping(value = "/tenantlist/{offset}/{pageSize}", method = RequestMethod.POST)
	private ResultBean tenantlist(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize,@RequestBody(required=false) TenantFilterParm userFilterParm) {
		logger.info("查询商户列表");
		MebBasic basicUser = mebBasicService.get(FontCache.getLoaclUser().getId());
		if(userFilterParm==null)
			userFilterParm=new TenantFilterParm();
		if(basicUser.getLongitude()==null || basicUser.getLatitude()==null)
			return fail("没有您的位置信息，无法查询,请定位您的位置！");
		userFilterParm.setLongitude(basicUser.getLongitude());
		userFilterParm.setLatitude(basicUser.getLatitude());
		userFilterParm.setPageNo(offset);
		userFilterParm.setPageSize(pageSize);
		PagerModel<TenantFilterResult> pageModel = guestPublishService.listTenant(userFilterParm);
		return success(pageModel); 
	}
	
	/**
	 * 查询用户信息
	 * @param guestsExtendId 主键id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	private ResultBean createOrder(HttpServletRequest request,@RequestBody GuestPublish guestPublish) throws IOException {
		logger.info("客户创建订单");
		if(guestPublish.getNumber()==null || guestPublish.getNumber()<=0 || guestPublish.getTenantId()==null
				|| guestPublish.getServiceType()==null){
			return fail("参数不正确！"); 
		}
		JwtUserInfo localUser = FontCache.getLoaclUser();
		Integer userid=Integer.valueOf(localUser.getId());
		MebBasic basic = mebBasicService.get(userid);
		if(GuestStatus.FORBID.toString().equals(basic.getStatus())){
			return fail("账号已经被禁用，请您联系管理员！");
		}
		//检查商家状态
		MebBasic basicUser = mebBasicService.get(guestPublish.getTenantId());
		if(basicUser==null){
			return fail("抱歉，找不到商家！"); 
		}
		if(!TenantStatus.IN_LINE.equals(basicUser.getStatus())){
			return fail("抱歉，商家已经打烊或正在忙碌！"); 
		}
		guestPublish.setTenantName(basicUser.getUsername());
		guestPublish.setTenantPhone(basicUser.getPhone());
		guestPublish.setProfile(basicUser.getProfile());
		guestPublish.setCreatetime(new Date());
		guestPublish.setGuestId(localUser.getId());
		guestPublish.setStatus(GuestPublicStatus.WAIT_PAY);
		
		//客户押金
		Integer guest_pay=CoreDBCache.getInteger("guest_pay");
		Map<String, Object> result =null;
		if(guest_pay>0){
			result = ordermentService.wxPayUserMatch(localUser.getOpenid(), request,null);
			if(result!=null){
				result.put("needPay", Busi.YES_STRING);
				return success(result);
			}
			else{
				return fail("调用支付错误！");
			}
		}
		result=new HashMap<String, Object>();
		result.put("needPay", Busi.NO_STRING);
		guestPublish.setStatus(GuestPublicStatus.WAIT);
		int id = guestPublishService.add(guestPublish);
		// 发送推送消息
		PushMessage msg=new PushMessage(PushMessage.G_ORDER, id, guestPublish.getTenantId(), localUser.getName(), "客户"+localUser.getName()+"预定您你的店面");
		handler.sendMessageToUser(guestPublish.getTenantId(), msg);
		return success(result);  
	}
	
//	/**
//	 * 查询用户信息
//	 * @param guestsExtendId 主键id
//	 * @return
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/cancelOrder/{id}", method = RequestMethod.POST)
//	private ResultBean cancelOrder(@PathVariable(value = "id") Integer id) throws IOException {
//		logger.info("取消订单");
//		GuestPublish guestPublish=guestPublishService.get(id);
//		if(guestPublish.getStatus().equals(GuestPublicStatus.ACCEPT)){
//			// 发送推送消息
//			PushMessage msg=new PushMessage(PushMessage.G_ORDER_CANCEL, id, guestPublish.getTenantId(), FontCache.getLoaclUser().getName(), null);
//			handler.sendMessageToUser(guestPublish.getTenantId(), msg);
//			return success("您已经成功取消了预定成功的订单！");  
//		}
//		
//		return success("取消预定成功！");  
//	}
	
	
	/**
	 * 查询自己发布的预定信息
	 * @param guestsExtendId 主键id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/orders/page/{offset}/{pageSize}", method = RequestMethod.POST)
	private ResultBean getMyOrders(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize,@RequestBody GuestPublish guestPublish) throws IOException {
		Order order=new Order("createtime", Order.DESC);
		PagerModel<GuestPublish> page = guestPublishService.page(null, order, offset, pageSize);
		return success(page);
	}
	
}
