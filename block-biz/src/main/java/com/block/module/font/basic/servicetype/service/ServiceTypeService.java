/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 服务类型字典表-ServiceType
 * @date 2017-11-04 15:50:23
 * @version V1.0
 **/
package com.block.module.font.basic.servicetype.service;

import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.servicetype.dao.ServiceTypeDao;
import com.block.module.font.basic.servicetype.entity.ServiceType;


/**
 * 服务类型字典表Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface ServiceTypeService  extends BaseService<ServiceType,ServiceTypeDao>{

	/**
	 * 同步服务了类型到内存
	 */
	public void refreshServiceType();
}
