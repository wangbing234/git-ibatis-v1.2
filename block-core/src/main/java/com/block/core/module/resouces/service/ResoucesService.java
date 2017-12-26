/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.resouces.service;

import java.util.List;

import com.block.core.ibatis.service.BaseService;
import com.block.core.module.resouces.dao.ResoucesDao;
import com.block.core.module.resouces.entity.Resouces;


/**
 * Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface ResoucesService  extends BaseService<Resouces,ResoucesDao>{

	/**
	 * 获取用户权限菜单
	 * @param token
	 * @return
	 */
	public List<Resouces> getMenus(String token);
}
