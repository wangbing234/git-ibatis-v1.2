package com.block.module.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.block.module.font.basic.systemconf.entity.SystemConf;

public class CoreDBCache {


	private static Map<String, Object> params;


	private static void putToParms(String key,String value) {
		if (value.indexOf(",")!=-1) { // 包含非数字，视为分隔符
			String[] values = value.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for (String s : values) {
				list.add(Integer.parseInt(s));
			}
			params.put(key, list);
		} 
		else if(value.matches(".*\\D.*"))
		{
			params.put(key, value);
		}
		else { // TODO 纯数字转换为整数类型？
			params.put(key, Integer.parseInt(value));
		}
	}
	
	public static void init(List<SystemConf> list) {
		params=new HashMap<String, Object>();
		 for (SystemConf systemConf : list) {
			 putToParms(systemConf.getKeyName(), systemConf.getValue());
		}
	}

	public static void destroy() {
		params.clear();
	}

	public static Object get(String key) {
		return params.get(key);
	}
	
	public static String  getString(String key) {
		return String.valueOf(params.get(key));
	}
	public static Integer  getInteger(String key) {
		return Integer.valueOf(getString(key));
	}
	public static Double  getDouble(String key) {
		return Double.valueOf(getString(key));
	}
	public static Float  getFloat(String key) {
		return Float.valueOf(getString(key));
	}
	

}
