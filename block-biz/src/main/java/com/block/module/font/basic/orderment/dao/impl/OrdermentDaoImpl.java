/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 角色表-Orderment
 * @date 2017-11-21 14:36:18
 * @version V1.0
 **/
package com.block.module.font.basic.orderment.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.module.font.basic.orderment.dao.OrdermentDao;

import com.block.module.font.basic.orderment.entity.Orderment;



/**
 * 角色表dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("ordermentDaoBasic")
public class OrdermentDaoImpl extends  BaseDaoImpl<Orderment> implements OrdermentDao {

}
