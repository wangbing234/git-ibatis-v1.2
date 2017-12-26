/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配日志表-TenantUserMatchLog
 * @date 2017-11-06 14:25:13
 * @version V1.0
 **/
package com.block.module.font.basic.tenantusermatchlog.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.module.font.basic.tenantusermatchlog.dao.TenantUserMatchLogDao;

import com.block.module.font.basic.tenantusermatchlog.entity.TenantUserMatchLog;



/**
 * 商户和用户匹配日志表dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("tenantUserMatchLogDaoBasic")
public class TenantUserMatchLogDaoImpl extends  BaseDaoImpl<TenantUserMatchLog> implements TenantUserMatchLogDao {

}
