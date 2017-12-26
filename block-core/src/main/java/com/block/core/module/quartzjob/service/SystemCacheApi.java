package com.block.core.module.quartzjob.service;

/**
 * 系统缓存区
 *
 */
public interface SystemCacheApi {

	/**
	 * 初始化
	 */
	void init();

	/**
	 * 刷新
	 */
	void refresh();

	/**
	 * 销毁
	 */
	void destroy();

}
