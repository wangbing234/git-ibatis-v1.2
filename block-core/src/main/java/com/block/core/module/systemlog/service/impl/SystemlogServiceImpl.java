/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -Systemlog
 * @date 2017-11-16 16:35:13
 * @version V1.0
 **/
package com.block.core.module.systemlog.service.impl;

import org.springframework.stereotype.Service;

import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.module.systemlog.dao.SystemlogDao;
import com.block.core.module.systemlog.entity.Systemlog;
import com.block.core.module.systemlog.service.SystemlogService;


/**
 * Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("systemlogServiceSystem")
public class SystemlogServiceImpl extends BaseCenterServiceImpl<Systemlog,SystemlogDao> implements SystemlogService {

	@Override
	public void log(String title,String content,String account,Integer type) {
		Systemlog systemlog = new Systemlog();
		systemlog.setTitle(title);
		systemlog.setContent(content);
		systemlog.setAccount(account);
		systemlog.setType(type);
		baseDao.add(systemlog);
	}
}
