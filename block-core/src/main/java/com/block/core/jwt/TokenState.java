/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */
package com.block.core.jwt;

/**
 * TokenState 用户登录状态
 * @author bing.wang
 * @version 1.0
 * @since 1.0
 * */
 public enum TokenState {  
	 /**
	  * 过期
	  */
	EXPIRED("EXPIRED"),
	/**
	 * 无效(token不合法)
	 */
	INVALID("INVALID"), 
	/**
	 * 有效的
	 */
	VALID("VALID");  
	
    private String  state;  
      
    private TokenState(String state) {  
        this.state = state;  
    }
    
    /**
     * 根据状态字符串获取token状态枚举对象
     * @param tokenState
     * @return
     */
    public static TokenState getTokenState(String tokenState){
    	TokenState[] states=TokenState.values();
    	TokenState ts=null;
    	for (TokenState state : states) {
			if(state.toString().equals(tokenState)){
				ts=state;
				break;
			}
		}
    	return ts;
    }
    public String toString() {
    	return this.state;
    }
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
}  
