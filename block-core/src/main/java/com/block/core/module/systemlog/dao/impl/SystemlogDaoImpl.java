/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -Systemlog
 * @date 2017-11-16 16:35:13
 * @version V1.0
 **/
package com.block.core.module.systemlog.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.core.module.systemlog.dao.SystemlogDao;

import com.block.core.module.systemlog.entity.Systemlog;



/**
 * dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("systemlogDaoSystem")
public class SystemlogDaoImpl extends  BaseDaoImpl<Systemlog> implements SystemlogDao {

}
