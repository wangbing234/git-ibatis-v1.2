/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公关发布信息表-UserPublish
 * @date 2017-11-04 15:45:10
 * @version V1.0
 **/
package com.block.module.font.user.userpublish.service;

import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.invite.entity.Invite;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.user.userpublish.dao.UserPublishDao;
import com.block.module.font.user.userpublish.entity.UserPublish;


/**
 * 公关发布信息表Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface UserPublishService  extends BaseService<UserPublish,UserPublishDao>{

	    /**
	     * 发布服务信息
	     * @param po
	     * @return
	     */
	    public UserPublish publish(UserPublish userPublish,MebBasic basic);
	    
	    /**
	     * 发布服务信息
	     * @param po
	     * @return
	     */
	    public int addUserPublish(UserPublish userPublish);
	    
	    /**
	     * 增加用户到
	     * @param inviteId
	     * @return
	     */
	    public String addUserToTenantPublish(Invite invite);

	
}
