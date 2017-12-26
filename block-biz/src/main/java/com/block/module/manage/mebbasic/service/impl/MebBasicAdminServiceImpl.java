/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 系统设置-SystemConf
 * @date 2017-11-04 15:50:23
 * @version V1.0
 **/
package com.block.module.manage.mebbasic.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.block.core.domain.Busi;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.core.ibatis.util.date.DateUtil;
import com.block.module.font.basic.mebbasic.dao.MebBasicDao;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.font.basic.mebwallet.entity.MebWallet;
import com.block.module.font.basic.mebwallet.service.MebWalletService;
import com.block.module.font.basic.mebwalletfreeze.entity.MebWalletFreeze;
import com.block.module.font.basic.mebwalletfreeze.service.MebWalletFreezeService;
import com.block.module.manage.mebbasic.entity.StaticDayParms;
import com.block.module.manage.mebbasic.entity.StaticDayResult;
import com.block.module.manage.mebbasic.service.MebBasicAdminService;


/**
 * 系统设置Controller前端接口类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("mebBasicAdminService")
public class MebBasicAdminServiceImpl  extends BaseCenterServiceImpl<MebBasic,MebBasicDao> implements MebBasicAdminService {
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="mebWalletServiceBasic")
	MebWalletService mebWalletService;
	
	@Resource(name="mebWalletFreezeServiceBasic")
	MebWalletFreezeService mebWalletFreezeService;
	/**
	 * 备注：
	 * 
	 * 每月10号9点15分钟：0 15 9 10 * ?     
	 * 凌晨5点计算任务：    0 0 5 * * ?  每天12点   0 0 12 * * ?
	 * 					   
	 */
	
	
	/**
	 * 执行资金解冻
	 */
	@Override
	@Transactional
	public void executeAtFive() {
		// TODO Auto-generated method stub
		unFreezeMoney();
		
		
		logger.info("定时任务执行完毕"+DateUtil.formatDatetime(new Date()));
	}


	private void unFreezeMoney() {
		Date nowDate=new Date();
		logger.info("定时任务开始执行"+DateUtil.formatDatetime(nowDate));
		
		logger.info("资金解冻"); 
		//查询有解冻资金的用户
		And and =new And("freeze", 0,Restrictions.GT);
		and.add("stop", Busi.NO_STRING,Restrictions.EQ);
		List<MebWallet> walletList = mebWalletService.list(and, null);
		
		if(walletList==null || walletList.isEmpty())
		{
			logger.info("没有需要解冻的资金"); 
			return;
		}
		//查询解冻明细，并且没有被禁止的解冻
		for (MebWallet mebWallet : walletList) {
			And freezeAmomnt =new And("userId", mebWallet.getUserId(),Restrictions.EQ);
			freezeAmomnt.add("freeze", Busi.NO_STRING,Restrictions.EQ);
			freezeAmomnt.add("stop", Busi.NO_STRING,Restrictions.EQ);
			freezeAmomnt.add("freezetime",nowDate ,Restrictions.LE_DATETIME);
			float incrementAmount=mebWallet.getFreeze();
			List<MebWalletFreeze> listFreezeWallet = mebWalletFreezeService.list(freezeAmomnt, null);
			
			if(listFreezeWallet!=null && !listFreezeWallet.isEmpty()){
				for (MebWalletFreeze mebWalletFreeze : listFreezeWallet) {
					incrementAmount-=mebWalletFreeze.getAmount();
					//更新为已经解冻
					mebWalletFreezeService.update(mebWalletFreeze.getId(), "freeze",Busi.YES);
				}
				if(incrementAmount>0){
					mebWalletService.update(mebWallet.getId(), "freeze", incrementAmount);
				}
				else if(incrementAmount<0){
					String errorMsg="资金存在错误！"+mebWallet.getUserId();
					logger.error(errorMsg); 
					throw new RuntimeException(errorMsg);
				}
			}
			
		}
	}
	
	
	@Override
	public PagerModel<StaticDayResult> staticStaticOrderDay(StaticDayParms parm) {
		PagerModel pageList = this.baseDao.selectPageList("MebBasicManagerDao.staticStaticOrderDayList", "MebBasicManagerDao.staticStaticOrderDayCount", parm);
		return pageList;
	}
	
	
	/**
	 * 执行资金解冻
	 */
	@Override
	@Transactional
	public void executeCollectData() {
		// TODO Auto-generated method stub
		Date nowDate=new Date();
		logger.info("定时任务开始执行"+DateUtil.formatDatetime(nowDate));
	 
	}
	
	@Override
	@Transactional
	public void executeClean() {
		// TODO Auto-generated method stub
	}
	
}
