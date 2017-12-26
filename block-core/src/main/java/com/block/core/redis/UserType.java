package com.block.core.redis;

public enum UserType {
	BLOCK_OA("__S__");
	public String type=null;
	UserType(String type){
        this.type = type ;
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
