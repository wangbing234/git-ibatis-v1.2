/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.user.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.block.core.beanutils.CipherUtil;
import com.block.core.domain.Busi;
import com.block.core.domain.SystemCache;
import com.block.core.framework.dto.ResultBean;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.module.systemlog.entity.SystemLogType;
import com.block.core.module.systemlog.service.SystemlogService;
import com.block.core.module.user.dao.UserDao;
import com.block.core.module.user.entity.UserModel;
import com.block.core.module.user.service.UserService;
import com.block.core.redis.LoginMethod;
import com.block.core.redis.UserCenterClient;
import com.block.core.redis.UserType;
import com.block.core.redis.baseuser.UserCenter;


/**
 * Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("userServiceSystem")
public class UserServiceImpl extends BaseCenterServiceImpl<UserModel,UserDao> implements UserService {
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入SystemlogService
	@Resource(name="systemlogServiceSystem")
	SystemlogService systemlogService;
	
	
	public void addUser(UserModel user,UserCenter _user){
		user.setCreatetime(new Date());
		user.setUpdatetime(new Date());
		user.setStatus(Busi.YES);
		UserCenter userModel = SystemCache.getLoaclUser();
		user.setCreateAccount(userModel.getSystemId());
		user.setPassword(CipherUtil.encodePassword(user.getPassword(), user.getUsername()));
		baseDao.add(user);
		systemlogService.log("增加用户", _user.getName()+"增加用户", _user.getSystemId(), SystemLogType.accountType_m);
	}
}
