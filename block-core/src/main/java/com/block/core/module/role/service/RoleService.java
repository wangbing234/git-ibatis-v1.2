/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.role.service;

import com.block.core.ibatis.service.BaseService;
import com.block.core.module.role.dao.RoleDao;
import com.block.core.module.role.entity.RoleModel;


/**
 * Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface RoleService  extends BaseService<RoleModel,RoleDao>{

	public RoleModel getRoleWithPrivilege(Integer id);
}
