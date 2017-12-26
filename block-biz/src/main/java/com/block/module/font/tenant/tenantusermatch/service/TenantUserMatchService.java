/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配表-TenantUserMatch
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantusermatch.service;

import java.io.IOException;
import java.util.List;

import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.mebcomment.entity.MebComment;
import com.block.module.font.tenant.tenantpublish.entity.TenantPublish;
import com.block.module.font.tenant.tenantusermatch.dao.TenantUserMatchDao;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatchParms;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatchResult;


/**
 * 商户和用户匹配表Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface TenantUserMatchService  extends BaseService<TenantUserMatch,TenantUserMatchDao>{
	
	/**
	 * 用户增加到邀请中
	 * @param userId
	 * @param tenantPublish
	 */
	public void addToMatch(Integer  userId, TenantPublish tenantPublish,Boolean isUser);
	
	/**
	 * 取消匹配的订单
	 * @param userId
	 * @param tenantPublish
	 */
	public void removeMatch(TenantUserMatch tenantUserMatch,Boolean isUser);
	
	/**
	 * 付款订单
	 * @param userId
	 * @param tenantPublish
	 */
	public String payMatch(Integer matchId);
	
	/**
	 * 添加评价
	 * @param mebComment
	 */
	public String addCommentToMatch(MebComment mebComment);
	
	
	public List<TenantUserMatchResult> queryTenantUserMatch(TenantUserMatchParms tenantUserMatchParms);
}
