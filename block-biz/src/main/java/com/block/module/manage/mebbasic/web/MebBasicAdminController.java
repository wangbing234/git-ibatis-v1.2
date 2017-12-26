/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -MebBasic
 * @date 2017-11-17 10:37:46
 * @version V1.0
 **/
package com.block.module.manage.mebbasic.web;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;
import com.block.core.framework.BaseController;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.ibatis.sql.order.Order;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.basic.mebbasic.service.MebBasicService;
import com.block.module.font.basic.mebwallet.entity.MebWallet;
import com.block.module.font.basic.mebwallet.service.MebWalletService;
import com.block.module.font.basic.socket.entity.PushMessage;
import com.block.module.font.basic.socket.websocket.MyWebSocketHandler;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;
import com.block.module.font.tenant.tenantusermatch.service.TenantUserMatchService;
import com.block.module.manage.mebbasic.entity.StaticDayParms;
import com.block.module.manage.mebbasic.entity.StaticDayResult;
import com.block.module.manage.mebbasic.entity.StaticResultInfo;
import com.block.module.manage.mebbasic.service.MebBasicAdminService;


/**
 * Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/admin/mebBasic")//域/模块
public class MebBasicAdminController  extends BaseController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入mebWalletService
	@Resource(name="mebBasicAdminService")
	protected MebBasicAdminService mebBasicAdminService;
	
	//注入MebBasicService
	@Resource(name="mebBasicServiceBasic")
	MebBasicService mebBasicService;
	
	//注入mebWalletService
	@Resource(name="mebWalletServiceBasic")
	protected MebWalletService mebWalletService;
	
	//匹配类
	@Resource(name="tenantUserMatchServiceTenant")
	TenantUserMatchService tenantUserMatchService;
	
	//消息推送类
	@Resource
	MyWebSocketHandler handler;
	/**
	 * 查询(MebBasic)列表
	 *@param mebBasic MebBasic对象
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private ResultBean update(@RequestBody  @Valid MebBasic mebBasic,BindingResult bindResult) {
		//字段规则校验
		if(bindResult.hasErrors()){
			return fail(bindResult.getFieldError().getDefaultMessage());
		}
		int flag = mebBasicService.update(mebBasic);
		logger.info("插入(MebBasic)对象成功！");
		return success(flag);
	}
	
	/**
	 * 分页查询(MebBasic)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param mebBasic MebBasic对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("mebBasic") MebBasic mebBasic) {
			And and =new And("id", mebBasic.getId(),Restrictions.EQ);
			 	and.add("nickname", mebBasic.getNickname(),Restrictions.EQ);
			 	and.add("phone", mebBasic.getPhone(),Restrictions.EQ);
			 	and.add("type", mebBasic.getType(),Restrictions.EQ);
			 	and.add("gender", mebBasic.getGender(),Restrictions.EQ);
		 	Order order=new Order();
		 		order.add("updatetime");
				order.add("createtime");
		PagerModel<MebBasic> pageModel = mebBasicService.page(and,order,offset,pageSize);
		logger.info("分页查询(MebBasic)分页对象成功！");
		return success(pageModel);
	}
	
	/**
	 * 分页查询(MebBasic)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param mebBasic MebBasic对象
	 * @return
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	private ResultBean updateStatus(@RequestBody MebBasic mebBasic) {
		  if(null==mebBasic.getId()) {
			  return fail("参数错误");
		  }
	    mebBasicService.update(mebBasic.getId(), "status", mebBasic.getStatus());
		return success("更新成功");
	}
	
	/**
	 * 分页查询(MebBasic)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param mebBasic MebBasic对象
	 * @return
	 */
	@RequestMapping(value = "/walletPage/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean walletPage(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@RequestBody(required=false) MebWallet mebWallet) {
		if(mebWallet==null)
			mebWallet=new MebWallet();
		And and =new And("userId", mebWallet.getId(),Restrictions.EQ);
		PagerModel<MebWallet> pageModel = mebWalletService.page(and,null,offset,pageSize);
		logger.info("分页查询公用会员财务表(MebWallet)分页对象成功！");
		return success(pageModel);
	}
	
	

	/**
	 * 分页查询(MebBasic)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param mebBasic MebBasic对象
	 * @return
	 */
	@RequestMapping(value = "/matchPage/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean matchPage(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@RequestBody(required=false) TenantUserMatch match) {
		if(match==null)
			match=new TenantUserMatch();
		And and =new And("status", match.getStatus(),Restrictions.EQ);
		PagerModel<TenantUserMatch> pageModel = tenantUserMatchService.page(and,null,offset,pageSize);
		logger.info("匹配信息分页对象成功！");
		return success(pageModel);
	}
	
	/**
	 * 分页查询(MebBasic)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param mebBasic MebBasic对象
	 * @return
	 */
	@RequestMapping(value = "/static/list", method = RequestMethod.GET)
	private ResultBean staticOrderAmount() {
		List<Object> rlist = this.mebBasicService.getSqlSessionTemplate().selectList("MebBasicManagerDao.staticOrderAmount");
		logger.info("匹配信息分页对象成功！");
		 Map<String, Object> resultMap=new HashMap<String, Object>();
		 for (Object object : rlist) {
			 Map<String, Object> map=(Map<String, Object>)object;
			 String code = map.get("code").toString();
			 resultMap.put(code,map.get("name"));
		 }
		 	StaticResultInfo staticResultInfo = new StaticResultInfo();
		 	 PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(StaticResultInfo.class);
	     	for (PropertyDescriptor targetPd : targetPds) {
				String name=targetPd.getName();
				try {
					double _double=(Double)resultMap.get(name);
					Object obj=null;
					if(name.equals("successUserOrderAmount") || name.equals("allUserOrderAmount")){
						obj=_double;
					}
					else{
						obj=(int)_double;
					}
					targetPd.getWriteMethod().invoke(staticResultInfo, obj);
				} catch (Exception e) {
					logger.info("字段无法获取值："+name);
				}
	     	}
	     	List<StaticResultInfo> list = new ArrayList<StaticResultInfo>();
	     	list.add(staticResultInfo);
	     	return success(new PagerModel<>(1, list));
	}
	
	/**
	 * 按天统计用户信息
	 * @param offset
	 * @param pageSize
	 * @param parm
	 * @return
	 */
	@RequestMapping(value = "/static/days/{offset}/{pageSize}", method = RequestMethod.POST)
	public ResultBean staticStaticOrderDay(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@RequestBody StaticDayParms parm){
		 if(null==parm){
			 parm=new StaticDayParms();
		 }
		parm.setPageNo(offset);
		parm.setPageSize(pageSize);
		PagerModel<StaticDayResult> pageModel = mebBasicAdminService.staticStaticOrderDay(parm);
		return success(pageModel);
	}
	
	/**
	 * 按天统计用户信息
	 * @param offset
	 * @param pageSize
	 * @param parm
	 * @return
	 */
	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	public ResultBean broadcast(@RequestParam(value = "message") String message,@RequestParam(value = "type") String type){
		PushMessage pushMessage=new PushMessage(type, -1, -1, "系统", message);
		try {
			handler.broadcast(new TextMessage(JSONObject.toJSONString(pushMessage)));
		} catch (IOException e) {
			e.printStackTrace();
			return fail("发送消息失败！");
		}
		return success("发送成功");
	}
}
