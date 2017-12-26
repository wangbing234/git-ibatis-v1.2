package com.block.module.font.user.userextend.entity;

public class UserStatus {

	public static Integer FORBID=-1;//禁用
	public static Integer IN_LINE=1;//上班
	public static Integer OUT_LINE=2;//下班状态
	public static Integer IN_LINE_MATCH=3;//上班匹配
	public static Integer IN_LINE_TENANT=5;//上班匹配
	public static Integer BUSY=4;//忙碌
	
	public static Boolean isMatch(Integer status)
	{
		return UserStatus.IN_LINE_MATCH.equals(status) ||  UserStatus.IN_LINE_TENANT.equals(status);
	}
}
