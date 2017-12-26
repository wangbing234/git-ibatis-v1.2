/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.block.core.ibatis.dao.BaseDao;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.module.privilege.dao.PrivilegeDao;
import com.block.core.module.privilege.entity.PrivilegeModel;
import com.block.core.module.role.dao.RoleDao;
import com.block.core.module.role.entity.RoleModel;
import com.block.core.module.role.service.RoleService;


/**
 *   Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("roleServiceSystem")
public class RoleServiceImpl extends BaseCenterServiceImpl<RoleModel,RoleDao> implements RoleService {

	@Resource(name="privilegeDaoSystem")
	private PrivilegeDao privilegeDao;
	
	@Override
	public int add(RoleModel role) {
		int roleId=this.baseDao.add(role);
		String privileges=role.getPrivileges();
		if(StringUtils.isNotBlank(privileges))
		{
			String[] pArr = privileges.split(",");
			for (int i = 0; i < pArr.length; i++) {
				PrivilegeModel privilege=new PrivilegeModel();
				privilege.setMid(Integer.parseInt(pArr[i]));
				privilege.setRid(roleId);
				privilegeDao.add(privilege);
			}
		}
		return baseDao.add(role);
	}

	@Override
	public int update(RoleModel role) {
		privilegeDao.deletePrivilege(role.getId());
		String[] pArr = role.getPrivileges().split(",");
		for (int i = 0; i < pArr.length; i++) {
			PrivilegeModel privilege=new PrivilegeModel();
			privilege.setMid(Integer.parseInt(pArr[i]));
			privilege.setRid(role.getId());
			privilegeDao.add(privilege);
		}
		return baseDao.update(role);
	}
	
	@Override
	public int del(Serializable id) {
		RoleModel role = baseDao.get(id);
		if(role!=null)
			privilegeDao.deletePrivilege(role.getId());
		return baseDao.del(id);
	}
	
	@Override
	public RoleModel getRoleWithPrivilege(Integer id) {
		RoleModel role = baseDao.get(id);
		And and =new And("roleName", id,Restrictions.EQ);
		List<PrivilegeModel> pList = privilegeDao.list(and,null);
		String pIds="";
		for (PrivilegeModel privilege : pList) {
			pIds+=","+privilege.getMid();
		}
		if(StringUtils.isNotEmpty(pIds))
			role.setPrivileges(pIds.substring(1));
		return role;
	}
	
	public void testJob(){
		System.out.println("=======testJob==="); 
	}
	
	

}
