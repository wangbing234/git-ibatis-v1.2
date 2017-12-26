/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.user.service;

import com.block.core.ibatis.service.BaseService;
import com.block.core.module.user.dao.UserDao;
import com.block.core.module.user.entity.UserModel;
import com.block.core.redis.baseuser.UserCenter;


/**
 * Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface UserService  extends BaseService<UserModel,UserDao>{

	public void addUser(UserModel user,UserCenter _user);
}
