/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.user.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.core.module.user.dao.UserDao;
import com.block.core.module.user.entity.UserModel;



/**
 * dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("userDaoSystem")
public class UserDaoImpl extends  BaseDaoImpl<UserModel> implements UserDao {

}
