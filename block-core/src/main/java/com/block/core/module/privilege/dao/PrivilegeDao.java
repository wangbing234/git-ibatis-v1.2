/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */


package com.block.core.module.privilege.dao;
import com.block.core.ibatis.dao.BaseDao;
import com.block.core.module.privilege.entity.PrivilegeModel;



/**
 *   dao层接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface PrivilegeDao extends BaseDao<PrivilegeModel>{

   public void deletePrivilege(Integer rid);

}
