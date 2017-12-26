package com.block.core.domain;

import com.block.core.redis.baseuser.JwtUserInfo;

public class FontCache {
	private static ThreadLocal<JwtUserInfo> loaclUser= new ThreadLocal<JwtUserInfo>();
	private static ThreadLocal<String> token= new ThreadLocal<String>();
	public static JwtUserInfo getLoaclUser() {
		return loaclUser.get();
	}

	public static void setLoaclUser(JwtUserInfo user) {
		loaclUser.set(user);
	}

 
	public static String getToken() {
		return token.get();
	}

	public static void setToken(String _token) {
		token.set(_token);
	}
	
}
