/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 系统设置-SystemConf
 * @date 2017-11-04 15:50:23
 * @version V1.0
 **/
package com.block.module.font.basic.systemconf.service;

import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.systemconf.dao.SystemConfDao;
import com.block.module.font.basic.systemconf.entity.SystemConf;


/**
 * 系统设置Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface SystemConfService  extends BaseService<SystemConf,SystemConfDao>{
	
	/**
	 * 同步服务了类型到内存
	 */
	public void refreshSystemConf();
}
