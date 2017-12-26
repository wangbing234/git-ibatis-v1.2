/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 服务类型字典表-ServiceType
 * @date 2017-11-04 15:50:23
 * @version V1.0
 **/
package com.block.module.font.basic.servicetype.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.module.font.basic.servicetype.dao.ServiceTypeDao;

import com.block.module.font.basic.servicetype.entity.ServiceType;



/**
 * 服务类型字典表dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("serviceTypeDaoBasic")
public class ServiceTypeDaoImpl extends  BaseDaoImpl<ServiceType> implements ServiceTypeDao {

}
