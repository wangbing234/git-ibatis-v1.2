package com.block.module.font.tenant.tenantextend.web;

import java.util.Calendar;
import java.util.Date;

import com.block.core.ibatis.util.date.DateUtil;
import com.block.module.font.tenant.tenantextend.entity.PayResultCal;

public class TenantCalUtil {

	
	/**
	 * 计算多长时间
	 * @param beginDate
	 * @param endDate
	 * @param price
	 * @return
	 */
	public static PayResultCal timeCal(Date beginDate,Date endDate,float price)
	{
		PayResultCal pc=new PayResultCal();
		int minuteDiff=timeAbs(beginDate, endDate);
		float rount=Math.round(minuteDiff/30);
		float timeCal=rount/2*price;
		pc.setMoney(timeCal);
		pc.setTime(minuteDiff);
		return pc;
	}
	
	/**
	 * 计算多长时间
	 * @param beginDate
	 * @param endDate
	 * @param price
	 * @return
	 */
	public static int timeAbs(Date beginDate,Date endDate)
	{
		return (int)Math.abs(DateUtil.dateDiff(beginDate, endDate));
	}
	
	public static void main(String[] args) {
		Date r = DateUtil.offsetDate(new Date(), -1,Calendar.HOUR_OF_DAY);
		r=DateUtil.offsetDate(r, -40, Calendar.MINUTE);
		System.out.println("计算价格(100元/小时)："+timeCal(r, new Date(), 100));
	}
}
