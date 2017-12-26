package com.block.module.font.basic.socket.entity;
import java.util.Date;
/**
 * 消息类
 * 
 * @author Goofy
 * @Date 2015年6月12日 下午7:32:39
 */
public class Message {
	// 发送者
	public Integer from;
	/**
	 * 发送类型：
	 * 系统公告	  system_u(所有用户监听)
	 * 系统公告	  system_t(所有用户监听)
	 * 系统公告	  system_g(所有用户监听)
	 * 系统公告	  system_a(所有人)
	 * 商户发送邀请t_invite(用户监听)
	 * 商户接受邀请t_accept(用户监听)
	 * 商户成功付款t_pay(用户监听)
	 * 商户取消匹配t_cancel(用户监听)
	 * 用户发送邀请u_invite(商户监听)
	 * 用户接受邀请u_accept(商户监听)
	 * 用户确认达到u_arrive(商户监听)
	 * 用户取消匹配u_cancel(商户监听)
	 */
	public String type;
	// 接收者
	public Integer to;
	// 发送的文本
	public String text;
	// 发送日期
	public Date date;

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
