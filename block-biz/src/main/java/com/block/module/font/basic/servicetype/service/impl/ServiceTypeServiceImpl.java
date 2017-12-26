/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 服务类型字典表-ServiceType
 * @date 2017-11-04 15:50:23
 * @version V1.0
 **/
package com.block.module.font.basic.servicetype.service.impl;

import org.springframework.stereotype.Service;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.ibatis.sql.order.Order;
import com.block.module.font.basic.servicetype.service.ServiceTypeService;
import com.block.module.common.enums.CommonManager;
import com.block.module.font.basic.servicetype.dao.ServiceTypeDao;
import com.block.module.font.basic.servicetype.entity.ServiceType;


/**
 * 服务类型字典表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("serviceTypeServiceBasic")
public class ServiceTypeServiceImpl extends BaseCenterServiceImpl<ServiceType,ServiceTypeDao> implements ServiceTypeService {
	
	public void refreshServiceType()
	{
		Order order=new Order("orderNo");
		CommonManager.serviceTypeList=this.baseDao.list(null, order);
	}
}
