/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 系统设置-SystemConf
 * @date 2017-11-17 10:37:47
 * @version V1.0
 **/
package com.block.module.manage.systemconf.web;

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
import com.block.module.common.enums.FontCache;
import com.block.module.font.basic.systemconf.entity.SystemConf;
import com.block.module.font.basic.systemconf.service.SystemConfService;


/**
 * 系统设置Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/admin/systemConf")//域/模块
public class SystemConfController  extends BaseController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入SystemConfService
	@Resource(name="systemConfServiceBasic")
	SystemConfService systemConfService;
	
	//注入SystemConfService
	@Resource(name="fontCache")
	FontCache fontCache;
	/**
	 * 查询系统设置(SystemConf)列表
	 *@param systemConf SystemConf对象
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	private ResultBean saveOrUpdate(@RequestBody  SystemConf systemConf) {
	 
		if(systemConf.getId()==null)
		{
			systemConfService.add(systemConf);
			logger.info("插入系统设置(SystemConf)对象成功！");
		}
		else
		{
			systemConfService.update(systemConf);
			logger.info("插入系统设置(SystemConf)对象成功！");
		}
		
		return success("成功");
	}
	
	/**
	 * 分页查询系统设置(SystemConf)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param systemConf SystemConf对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("systemConf") SystemConf systemConf) {
			And and =new And("id", systemConf.getId(),Restrictions.EQ);
		 	and.add("keyName", systemConf.getKeyName(),Restrictions.LIKE);
		 	and.add("value", systemConf.getValue(),Restrictions.EQ);
		 	and.add("remark", systemConf.getRemark(),Restrictions.LIKE);
		PagerModel<SystemConf> pageModel = systemConfService.page(and,null,offset,pageSize);
		logger.info("分页查询系统设置(SystemConf)分页对象成功！");
		return success(pageModel);
	}

	/**
	 * 根据id删除对象
	 * @param ids id的数组
	 * @return 修改记录的条数
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ResultBean delete(@PathVariable("id") Integer id){
		int flag = systemConfService.del(id);
		logger.info("删除服务类型字典表(ServiceType)对象成功！");
		return  success(flag);
	}
	
	/**
	 * 根据id删除对象
	 * @param ids id的数组
	 * @return 修改记录的条数
	 */
	@RequestMapping(value = "/cache/clear", method = RequestMethod.GET)
	public ResultBean clearCache(){
		fontCache.refresh();
		return  success("刷选缓存成功！");
	}
	
}
