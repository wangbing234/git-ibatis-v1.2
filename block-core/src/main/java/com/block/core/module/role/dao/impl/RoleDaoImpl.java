/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.role.dao.impl;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.core.module.role.dao.RoleDao;
import com.block.core.module.role.entity.RoleModel;



/**
 *   dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("roleDaoSystem")
public class RoleDaoImpl extends  BaseDaoImpl<RoleModel> implements RoleDao {

}
