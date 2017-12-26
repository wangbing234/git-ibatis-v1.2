package com.block.module.common.enums;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.block.module.font.basic.servicetype.entity.ServiceType;
import com.block.module.font.tenant.tenantpublish.entity.PayType;

public class CommonManager {

	public static List<ServiceType> serviceTypeList;
	
	/**
	 * 通过id获取服务名称
	 * @param serviceTypeId
	 * @return
	 */
	public static String getServiceTypeName(Integer serviceTypeId)
	{
		for (ServiceType serviceType : CommonManager.serviceTypeList) {
			if(serviceType.getId().equals(serviceTypeId)){
				return serviceType.getName();
			}
		}
		return "无";
	}
	
	public static String getPayTypeName(Integer payType)
	{
		if(PayType.RATE.equals(payType)){
			return "按次";
		}
		return "按小时";
	}
	
	/**
	 * 通过服务类型id数组，获取服务类型名称数组
	 * @param serviceIds
	 * @return
	 */
	public static String getServiceTypeByNames(String serviceIds)
	{
		if(StringUtils.isBlank(serviceIds)){
			return "";
		}
		String[] serviceIdArray= serviceIds.split(",");
		StringBuilder sb=new StringBuilder();
		for (String serviceIdStr : serviceIdArray) {
			if(StringUtils.isNotBlank(serviceIdStr))
			{
				for (ServiceType serviceType : CommonManager.serviceTypeList) {
					if(String.valueOf(serviceType.getId()).equals(serviceIdStr)){
						sb.append(",").append(serviceType.getName());
						break;
					}
				}
			}
		}
		return sb.length()>0?sb.substring(1):"";
	}
}
