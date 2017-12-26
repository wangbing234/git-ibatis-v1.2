/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 系统设置-SystemConf
 * @date 2017-11-04 15:50:23
 * @version V1.0
 **/
package com.block.module.font.basic.systemconf.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.module.common.CoreDBCache;
import com.block.module.font.basic.systemconf.dao.SystemConfDao;
import com.block.module.font.basic.systemconf.entity.SystemConf;
import com.block.module.font.basic.systemconf.service.SystemConfService;


/**
 * 系统设置Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("systemConfServiceBasic")
public class SystemConfServiceImpl extends BaseCenterServiceImpl<SystemConf,SystemConfDao> implements SystemConfService {

	@Override
	public void refreshSystemConf() {
		List<SystemConf> systemConfList = this.baseDao.list(null, null);
		CoreDBCache.init(systemConfList);
	}
}
