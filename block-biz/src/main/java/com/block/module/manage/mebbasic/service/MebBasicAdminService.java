package com.block.module.manage.mebbasic.service;

import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.mebbasic.dao.MebBasicDao;
import com.block.module.font.basic.mebbasic.entity.MebBasic;
import com.block.module.manage.mebbasic.entity.StaticDayParms;
import com.block.module.manage.mebbasic.entity.StaticDayResult;

public interface MebBasicAdminService extends BaseService<MebBasic,MebBasicDao>{

	/**
	 * 5点执行资金解冻
	 */
	public void executeAtFive();
	

	/**
	 * 执行数据清理
	 */
	public void executeClean();
	

	/**
	 * 执行数据采集
	 */
	public void executeCollectData();
	
	/**
	 * 按日统计信息
	 */
	public PagerModel<StaticDayResult> staticStaticOrderDay(StaticDayParms parm);
}
