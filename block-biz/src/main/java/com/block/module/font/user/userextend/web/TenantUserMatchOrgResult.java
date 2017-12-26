package com.block.module.font.user.userextend.web;

import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;

public class TenantUserMatchOrgResult extends TenantUserMatch{
	
	private String username;
	private String address;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
