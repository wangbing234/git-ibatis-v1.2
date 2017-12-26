package com.block.core.domain;

import com.block.core.redis.baseuser.UserCenter;

public class SystemCache {
	private static ThreadLocal<UserCenter> loaclUser= new ThreadLocal<UserCenter>();
	private static ThreadLocal<String> token= new ThreadLocal<String>();
	public static UserCenter getLoaclUser() {
		return loaclUser.get();
	}

	public static void setLoaclUser(UserCenter user) {
		loaclUser.set(user);
	}

 
	public static String getToken() {
		return token.get();
	}

	public static void setToken(String _token) {
		token.set(_token);
	}
	
}
