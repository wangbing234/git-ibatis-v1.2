package com.block.core.redis.baseuser;

public class JwtUserInfo {
	 /**  
     * 
     */ 
    private Integer id;
    /**  
     * 昵称（不是实时更新）
     */ 
    private String name;
    
    /**  
     * 昵称（不是实时更新）
     */ 
    private String openid;
    /**  
     * 系统类型
     */ 
    private Integer systemType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSystemType() {
		return systemType;
	}
	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
    
}
