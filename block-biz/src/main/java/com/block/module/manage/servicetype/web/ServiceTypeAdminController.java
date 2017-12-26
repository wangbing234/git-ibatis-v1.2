/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 服务类型字典表-ServiceType
 * @date 2017-11-17 10:37:47
 * @version V1.0
 **/
package com.block.module.manage.servicetype.web;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.block.core.framework.BaseController;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.ibatis.sql.order.Order;
import com.block.module.font.basic.servicetype.entity.ServiceType;
import com.block.module.font.basic.servicetype.service.ServiceTypeService;


/**
 * 服务类型字典表Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/admin/serviceType")//域/模块
public class ServiceTypeAdminController  extends BaseController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入ServiceTypeService
	@Resource(name="serviceTypeServiceBasic")
	ServiceTypeService serviceTypeService;
	
	/**
	 * 插入服务类型字典表(ServiceType)对象
	 * @param serviceType ServiceType对象
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	private ResultBean insert(@RequestBody  @Valid ServiceType serviceType,BindingResult bindResult) {
		//字段规则校验
		if(null==serviceType.getId()){
			serviceTypeService.add(serviceType);
			logger.info("插入服务类型字典表(ServiceType)对象成功！");
		}
		else
		{
			serviceTypeService.update(serviceType);
			logger.info("插入服务类型字典表(ServiceType)对象成功！");
		}
	
		serviceTypeService.refreshServiceType();
		return success("插入成功！");
	}
	
	/**
	 * 分页查询服务类型字典表(ServiceType)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param serviceType ServiceType对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("serviceType") ServiceType serviceType) {
		Order order=new Order("orderNo");
		And and =new And("name", serviceType.getName(),Restrictions.LIKE);
		PagerModel<ServiceType> pageModel = serviceTypeService.page(and,order,offset,pageSize);
		logger.info("分页查询服务类型字典表(ServiceType)分页对象成功！");
		return success(pageModel);
	}

	/**
	 * 根据id删除对象
	 * @param ids id的数组
	 * @return 修改记录的条数
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ResultBean delete(@PathVariable("id") Integer id){
		int flag = serviceTypeService.del(id);
		logger.info("删除服务类型字典表(ServiceType)对象成功！");
		serviceTypeService.refreshServiceType();
		return  success(flag);
	}
	
}
