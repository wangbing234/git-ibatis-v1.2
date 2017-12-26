package com.block.module.manage.mebbasic.entity;

import com.block.core.ibatis.beans.Po;

public class StaticResultInfo extends Po{
	
	
   private Integer  userRegister;
    
   private Integer  tenantRegister;
   
   private Integer  guestRegister;
    
   private Integer  allGuestOrderCount;
     
   private Double  successUserOrderAmount;
      
   private Double   allUserOrderAmount;
     
   private Integer  allTenantOrderCount;
     
   private Integer   successUserOrderCount;

public Integer getUserRegister() {
	return userRegister;
}

public void setUserRegister(Integer userRegister) {
	this.userRegister = userRegister;
}

public Integer getTenantRegister() {
	return tenantRegister;
}

public void setTenantRegister(Integer tenantRegister) {
	this.tenantRegister = tenantRegister;
}

public Integer getGuestRegister() {
	return guestRegister;
}

public void setGuestRegister(Integer guestRegister) {
	this.guestRegister = guestRegister;
}

public Integer getAllGuestOrderCount() {
	return allGuestOrderCount;
}

public void setAllGuestOrderCount(Integer allGuestOrderCount) {
	this.allGuestOrderCount = allGuestOrderCount;
}

public Double getSuccessUserOrderAmount() {
	return successUserOrderAmount;
}

public void setSuccessUserOrderAmount(Double successUserOrderAmount) {
	this.successUserOrderAmount = successUserOrderAmount;
}

public Double getAllUserOrderAmount() {
	return allUserOrderAmount;
}

public void setAllUserOrderAmount(Double allUserOrderAmount) {
	this.allUserOrderAmount = allUserOrderAmount;
}

public Integer getAllTenantOrderCount() {
	return allTenantOrderCount;
}

public void setAllTenantOrderCount(Integer allTenantOrderCount) {
	this.allTenantOrderCount = allTenantOrderCount;
}

public Integer getSuccessUserOrderCount() {
	return successUserOrderCount;
}

public void setSuccessUserOrderCount(Integer successUserOrderCount) {
	this.successUserOrderCount = successUserOrderCount;
}
   
   
      
}
