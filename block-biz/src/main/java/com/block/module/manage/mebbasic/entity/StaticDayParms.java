package com.block.module.manage.mebbasic.entity;

import com.block.core.ibatis.beans.PageParms;
public final class StaticDayParms  extends PageParms {

	
	private String startDate;
	private String endDate;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

 
	
}
