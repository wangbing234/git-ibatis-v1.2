/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.resouces.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.core.module.resouces.dao.ResoucesDao;
import com.block.core.module.resouces.entity.Resouces;



/**
 *   dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Component("resoucesDaoSystem")
public class ResoucesDaoImpl extends  BaseDaoImpl<Resouces> implements ResoucesDao {

	@Override
	public List<Resouces> selectAuthMenus(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("ResoucesDaoSystem.selectAuthMenus",map);
	}

}
