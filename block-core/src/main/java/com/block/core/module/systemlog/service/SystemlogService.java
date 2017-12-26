/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -Systemlog
 * @date 2017-11-16 16:35:13
 * @version V1.0
 **/
package com.block.core.module.systemlog.service;

import com.block.core.ibatis.service.BaseService;
import com.block.core.module.systemlog.dao.SystemlogDao;
import com.block.core.module.systemlog.entity.Systemlog;


/**
 * Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface SystemlogService  extends BaseService<Systemlog,SystemlogDao>{


	/**
	 * 增加日志
	 * 
	 * @return
	 */
	public void log(String title,String content,String account,Integer type);
}
