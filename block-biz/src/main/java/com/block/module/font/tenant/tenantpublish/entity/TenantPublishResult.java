package com.block.module.font.tenant.tenantpublish.entity;

public class TenantPublishResult extends TenantPublish{

	 /**  
     * 昵称
     */ 
    private String nickname;
    
    /**  
     * 头像
     */ 
    private String profile;
    
    /**
     * 手机号
     */
    private String phone;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    
}
