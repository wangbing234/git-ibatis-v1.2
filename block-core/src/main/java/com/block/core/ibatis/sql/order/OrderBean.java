package com.block.core.ibatis.sql.order;

import com.block.core.ibatis.util.NameCaseUtil;

public class OrderBean {

	private String field;

	private boolean isAsc;

	public OrderBean(String field, boolean isAsc) {
		super();
		this.field = NameCaseUtil.toCamelCase(field);
		this.isAsc = isAsc;
	}

	public String getField() {
		return field;
	}

	public boolean isAsc() {
		return isAsc;
	}

}
