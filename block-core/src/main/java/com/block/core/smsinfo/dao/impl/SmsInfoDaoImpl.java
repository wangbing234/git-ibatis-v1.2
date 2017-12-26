/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 短信记录表-SmsInfo
 * @date 2017-10-31 10:20:52
 * @version V1.0
 **/
package com.block.core.smsinfo.dao.impl;


import org.springframework.stereotype.Repository;

import com.block.core.ibatis.dao.impl.BaseDaoImpl;
import com.block.core.smsinfo.dao.SmsInfoDao;
import com.block.core.smsinfo.entity.SmsInfo;



/**
 * 短信记录表dao实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Repository("smsInfoDaoSystem")
public class SmsInfoDaoImpl extends  BaseDaoImpl<SmsInfo> implements SmsInfoDao {

}
