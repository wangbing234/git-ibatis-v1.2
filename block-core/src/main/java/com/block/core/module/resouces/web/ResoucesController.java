/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.resouces.web;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.block.core.domain.SystemCache;
import com.block.core.framework.BaseController;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.ibatis.sql.order.Order;
import com.block.core.module.resouces.entity.Resouces;
import com.block.core.module.resouces.service.ResoucesService;
import com.block.core.module.systemlog.entity.SystemLogType;
import com.block.core.module.systemlog.service.SystemlogService;
import com.block.core.module.user.entity.UserModel;
import com.block.core.redis.RedisSevice;
import com.block.core.redis.baseuser.UserCenter;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 *   Controller
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/system/resouces")//域/模块
public class ResoucesController  extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name="resoucesServiceSystem")
	ResoucesService resoucesService;
	
	@Resource(name="redisSevice")
	private RedisSevice redisSevice;
	
	//注入SystemlogService
	@Resource(name="systemlogServiceSystem")
	SystemlogService systemlogService;
	
	/**
	 * 插入(Resouces)对象
	 * @param resouces Resouces对象
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	private ResultBean insert(@RequestBody @Valid Resouces resouces,BindingResult bindResult) {
		if(bindResult.hasErrors()){
			return fail(bindResult.getFieldError().getDefaultMessage());
		}
		UserCenter _user=SystemCache.getLoaclUser();
		int i = resoucesService.add(resouces);
		logger.info("插入(Resouces)对象成功！");
		systemlogService.log("增加资源", _user.getName()+"增加资源"+resouces.getName(), _user.getSystemId(), SystemLogType.accountType_m);
		return success(i);
	}
	
	/**
	 * 查询(Resouces)列表
	 *@param resouces Resouces对象
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private ResultBean update(@RequestBody Resouces resouces) {
		int flag = resoucesService.update(resouces);
		logger.info("插入(Resouces)对象成功！");
		UserCenter _user=SystemCache.getLoaclUser();
		systemlogService.log("修改资源", _user.getName()+"修改资源"+resouces.getName(), _user.getSystemId(), SystemLogType.accountType_m);
		return success(flag);
	}
	
	
	
	/**
	 * 分页查询(Resouces)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param resouces Resouces对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("resouces") Resouces resouces) {
		
			And and =new And("type", resouces.getType(),Restrictions.EQ);
		 	and.add("remarks", resouces.getRemarks(),Restrictions.EQ);
		 	Order order=new Order();
			order.add("orderNum");
			order.add("type");
		PagerModel<Resouces> pageModel = resoucesService.page(and,order,offset,pageSize);
		logger.info("分页查询(Resouces)分页对象成功！");
		return success(pageModel);
	}

	/**
	 * 根据id获取(Resouces)对象
	 * @param resoucesId 主键id
	 * @return
	 */
	@RequestMapping(value = "/getById/{resoucesId}", method = RequestMethod.GET)
	private ResultBean getById(@PathVariable("resoucesId") Long resoucesId) {
		Resouces resouces = resoucesService.get(resoucesId);
		logger.info("根据id获取(Resouces)对象成功！");
		return success(resouces);
	}
	
	
	/**
	 * 根据id删除对象
	 * @param ids id的数组
	 * @return 修改记录的条数
	 */
	@RequestMapping(value = "/deletes", method = RequestMethod.GET)
	public ResultBean deleteByIds(@RequestParam(value = "ids") String[] ids){
		int flag = resoucesService.deleteByIds(ids);
		logger.info("批量删除(Resouces)对象成功！");
		UserCenter _user=SystemCache.getLoaclUser();
		systemlogService.log("删除资源", _user.getName()+"删除资源"+ids, _user.getSystemId(), SystemLogType.accountType_m);
		return  success(flag);
	}
	
	/**
	 * 根据id获取子资源列表
	 * @param resoucesId 资源id
	 * @return
	 */
	@RequestMapping(value = "/getResourceByPid/{resoucesId}", method = RequestMethod.GET)
	private ResultBean getResourceByPid(@PathVariable("resoucesId") Long resoucesId) {
		And and =new And("pid", resoucesId,Restrictions.EQ);
		 List<Resouces> list = resoucesService.list(and, null);
		logger.info("获取字段对id对象成功！");
		return  success(list);
	}
	
	/**
	 * 根据token获取菜单
	 * @param req  请求对象
	 * @return
	 */
	@RequestMapping(value = "/getAuthMenus", method = RequestMethod.GET)
	private ResultBean getAuthMenus() {
		UserCenter user = SystemCache.getLoaclUser();
		List<Resouces> list = resoucesService.getMenus(user.getSystemId()+"");
		logger.info("根据token获取菜单！");
		return  success(list);
	}
	
	/**
	 * 获取所有对外接口注释
	 * @return
	 */
	@RequestMapping(value = "/interfaces", method = RequestMethod.GET)
	private String interfaces() {
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		Map<String, Object> beansWithAnnotationMap = ctx.getBeansWithAnnotation(org.springframework.stereotype.Controller.class);
		Class<? extends Object> clazz = null;
		//跟目录
		JSONArray jsonClassArray=new JSONArray();
		for (Map.Entry<String, Object> entry : beansWithAnnotationMap.entrySet()) {
			clazz = entry.getValue().getClass();// 获取到实例对象的class信息
			Method[] Methods = clazz.getDeclaredMethods();
			JSONObject jsonClass=new JSONObject();
			jsonClass.put("class", clazz.getName());
			JSONArray jsonMethodArray=new JSONArray();
			String classMapping = clazz.getDeclaredAnnotation(RequestMapping.class).value()[0].toString();
			for (Method method : Methods) {
				RequestMapping rm = method.getAnnotation(RequestMapping.class);
				if (rm != null) {
					JSONObject jsonMethod=new JSONObject();
					jsonMethod.put("url", classMapping + rm.value()[0].toString());
					jsonMethod.put("methodName", method.getName());
					jsonMethod.put("method", rm.method()[0].toString());
					StringBuffer sb=new StringBuffer();
					for (Class pClazz : method.getParameterTypes()) {
						if(!pClazz.isAssignableFrom(BindingResult.class)){
							sb.append(",");
							sb.append(pClazz.getSimpleName());
						}
						
					} 
					if(sb.length()>0)
						jsonMethod.put("args", sb.substring(1));
					jsonMethodArray.add(jsonMethod);
				}
			}
			jsonClass.put("mapping", jsonMethodArray);
			jsonClassArray.add(jsonClass);
		}
		return jsonClassArray.toJSONString();
	}
	
}
