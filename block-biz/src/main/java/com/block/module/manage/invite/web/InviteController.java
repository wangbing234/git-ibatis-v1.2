/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 邀请表-Invite
 * @date 2017-11-21 14:36:16
 * @version V1.0
 **/
package com.block.module.manage.invite.web;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.block.core.framework.BaseController;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.ibatis.sql.order.Order;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.basic.invite.service.InviteService;


/**
 * 邀请表Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/admin/invite")//域/模块
public class InviteController  extends BaseController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入InviteService
	@Resource(name="inviteServiceBasic")
	InviteService inviteService;
	
	/**
	 * 分页查询邀请表(Invite)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param invite Invite对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("invite") Invite invite) {
			And and =new And("type", invite.getId(),Restrictions.EQ);
		 	and.add("status", invite.getRemark(),Restrictions.EQ);
		 	and.add("userId", invite.getUserId(),Restrictions.EQ);
		 	and.add("tenantId", invite.getTenantId(),Restrictions.EQ);
		 	Order order=new Order();
			order.add("createtime");
		PagerModel<Invite> pageModel = inviteService.page(and,order,offset,pageSize);
		logger.info("分页查询邀请表(Invite)分页对象成功！");
		return success(pageModel);
	}
	 
	
}
