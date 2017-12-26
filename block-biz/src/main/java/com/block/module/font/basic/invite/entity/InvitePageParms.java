package com.block.module.font.basic.invite.entity;

import com.block.core.ibatis.beans.PageParms;

public class InvitePageParms extends PageParms{

	 /**  
     * 
     */ 
    private Integer userId;
    /**  
     * 
     */ 
    private Integer tenantId;
    
    private Integer status;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
}
