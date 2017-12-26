package com.block.core.ibatis.beans;

import java.util.LinkedList;
import java.util.List;

/**
 * 分页模型，也是所有实体类的基类
 * @author bing.wang
 * @time  
 * @email test.qq.com
 */
public class PagerModel<T extends Po>{
	private int total; // 总数
	private List<T> list; // 分页集合列表
	private int pageSize = 12;// 每页显示记录数
	private int offset; // 偏移量

	public PagerModel() {
		super();
	}
	
	public PagerModel(int total, List<T> list) {
		super();
		this.total = total;
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public List getList() {
		return list == null ? new LinkedList() : list;
	}

	public void setList(List list) {
		this.list = list;
	}
	

	@Override
	public String toString() {
		return "total:" + total + ",list:" + list + ",offset:" + offset;
	}
}
