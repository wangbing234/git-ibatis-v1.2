package com.block.core.beanutils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class BlockJSONUtils {
	public static JSONObject deepMerge(JSONObject source, JSONObject target) throws Exception {
	{
		JSONObject jsonObj=new JSONObject();
		 for (Object obj: source.keySet()) {
			 String key=obj.toString();
		        Object value = source.get(key);
		        if (null!=value && !"null".equals(value) && !(value instanceof Date) && StringUtils.isNoneBlank(String.valueOf(value))) {
		        	 System.out.println(key +"  "+value); 
		        	 jsonObj.put(key, value);
		        } 
		  }
		 
		 for (Object obj: target.keySet()) {
			 String key=obj.toString();
		        Object value = target.get(key);
		        if (null!=value && !"null".equals(value) && !(value instanceof Date) && StringUtils.isNoneBlank(String.valueOf(value))) {
		        	 System.out.println(key +"  "+value); 
		        	 jsonObj.put(key, value);
		        } 
		  }
		   return jsonObj;
		}
	}
}
