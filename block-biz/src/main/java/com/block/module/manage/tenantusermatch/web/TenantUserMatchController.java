/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配表-TenantUserMatch
 * @date 2017-11-17 10:37:47
 * @version V1.0
 **/
package com.block.module.manage.tenantusermatch.web;

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
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;
import com.block.module.font.tenant.tenantusermatch.service.TenantUserMatchService;


/**
 * 商户和用户匹配表Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/admin/tenantUserMatch")//域/模块
public class TenantUserMatchController  extends BaseController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入TenantUserMatchService
	@Resource(name="tenantUserMatchServiceTenant")
	TenantUserMatchService tenantUserMatchService;
	 
	
	/**
	 * 分页查询商户和用户匹配表(TenantUserMatch)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param tenantUserMatch TenantUserMatch对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("tenantUserMatch") TenantUserMatch tenantUserMatch) {
			And and =new And("id", tenantUserMatch.getId(),Restrictions.EQ);
		 	and.add("status", tenantUserMatch.getStatus(),Restrictions.EQ);
		 	Order order=new Order();
			order.add("id");
		PagerModel<TenantUserMatch> pageModel = tenantUserMatchService.page(and,order,offset,pageSize);
		logger.info("分页查询商户和用户匹配表(TenantUserMatch)分页对象成功！");
		return success(pageModel);
	}
	 
}
