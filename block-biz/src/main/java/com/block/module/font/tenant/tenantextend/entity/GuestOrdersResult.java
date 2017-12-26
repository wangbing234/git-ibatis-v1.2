package com.block.module.font.tenant.tenantextend.entity;

import com.block.module.common.enums.CommonManager;
import com.block.module.font.guest.guestpublish.entity.GuestPublish;

public class GuestOrdersResult extends GuestPublish{
	/**  
     * 公司名称
     */ 
    private String username;
    
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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
    
	public String getServiceTypeName()
	{
		return CommonManager.getServiceTypeName(getServiceType());
	}
}
