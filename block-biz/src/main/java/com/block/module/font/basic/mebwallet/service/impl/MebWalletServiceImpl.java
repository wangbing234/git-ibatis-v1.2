/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公用会员财务表-MebWallet
 * @date 2017-11-04 15:50:22
 * @version V1.0
 **/
package com.block.module.font.basic.mebwallet.service.impl;

import org.springframework.stereotype.Service;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.module.font.basic.mebwallet.service.MebWalletService;
import com.block.module.font.basic.mebwallet.dao.MebWalletDao;
import com.block.module.font.basic.mebwallet.entity.MebWallet;


/**
 * 公用会员财务表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("mebWalletServiceBasic")
public class MebWalletServiceImpl extends BaseCenterServiceImpl<MebWallet,MebWalletDao> implements MebWalletService {

}
