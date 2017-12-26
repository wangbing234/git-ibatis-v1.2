/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */
package com.block.core.ibatis.sql.where;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.block.core.beanutils.ReflectionUtils;
import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.TempField;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.beans.Pram;
import com.block.core.ibatis.sql.exception.AiyiIdTypeException;

/**
 * Sql工具类
 * @author bing.wang
 * @time 2017年7月21日10:38:15
 * @email test.qq.com
 */
public class SqlUtil<T extends Po> {
	
	protected static Logger logger = LoggerFactory.getLogger(SqlUtil.class);
	/**
	 * 获取字段
	 * @param t
	 * @param fieldName
	 * @return
	 */
	public Field getField(Class<?> t, String fieldName){
		Field[] fields = t.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}
	
	/**
	 * 获取字段参数列表
	 * @param po
	 * @return
	 */
	public List<Pram> getPramListOfSelect(Po po){
		List<Pram> list = new ArrayList<>();
		Class<? extends Po> thisClass = po.getClass();
	    Field[] fields = thisClass.getDeclaredFields();
	    for(Field f : fields){
	    	try {
	    		if (!f.isAnnotationPresent(TempField.class)) {
	    			String fName = f.getName();
	    			String get = getMethodNameOfGet(f);
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
						Pram pram = new Pram(getSqlColumn(fieldName,fName), thisClass.getMethod(get + fName.substring(0, 1).toUpperCase() + fName.substring(1)).invoke(po),"");
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Pram pram = new Pram(getSqlColumn(fieldName,fName), thisClass.getMethod(get + fName.substring(0, 1).toUpperCase() + fName.substring(1)).invoke(po),"");
						list.add(pram);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    }
		return list;
	}
	
	/**
	 * 获取表数据库名称
	 * @param po
	 * @return
	 */
	public String getTableName(Po po){
		Class<? extends Po> c = po.getClass();
		if(c.isAnnotationPresent(TableName.class)){
			return c.getAnnotation(TableName.class).name();
		}else{
			String className = po.getClass().getSimpleName();
			String tName = toTableString(className);
			String poName = tName.substring(tName.length() - 2, tName.length());
			if("po".equals(poName)){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
		
	}
	
	
	/**
	 * 获取参数列表
	 * @param po
	 * @return
	 */
	public List<Pram> getPramList(Class<T> po){
		List<Pram> list = new ArrayList<>();
		Class<? extends Po> thisClass = po;
	    Field[] fields = thisClass.getDeclaredFields();
	    	try {
	    		Object o = thisClass.newInstance();
	    		for(Field f : fields){
		    		if(!f.getName().equalsIgnoreCase("ID") && !f.isAnnotationPresent(TempField.class)){
		    			String fName = f.getName();
		    			
		    			String getf = getMethodNameOfGet(f);
		    			if (f.isAnnotationPresent(FieldName.class)) {
		    				String fieldName = f.getAnnotation(FieldName.class).name();
		    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Pram pram = new Pram(fieldName, getValue,"");
							list.add(pram);
		    			}else{
		    				String fieldName = toTableString(fName);
		    				String upFiledChar=fName.substring(0, 1).toUpperCase();
		    				String getMethodName=getf + upFiledChar + fName.substring(1);
		    				Method get = thisClass.getMethod(getMethodName);
		    				Object getValue = get.invoke(o);
		    				Pram pram = new Pram(fieldName , getValue,"");
							list.add(pram);
						}
		    		}
	    		}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				System.out.println("找不到方法！");
			} 
		return list;
	}
	
	/**
	 * 获取参数列表
	 * @param po
	 * @return
	 */
	public List<Pram> getPramListByMap(Class<T> po,Map<String, Object> map){
		List<Pram> list = new ArrayList<>();
		Class<? extends Po> thisClass = po;
	    Field[] fields = thisClass.getDeclaredFields();
	    	try {
	    		Object o = thisClass.newInstance();
	    		for(Field f : fields){
		    		if(!f.getName().equalsIgnoreCase("ID") && map.containsKey(f.getName()) && !f.isAnnotationPresent(TempField.class)){
		    			String fName = f.getName();
		    			
		    			String getf= getMethodNameOfGet(f);
		    			if (f.isAnnotationPresent(FieldName.class)) {
		    				String fieldName = f.getAnnotation(FieldName.class).name();
		    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Pram pram = new Pram(fieldName, getValue,fName);
							list.add(pram);
		    			}else{
		    				String fieldName = toTableString(fName);
		    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Pram pram = new Pram(fieldName , getValue,fName);
							list.add(pram);
						}
		    		}
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		return list;
	}

	public static String getMethodNameOfGet(Field f) {
		String getf="get";
//		String fieldType = f.getGenericType().toString();
//		if (fieldType.indexOf("boolean") != -1 || fieldType.indexOf("Boolean") != -1) {
//			getf = "is";
//		}
		return getf;
	}
	
	/**
	 * 获取数据库名称
	 * @param po
	 * @param idName
	 * @return
	 */
	public String getDbFiledName(T po,String idName)
	{
		Field f = ReflectionUtils.getDeclaredField(po, idName);
		if(f==null) 
			return "";
		String fieldName="";
		if(f!=null){
			if (f.isAnnotationPresent(FieldName.class)) {
				fieldName = f.getAnnotation(FieldName.class).name();
			}
			else {
				fieldName=f.getName();
			}
		}
		return fieldName;
	}
	
	
	public List<Pram> getPramList(T po,Boolean inculdeNull){
		List<Pram> list = new ArrayList<Pram>();
		Class<? extends Po> thisClass = po.getClass();
		 List<Field> fields = ReflectionUtils.getFields(thisClass);
	    	try {
	    		for(Field f : fields){
//	    			!f.getName().equalsIgnoreCase("ID") &&
		    		if( !f.isAnnotationPresent(TempField.class)){
		    			String fName = f.getName();
		    			String getf = getMethodNameOfGet(f);
		    			String fieldName="";
		    			Object getValue=null;
		    			
		    			if (f.isAnnotationPresent(FieldName.class)) {
		    				fieldName = f.getAnnotation(FieldName.class).name();
		    			}
		    			else {
		    				fieldName=fName;
		    			}
		    			try
		    			{
			    			Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				getValue = get.invoke(po);
		    				if(inculdeNull || (getValue!=null && StringUtils.isNotBlank(String.valueOf(getValue)))){
		    					Pram pram = new Pram(fieldName, getValue,fName);
		    					list.add(pram);
		    				}
		    			}
						catch (NoSuchMethodException e) {
							System.out.println("找不到字段"); 
						} 
	    		}
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		return list;
	}
	
	
	/**
	 *获取select字段列表
	 * @param po
	 * @return
	 */
	public List<Pram> getPramListOfSelect(Class<T> po){
		List<Pram> list = new ArrayList<>();
		Class<? extends Po> thisClass = po;
	    Field[] fields = thisClass.getDeclaredFields();
	    	try {
	    		Object o = thisClass.newInstance();
	    		for(Field f : fields){
	    			if (!f.isAnnotationPresent(TempField.class)) {
	    				String fName = f.getName();
		    			String getf = "get";
		    			String fieldType = f.getGenericType().toString();
		    			try {
				    			if (f.isAnnotationPresent(FieldName.class)) {
				    				String fieldName = f.getAnnotation(FieldName.class).name();
				    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
				    				Object getValue = get.invoke(o);
				    				Pram pram = new Pram(getSqlColumn(fieldName,fName), getValue,"");
									list.add(pram);
				    			}else{
				    				String fieldName = toTableString(fName);
				    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
				    				Object getValue = get.invoke(o);
				    				Pram pram = new Pram(getSqlColumn(fieldName,fName), getValue,"");
									list.add(pram);
								}
		    			} catch (NoSuchMethodException e) {
		    				System.out.println("找不到方法！");
		    			}
					}
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		return list;
	}
	
	/**
	 * 获取sql的选择表字段
	 * @param dbName
	 * @param field
	 * @return
	 */
	private String getSqlColumn(String dbName,String field)
	{
		if(dbName.equalsIgnoreCase(field)){
			return field;
		}
		return dbName + " as " + field;
	}
	
	/**
	 * 获取参数列表
	 * @param po
	 * @return
	 */
	public List<Pram> getPramListByBean(Class<T> po){
		List<Pram> list = new ArrayList<>();
		Class<?> thisClass = po;
	    Field[] fields = thisClass.getDeclaredFields();
	    try {
    		Object o = thisClass.newInstance();
    		for(Field f : fields){
	    		if(!f.getName().equalsIgnoreCase("ID") && !f.isAnnotationPresent(TempField.class)){
	    			String fName = f.getName();
	    			String getf = getMethodNameOfGet(f);
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
	    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Pram pram = new Pram(getSqlColumn(fieldName,fName), getValue,"");
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Pram pram = new Pram(getSqlColumn(fieldName,fName), getValue,"");
						list.add(pram);
					}
	    			
	    		}
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 获取字段对象列表
	 * @param po
	 * @return
	 */
	public List<Pram> getPramListByBeanOfSelect(Class<T> po){
		List<Pram> list = new ArrayList<>();
		Class<?> thisClass = po;
	    Field[] fields = thisClass.getDeclaredFields();
	    try {
    		Object o = thisClass.newInstance();
    		for(Field f : fields){
    			if (!f.isAnnotationPresent(TempField.class)) {
    				String fName = f.getName();
	    			String getf  = getMethodNameOfGet(f);
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
	    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Pram pram = new Pram(getSqlColumn(fieldName,fName), getValue,"");
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Method get = thisClass.getMethod(getf + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Pram pram = new Pram(getSqlColumn(fieldName,fName), getValue,"");
						list.add(pram);
					}
				}
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取表名称
	 * @param po
	 * @return
	 */
	public String getTableName(Class<T> po){
		if(po.isAnnotationPresent(TableName.class)){
			return po.getAnnotation(TableName.class).name();
		}else{
			String tName = toTableString(po.getSimpleName());
			String poName = tName.substring(tName.length() - 2, tName.length());
			if("po".equals(poName)){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
	}
	
	/**
	 * 获取表名称
	 * @param po
	 * @return
	 */
	public String getTableNameByBean(Class<T> po){
		if(po.isAnnotationPresent(TableName.class)){
			return po.getAnnotation(TableName.class).name();
		}else{
			String tName = toTableString(po.getSimpleName());
			if("po".equals(tName.substring(tName.length() - 3, tName.length() - 1))){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
	}
	
	/**
	 * 获取实体类中的某个字段值
	 * @param po
	 * @param fileName
	 * @return
	 */
	public static<T> Serializable getFileValue(Class<T> po, String fileName){
		try {
			Method method = po.getMethod("get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1));
			Object o = po.newInstance();
			Object invoke = method.invoke(o);
			return null == invoke ? null : (Serializable)invoke;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 取字段名
	 * @param po
	 * @param fileName
	 * @return
	 */
	public Serializable getFileValue(Po po, String fileName){
		try {
			Class<? extends Po> cla = po.getClass();
			Method method = cla.getMethod("get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1));
			Object o = po;
			Object invoke = method.invoke(o);
			return null == invoke ? null : (Serializable)invoke;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	
	/**
	 * 反射强制赋给实体
	 * @param po
	 * @param fileName
	 * @param fileValue
	 * @return
	 */
	public static boolean setFileValue(Po po, String fileName, Serializable fileValue){
		Class<? extends Po> thisClass = po.getClass();
		try {
			if ("ID".equalsIgnoreCase(fileName)) {
				try {
					Field field = thisClass.getDeclaredField(fileName);
					String calssName = field.getType().getName();
					if (calssName.equals("int") || calssName.equals("java.lang.Integer")) {
						if (Integer.MAX_VALUE >  new Integer("" + fileValue)) {
							Integer val = new Integer("" + fileValue);
							Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
							method.invoke(po, val);
							return true;
						}else{
							throw new AiyiIdTypeException("ID type is not a corresponding type at " + "set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1) + "\n"
									+ "the will give value type is " + fileValue.getClass().getName() + "\n" 
									+ "the filed type type is " + field.getType().getName());
						}
					}else if(calssName.equals("long") || calssName.equals("java.lang.Long")){
						Long val = new Long("" + fileValue);
						Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
						method.invoke(po, val);
						return true;
					}else{
						Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
						method.invoke(po, fileValue);
						return true;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
			if (null != fileValue) {
				String setMethod="";
				try {
					Method method = null;
					setMethod=getSetMethod(fileName);
					Class<? extends Serializable> fClass = fileValue.getClass();
					if(fClass==Timestamp.class) {
						fClass=Date.class;
					}
					else if(fClass.getName().equals("[B")) {
						fClass=String.class;
						fileValue=new String((byte[])fileValue,"UTF-8");
					}
					method = thisClass.getMethod(setMethod, fClass);
					method.invoke(po, fileValue);
				} catch (NoSuchMethodException e) {
					
					// TODO: handle exception
					try {
						Method method = thisClass.getMethod(setMethod, Boolean.class);
						if (fileValue instanceof Integer) {
							method.invoke(po, (int) fileValue > 0 ? true : false);
						} 
						else if(fileValue instanceof String){
							method.invoke(po,Integer.parseInt(fileValue+"")> 0 ? true : false);
						}
					} catch (NoSuchMethodException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	/**
	 * 设置方法
	 * @param fileName
	 * @return
	 */
	private static String getSetMethod(String fileName)
	{
		return "set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
	}
	
	

	
	
	/**
	 * 获取数据库表
	 * @param text
	 * @return
	 */
	public String toTableString(String text){
		String tName = text.substring(0, 1).toLowerCase();
		for(int i = 1; i < text.length(); i++){
			if(!Character.isLowerCase(text.charAt(i))){
				tName += "_";
			}
			tName += text.substring(i, i + 1);
		}
		return tName.toLowerCase();
	}

	/**
	 * 通过对象获取表名称
	 * @param po
	 * @return
	 */
	public String getTableNameByClazz(Class<? extends Po> po) {
		// TODO Auto-generated method stub
		if(po.isAnnotationPresent(TableName.class)){
			return po.getAnnotation(TableName.class).name();
		}else{
			String tName = toTableString(po.getSimpleName());
			if("po".equals(tName.substring(tName.length() - 3, tName.length() - 1))){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
	}
	
}
