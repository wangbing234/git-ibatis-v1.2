/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 角色表-Orderment
 * @date 2017-11-21 14:36:18
 * @version V1.0
 **/
package com.block.module.font.basic.orderment.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.orderment.dao.OrdermentDao;
import com.block.module.font.basic.orderment.entity.Orderment;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;


/**
 * 角色表Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface OrdermentService  extends BaseService<Orderment,OrdermentDao>{
	
	/**
	 * 统一处理支付
	 * @return
	 */
	public Map<String, Object> wxPayUserMatch(String openid, HttpServletRequest request,TenantUserMatch tenantUserMatch);
	
	/**
	 * 统一处理支付回调
	 * @return
	 */
	public void wxNotify(HttpServletRequest request,HttpServletResponse response)throws Exception ;
	
	/**
	 * 增加订单信息
	 */
	public Orderment addOrderInfo(float amount,String type,String productName,Integer busiId);
	
	/**
	 * 查询订单信息
	 */
	public Orderment getOrderByBusiType(String type,Integer busiId);
	
	/**
	 * 根据订单号查询订单信息
	 */
	public Boolean updateStatuByNo(String orderNo);
}
