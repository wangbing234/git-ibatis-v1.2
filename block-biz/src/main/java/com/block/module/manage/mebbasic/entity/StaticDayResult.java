package com.block.module.manage.mebbasic.entity;

import com.block.core.ibatis.beans.Po;

public class StaticDayResult extends Po{

	   private String curDay;//当前日期
	   
	   private Integer  userCount;//用户数-
	    
	   private Integer  tenantCount;//商户户数-
	   
	   private Integer  guestCount; //客户户数-
	   
	   private Integer  userOrderCount;//用户订单户数-
	   
	   private Integer  userOrderSuccessCount;//用户成功户数-
	   
	   private Integer  orderSuccessAmount;//当天日成交额-
	   
	   private Integer  guestOrderCount; //客户户数

	   
	public String getCurDay() {
		return curDay;
	}

	public void setCurDay(String curDay) {
		this.curDay = curDay;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getTenantCount() {
		return tenantCount;
	}

	public void setTenantCount(Integer tenantCount) {
		this.tenantCount = tenantCount;
	}

	public Integer getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(Integer guestCount) {
		this.guestCount = guestCount;
	}

	public Integer getUserOrderCount() {
		return userOrderCount;
	}

	public void setUserOrderCount(Integer userOrderCount) {
		this.userOrderCount = userOrderCount;
	}

	public Integer getUserOrderSuccessCount() {
		return userOrderSuccessCount;
	}

	public void setUserOrderSuccessCount(Integer userOrderSuccessCount) {
		this.userOrderSuccessCount = userOrderSuccessCount;
	}

	public Integer getOrderSuccessAmount() {
		return orderSuccessAmount;
	}

	public void setOrderSuccessAmount(Integer orderSuccessAmount) {
		this.orderSuccessAmount = orderSuccessAmount;
	}


	public Integer getGuestOrderCount() {
		return guestOrderCount;
	}

	public void setGuestOrderCount(Integer guestOrderCount) {
		this.guestOrderCount = guestOrderCount;
	}
	   
	   
}
