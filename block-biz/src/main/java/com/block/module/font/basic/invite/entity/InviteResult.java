package com.block.module.font.basic.invite.entity;

import java.util.Date;

import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.util.date.DateUtil;
import com.block.module.common.enums.CommonManager;

public class InviteResult extends Invite {
    
    private String username;
    
    private String address;
    
    private Float price;
    
    /**  
     * 
     */ 
	@FieldName(name="service_type")
    private Integer serviceType;
	
	/**  
     * 付款类型
     * 1:按小时计费，2，按次计费
     */ 
	@FieldName(name="pay_type")
    private Integer payType;
	
	 /**  
     * 截止时间
     */ 
    private Date deadline;
    
   
    
    /**
     * 手机号
     */
    private String phone;
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
    
    public String getDeadlineString() {
        return DateUtil.formatDateMinute(getDeadline());  
    }
    
    public String getCreatetimeString() {
        return DateUtil.getDateTimeString(createtime);  
    }

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	 
	public String getServiceTypeName()
	{
		return CommonManager.getServiceTypeName(getServiceType());
	}
}
