package com.block.module.font.tenant.tenantusermatch.entity;

public class TenantMatchStatus {
//	1，商家发布信息
//	2，邀请用户来上班  （发短信）
//	3，用户确认过来上班。
//	4，商户确认用户过来上班。（发短信）
//	5，商户确认下班，付款。
	public static Integer USER_ACCEPT=1;//用户接受-
	public static Integer TENANT_ACCEPT=2;//商户接受
	public static Integer USER_ARRIVE_NOTICE=3;//用户确认到达-
	public static Integer TENANT_ARRIVE_NOTICE=4;//商户确认达到（开始计数消费）
	public static Integer TENANT_FINISH_NOTICE=5;//待付款(商户确认完成)
	public static Integer TENANT_PAYD_NOTICE=6;//已经完成，待评价
	public static Integer TENANT_COMMENT=9;//已评价
	
	public static Integer TENANT_CANCEL=7;//商户取消
	public static Integer USER_CANCEL=8;//用户取消-
}
