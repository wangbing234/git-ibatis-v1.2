package com.block.module.font.guest.guestpublish.web;

import com.block.core.beanutils.BlockBeanUtils;
import com.block.module.common.enums.CommonManager;
import com.block.module.font.basic.mebbasic.entity.MebBasic;

public class TenantFilterResult  extends MebBasic{
	
	private float distance;
	private String serviceType;
	  /**  
     * 信用分
     */ 
    private Float credit;
    /**  
     * 评分
     */ 
    private Float opinion;
    /**  
     * 服务次数
     */ 
    private Float times;
    
    public String  getDistance() {
		return BlockBeanUtils.formatNumber(distance);
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}


	public String getServiceType() {
		serviceType=CommonManager.getServiceTypeByNames(getWeixin());
		return serviceType;
	}
	 
	public Float getCredit() {
		return credit;
	}
	public void setCredit(Float credit) {
		this.credit = credit;
	}
	public Float getOpinion() {
		return opinion;
	}
	public void setOpinion(Float opinion) {
		this.opinion = opinion;
	}
	public Float getTimes() {
		return times;
	}
	public void setTimes(Float times) {
		this.times = times;
	}
	

	
}
