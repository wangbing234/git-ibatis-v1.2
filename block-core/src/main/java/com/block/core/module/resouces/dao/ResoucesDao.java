/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */


package com.block.core.module.resouces.dao;
import java.util.List;
import java.util.Map;

import com.block.core.ibatis.dao.BaseDao;
import com.block.core.module.resouces.entity.Resouces;



/**
 *   dao层接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface ResoucesDao extends BaseDao<Resouces>{

	public List<Resouces> selectAuthMenus(Map<String,Object> map);
}
