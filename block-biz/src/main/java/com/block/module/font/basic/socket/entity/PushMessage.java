package com.block.module.font.basic.socket.entity;

public class PushMessage {
		
		public static final String  SYSTEM_U="system_u";// 系统公告	(所有用户监听)
		public static final String  SYSTEM_T="system_t";// 系统公告	(所有商户监听)
		public static final String  SYSTEM_G="system_g";// 系统公告	(所有顾客监听)
		public static final String  SYSTEM_A="system";// 系统公告	(所有人)
		
		//商户发出，用户监听
		public static final String  T_INVITE="t_invite";// 商户发送邀请(用户监听)
		public static final String 	T_ACCEPT="t_accept";// 商户接受邀请(用户监听)
		public static final String  T_ARRIVE="t_arrive";// 用户接受邀请(商户监听),开始计费
		public static final String 	T_REJECT="t_reject";// 商户拒绝邀请(用户监听)
		public static final String  T_PAY   ="t_pay";	// 商户成功付款(用户监听)
		public static final String  T_CANCEL="t_cancel";// 商户取消匹配(用户监听)
		
		
		//商户发出，客户监听
		public static final String  T_ACCEPT_ORDER="t_accept_order";// 拒绝订单(客户监听)
		public static final String 	T_REJECT_ORDER="t_reject_order";// 拒绝订单(客户监听)
		public static final String 	T_CANCEL_ORDER="t_cancel_order";// 取消订单(客户监听)
 
		
		//用户发出，商户监听
		public static final String  U_INVITE="u_invite";// 用户发送邀请(商户监听)
		public static final String  U_ACCEPT="u_accept";// 用户接受邀请(商户监听)
		public static final String  U_ARRIVE="u_arrive";// 用户确认达到(商户监听)
		public static final String 	U_REJECT="u_reject";// 用户拒绝邀请(用户监听)
		public static final String  U_CANCEL="u_cancel";// 用户取消匹配(商户监听)
		
		//客户发出，商户监听
		public static final String  G_ORDER="g_order";//  客户发送预定消息(商户监听)
		public static final String  G_ORDER_CANCEL="g_order_cancel";//  客户取消订单
		public static final String  PING="ping";//  心跳检测
		
		
		// 发送者
		private Integer from;
		// 名称
		private String fromName;

		//监听类型public static final String  SYSTEM_U="system_u";// 系统公告	(所有用户监听)
//		public static final String  SYSTEM_T="system_t";// 系统公告	(所有商户监听)
//		public static final String  SYSTEM_G="system_g";// 系统公告	(所有顾客监听)
//		public static final String  SYSTEM_A="system_a";// 系统公告	(所有人)
		private String systemType;
		
		private String type;
		
		/**
		 * 通知的业务id
		 */
		private Integer busiId;
		
		// 名称
		private String remark;
		
		
		
		public PushMessage(String type,Integer busiId,Integer from,String fromName,String remark) {
			this.type=type;
			this.busiId=busiId;
			this.from=from;
			this.fromName=fromName;
			this.remark=remark;
		}

		public Integer getFrom() {
			return from;
		}

		public void setFrom(Integer from) {
			this.from = from;
		}

		public String getFromName() {
			return fromName;
		}

		public void setFromName(String fromName) {
			this.fromName = fromName;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getBusiId() {
			return busiId;
		}

		public void setBusiId(Integer busiId) {
			this.busiId = busiId;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getSystemType() {
			return systemType;
		}

		public void setSystemType(String systemType) {
			this.systemType = systemType;
		}
		
		
}
