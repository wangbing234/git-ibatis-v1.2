package com.block.module.font.guest.guestpublish.web;

import com.block.core.ibatis.beans.PageParms;

public class TenantFilterParm extends PageParms{
	//几公里范围
	private Integer range;
	
	//商家经度
	private Float longitude;
	
	//商家纬度
	private Float latitude;
	
	//商家服务类型
	private Integer serviceType;
	
	//销量降序： times
	//信用降序： credit
	//评分降序： opinion
	//距离降序： distance
	private String orderBy;
	
	private Integer sort; //1  降序，-1升序
	
	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
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

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	
	
}
