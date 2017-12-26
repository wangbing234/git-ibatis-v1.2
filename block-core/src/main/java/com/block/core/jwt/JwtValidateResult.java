package com.block.core.jwt;

public class JwtValidateResult<T> {

	private TokenState state;
	private T data;
	
//	public JwtValidateResult(T t,Boolean isVaild) {
//		this.t=t;
//		this.isVaild=isVaild;
//	}
	
	 
	
	public TokenState getState() {
		return state;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void setState(TokenState state) {
		this.state = state;
	}
	
	
	
}
