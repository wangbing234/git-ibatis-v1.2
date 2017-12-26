/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.privilege.dao.impl;


import org.springframework.stereotype.Component;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.module.privilege.dao.PrivilegeDao;
import com.block.core.module.privilege.entity.PrivilegeModel;



/**
 *   dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Component("privilegeDaoSystem")
public class PrivilegeDaoImpl extends  BaseDaoImpl<PrivilegeModel> implements PrivilegeDao {

	@Override
	public void deletePrivilege(Integer rid) {
		And and =new And("rid", rid,Restrictions.EQ);
		super.del(and);
	}

}
