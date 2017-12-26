package com.block.module.font.tenant.tenantextend.web;

import com.block.core.ibatis.beans.PageParms;

public class UserFilterParm extends PageParms{
	//几公里范围
	private Integer range;
	
	//年龄上限
	private Integer ageUp;
	
	//年龄下限
	private Integer ageDown;
	
	//男性： 1，女性：2，
	private Integer gender;
	
	//商家经度
	private Float longitude;
	
	//商家纬度
	private Float latitude;
	
	//服务次数： times;信用： credit,评分： opinion,距离： distance
	private String orderBy;
	
	private Integer sort; //1  降序，-1升序
	
	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public Integer getAgeUp() {
		return ageUp;
	}

	public void setAgeUp(Integer ageUp) {
		this.ageUp = ageUp;
	}

	public Integer getAgeDown() {
		return ageDown;
	}

	public void setAgeDown(Integer ageDown) {
		this.ageDown = ageDown;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

}
