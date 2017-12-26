/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 邀请表-Invite
 * @date 2017-11-06 14:25:12
 * @version V1.0
 **/
package com.block.module.font.basic.invite.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.module.font.basic.invite.dao.InviteDao;

import com.block.module.font.basic.invite.entity.Invite;



/**
 * 邀请表dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("inviteDaoBasic")
public class InviteDaoImpl extends  BaseDaoImpl<Invite> implements InviteDao {

}
