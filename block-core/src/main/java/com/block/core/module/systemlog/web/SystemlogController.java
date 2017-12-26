/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -Systemlog
 * @date 2017-11-16 16:35:13
 * @version V1.0
 **/
package com.block.core.module.systemlog.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
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

import com.block.core.framework.BaseController;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.ibatis.sql.order.Order;

import com.block.core.module.systemlog.service.SystemlogService;
import com.block.core.module.systemlog.entity.Systemlog;


/**
 * Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/system/systemlog")//域/模块
public class SystemlogController  extends BaseController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入SystemlogService
	@Resource(name="systemlogServiceSystem")
	SystemlogService systemlogService;
	
	 
	
	/**
	 * 分页查询(Systemlog)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param systemlog Systemlog对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("systemlog") Systemlog systemlog) {
			And and =new And("title", systemlog.getTitle(),Restrictions.LIKE);
		 	and.add("content", systemlog.getContent(),Restrictions.LIKE);
		 	and.add("account", systemlog.getAccount(),Restrictions.EQ);
		 	and.add("diffAreaLogin", systemlog.getDiffAreaLogin(),Restrictions.EQ);
		 	Order order=new Order();
			order.add("logintime",false);
		PagerModel<Systemlog> pageModel = systemlogService.page(and,order,offset,pageSize);
		logger.info("分页查询(Systemlog)分页对象成功！");
		return success(pageModel);
	}

}
