/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -QuartzJob
 * @date 2017-07-25 18:27:20
 * @version V1.0
 **/
package com.block.core.module.quartzjob.web;

import javax.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
import com.block.core.module.quartzjob.entity.QuartzJob;
import com.block.core.module.quartzjob.service.QuartzJobService;


/**
 * Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/system/quartzJob")//域/模块
public class QuartzJobController  extends BaseController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入QuartzJobService
	@Resource(name="quartzJobServiceSystem")
	QuartzJobService quartzJobService;
	
	/**
	 * 插入(QuartzJob)对象
	 * @param quartzJob QuartzJob对象
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	private ResultBean insert(@RequestBody QuartzJob quartzJob) {
		int i = quartzJobService.add(quartzJob);
		logger.info("插入(QuartzJob)对象成功！");
		return success(i);
	}
	
	/**
	 * 查询(QuartzJob)列表
	 *@param quartzJob QuartzJob对象
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private ResultBean update(@RequestBody QuartzJob quartzJob) {
		int flag = quartzJobService.update(quartzJob);
		logger.info("插入(QuartzJob)对象成功！");
		return success(flag);
	}
	
	/**
	 * 分页查询(QuartzJob)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param quartzJob QuartzJob对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("quartzJob") QuartzJob quartzJob) {
		
			And and =new And("jobDesc", quartzJob.getJobDesc(),Restrictions.EQ);
		 	and.add("beanId", quartzJob.getBeanId(),Restrictions.EQ);
		 	and.add("methodName", quartzJob.getMethodName(),Restrictions.EQ);
		 	and.add("cronExpression", quartzJob.getCronExpression(),Restrictions.EQ);
		 	and.add("isStop", quartzJob.getStop(),Restrictions.EQ);
		 	
		 	Order order=new Order();
			PagerModel<QuartzJob> pageModel = quartzJobService.page(and,order,offset,pageSize);
			logger.info("分页查询(QuartzJob)分页对象成功！");
		return success(pageModel);
	}

	/**
	 * 根据id获取(QuartzJob)对象
	 * @param quartzJobId 主键id
	 * @return
	 */
	@RequestMapping(value = "/getById/{quartzJobId}", method = RequestMethod.GET)
	private ResultBean getById(@PathVariable("quartzJobId") Long quartzJobId) {
		QuartzJob quartzJob = quartzJobService.get(quartzJobId);
		logger.info("根据id获取(QuartzJob)对象成功！");
		return success(quartzJob);
	}
	
	/**
	 * 根据id删除(QuartzJob)对象
	 * @param quartzJobId 主键id
	 * @return
	 */
	@RequestMapping(value = "/delete/{quartzJobId}", method = RequestMethod.GET)
	private ResultBean deleteById(@PathVariable("quartzJobId") Long quartzJobId) {
		int flag = quartzJobService.del(quartzJobId);
		logger.info("根据id删除(QuartzJob)对象成功！");
		return  success(flag);
	}
	
}
