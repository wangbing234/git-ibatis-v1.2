package com.block.core.ibatis.beans;

public class PageParms {
	private int pageSize = 10;// 每页显示记录数
	private int pageNo=0; // 偏移量
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
}
