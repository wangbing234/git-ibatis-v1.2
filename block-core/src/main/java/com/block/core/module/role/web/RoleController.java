/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.role.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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

import com.block.core.domain.SystemCache;
import com.block.core.framework.BaseController;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.module.role.entity.RoleModel;
import com.block.core.module.role.service.RoleService;
import com.block.core.module.systemlog.entity.SystemLogType;
import com.block.core.module.systemlog.service.SystemlogService;
import com.block.core.redis.baseuser.UserCenter;


/**
 * Controller
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@RequestMapping("/system/role")//域/模块
@ResponseBody
public class RoleController  extends BaseController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入RoleService
	@Resource(name="roleServiceSystem")
	RoleService roleService;
	
	//注入SystemlogService
	@Resource(name="systemlogServiceSystem")
	SystemlogService systemlogService;
		
	/**
	 * 插入(Role)对象
	 * @param role Role对象
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	private ResultBean insert(@RequestBody @Valid RoleModel role,BindingResult bindResult) {
		//字段规则校验
		if(bindResult.hasErrors()){
			return fail(bindResult.getFieldError().getDefaultMessage());
		}
		UserCenter _user=SystemCache.getLoaclUser();
		int i = roleService.add(role);
		logger.info("插入(Role)对象成功！");
		systemlogService.log("增加角色", _user.getName()+"增加角色"+role.getRoleName(), _user.getSystemId(), SystemLogType.accountType_m);
		return success(i);
	}
	
	/**
	 * 查询(Role)列表
	 *@param role Role对象
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private ResultBean update(@RequestBody RoleModel role) {
		if(null==role.getId()){
			return fail("参数不能为空");
		}
		RoleModel dbRole = roleService.get(role.getId());
		if(dbRole==null){
			return fail("更新角色不存在");
		}
		UserCenter _user=SystemCache.getLoaclUser();
		int flag = roleService.update(role);
		logger.info("插入(Role)对象成功！");
		systemlogService.log("修改角色", _user.getName()+"修改角色"+role.getRoleName(), _user.getSystemId(), SystemLogType.accountType_m);
		return success(flag);
	}
	
	/**
	 * 分页查询(Role)分页对象
	 * @param offset 第几页
	 * @param pageSize 分页大小
	 * @param role Role对象
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@Param("role") RoleModel role) {
		And and =new And("roleName", role.getRoleName(),Restrictions.EQ);
	 	and.add("status", role.getStatus(),Restrictions.EQ);
		PagerModel<RoleModel> pageModel = roleService.page(and,null,offset,pageSize);
		logger.info("分页查询(Role)分页对象成功！");
		return success(pageModel);
	}

	/**
	 * 根据id删除(Role)对象
	 * @param roleId 主键id
	 * @return
	 */
	@RequestMapping(value = "/delete/{roleId}", method = RequestMethod.GET)
	private ResultBean deleteById(@PathVariable("roleId") Integer roleId) {
		RoleModel role = roleService.get(roleId);
		int flag = roleService.del(roleId);
		logger.info("根据id删除(Role)对象成功！");
		UserCenter _user=SystemCache.getLoaclUser();
		systemlogService.log("修改角色", _user.getName()+"修改角色"+role.getRoleName(), _user.getSystemId(), SystemLogType.accountType_m);
		return  success(flag);
	}
	
}
