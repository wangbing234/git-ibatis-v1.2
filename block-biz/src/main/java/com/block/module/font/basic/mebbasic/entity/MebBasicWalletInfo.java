package com.block.module.font.basic.mebbasic.entity;

public class MebBasicWalletInfo extends MebBasic{
	 /**  
     * 押金
     */ 
    private Float deposit;
    /**  
     * 
     */ 
    private Float free;
    /**  
     * 
     */ 
    private Float freeze;
    
    /**  
     * 
     */ 
    private String stop;
    
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
    private Integer times;
	public Float getDeposit() {
		return deposit;
	}
	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}
	public Float getFree() {
		return free;
	}
	public void setFree(Float free) {
		this.free = free;
	}
	public Float getFreeze() {
		return freeze;
	}
	public void setFreeze(Float freeze) {
		this.freeze = freeze;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
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
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
    
    
}
