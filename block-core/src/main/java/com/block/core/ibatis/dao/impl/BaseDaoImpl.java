package com.block.core.ibatis.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.util.StringUtils;

import com.block.core.ibatis.annotation.po.FID;
import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.beans.PageParms;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.beans.Pram;
import com.block.core.ibatis.dao.BaseDao;
import com.block.core.ibatis.sql.builder.MyBatisSql;
import com.block.core.ibatis.sql.criteria.WherePrams;
import com.block.core.ibatis.sql.order.Order;
import com.block.core.ibatis.sql.where.SqlUtil;
import com.block.core.ibatis.util.GenericsUtils;

@SuppressWarnings("unused")
public class BaseDaoImpl<T extends Po> implements BaseDao<T> {

    private static final String SQL_NAME = "__SQL";

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "sqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionTemplate;

    private Class<T> entityClass;

    protected String idName;                  //对应id名称

    private String tableName;

    private List<Pram> sqlParms;

    private List<Pram> selectSqlParms;

    private SqlUtil<T> sqlUtil;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl(){
        super();

        this.sqlUtil = new SqlUtil<T>();

        this.entityClass = (Class<T>) GenericsUtils.getSuperClassGenricType(this.getClass());

        this.sqlParms = this.sqlUtil.getPramList(this.entityClass);

        this.selectSqlParms = this.sqlUtil.getPramListOfSelect(this.entityClass);

        this.tableName = this.sqlUtil.getTableName(this.entityClass);

		this.idName = "id";

		//如果在实体上有注解，使用注解作为ID
		if (entityClass.isAnnotationPresent(FID.class)) {
			String _idName = entityClass.getAnnotation(FID.class).name();
			if (!StringUtils.isEmpty(_idName)) {
				idName = _idName;
			}
		}
	}
    
    @Override
    public int add(T po) {
        // TODO Auto-generated method stub
        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";
        Map<String, Object> parmMap=new HashMap<String, Object>();
        List<Pram> pramList = sqlUtil.getPramList(po,false);
        for (int i = 0; i < pramList.size(); i++) {
        	Object value=pramList.get(i).getValue();
        	String filed=pramList.get(i).getFiled();
        	String file=pramList.get(i).getFile();
        	if(value==null)
        		continue;
            prams += file;
            values += "#{"+filed+"}";
            if(i < pramList.size() -1){
                prams += ",";
                values += ",";
            }
            parmMap.put(filed, value);
        }
        sql += prams + ") value (" + values +")";
        parmMap.put(SQL_NAME, sql);
        int i= sqlSessionTemplate.insert("Dao.add", parmMap);
        if(i==1)  {
        	Object idValue = parmMap.get(idName);
        	if(null!=idValue)
        		return new Integer(idValue.toString());
        	else
        		logger.info("主键必须是id");
        }
        return i;
    }
    
    @Override
    public int del(Serializable id) {
        // TODO Auto-generated method stub
    	Map<String, Object> parmMap=new HashMap<String, Object>();
        String idDbName=getIdDbName();
        String sql = "delete from " + tableName + " where "+idDbName+"=#{"+idName+"}";
        parmMap.put(idName, id);
        parmMap.put(SQL_NAME, sql);
        return sqlSessionTemplate.delete("Dao.deleteById", parmMap);
    }


    @Override
    public int update(T po,Boolean inculdeNull){
    	Map<String, Object> parmMap=new HashMap<String, Object>();
        Serializable id = sqlUtil.getFileValue(po, idName);
        if(null == id){
            return 0;
        }
        StringBuffer sql = new StringBuffer("update ").append(tableName).append(" set ");
        List<Pram> prams = sqlUtil.getPramList(po,inculdeNull);
        for (int i = 0; i < prams.size(); i++) {
        	Object value=prams.get(i).getValue();
        		 String file=prams.get(i).getFile();
            	 String filed=prams.get(i).getFiled();
        		 sql.append(file).append("=");
                 sql.append("#{").append(filed).append("}") ;
                  if (i < prams.size() -1) {
                  	 sql.append(",");
                  }
                  parmMap.put(filed, value);
        }
        sql.append(" where ").append(sqlUtil.getDbFiledName(po,idName)).append("=#{").append(idName).append("}");
        parmMap.put(SQL_NAME, sql);
        return sqlSessionTemplate.update("Dao.update", parmMap);
    }
    
	@Override
	public int update(Serializable id, String fieldName, Object fieldValue,Object... args) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(fieldName, fieldValue);
		if(args!=null && args.length>0){
			for (int i = 0; i < args.length-1; ) {
				map.put(String.valueOf(args[i]), args[i+1]);
				i+=2;
			}
		}
		return this.update(id, map);
	}

	@Override
	public int update(Serializable id, Map<String, Object> map) {
        StringBuffer sql = new StringBuffer("update ").append(tableName).append(" set ");
        List<Pram> prams = sqlUtil.getPramListByMap(this.entityClass,map);
        for (int i = 0; i < prams.size(); i++) {
        	 String filed=prams.get(i).getFiled();
        		 String file=prams.get(i).getFile();
        		 sql.append(file).append("=");
                 sql.append("#{").append(filed).append("}") ;
                  if (i < prams.size() -1) {
                  	 sql.append(",");
                  }
        }
        
        String idDbName=getIdDbName();
        sql.append(" where ").append(idDbName).append("=#{").append(idName).append("}");
        map.put(SQL_NAME, sql);
        map.put(idName, id);
        return sqlSessionTemplate.update("Dao.update", map);
	}
    
    @Override
	public int update(T po) {
		return update(po,false);
	}

    @Override
    public T get(Serializable id) {
        return get(this.entityClass, id);
    }

	@Override
	public T get(Class<T> t, Serializable id) {
		   // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer("select ");
        for (int i = 0; i < selectSqlParms.size(); i++) {
        	sql.append(selectSqlParms.get(i).getFile());
            if(i < selectSqlParms.size() -1){
            	sql.append(",");
            }else{
            	sql.append(" ");
            }
        }
        String idDbName=getIdDbName();
        sql.append(" from ").append(tableName).append(" where ").append(idDbName).append("=#{").append(idName).append("}");
        Map<String, Object> parmMap=new HashMap<String, Object>();
        parmMap.put(SQL_NAME, sql);
        parmMap.put(idName, id);
        Map<String, Object> resultMap = sqlSessionTemplate.selectOne("Dao.getById", parmMap);
        return handleResult(resultMap, t);
	}

    @Override
    public Serializable getField(Serializable id, String fileName) {
        // TODO Auto-generated method stub
        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询字段失败(无法找到" + this.entityClass + "中的" + fileName + "字段)");
            return null;
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null != annotation) {
            tabField = annotation.name() + " as " + fileName;
        }else{
        	tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        }

        String idDbName=getIdDbName();
        String sql = "select ";
        sql += tabField + " from " + tableName + " where "+idDbName+"=#{" + idName + "}";
        
        Map<String, Object> parmMap=new HashMap<String, Object>();
        parmMap.put(SQL_NAME, sql);
        parmMap.put(idName, id);
        Map<String, Object> resultMap = sqlSessionTemplate.selectOne("Dao.getFieldById", parmMap);
        return (Serializable) resultMap.get(fileName);
    }
    
    @Override
    public long nextId(){
        String sql = "SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='" + tableName + "'";
        Long idVal = sqlSessionTemplate.selectOne("fetchSeqNextval", sql);
        if (null == idVal) {
            logger.error("/********************************");
            logger.error("/获取id失败，" + tableName + "表异常。请检查是否存在或者配为自增主键");
            logger.error("/********************************");
            return 0;
        }
        return idVal;
    }

    @Override
    public T get(WherePrams where) {
        // TODO Auto-generated method stub
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        MyBatisSql sqlObj = new MyBatisSql(where);
        sql += "from " + tableName + sqlObj.getSql();
        Map<String, Object> map = sqlObj.getArgMap();
        map.put(SQL_NAME, sql);
        Map<String, Object> resultMap = sqlSessionTemplate.selectOne("Dao.getByParm", map);
        return handleResult(resultMap, this.entityClass);
    }
    
    @Override
    public List<T> list(WherePrams where,Order order) {
        // TODO Auto-generated method stub
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        
        MyBatisSql sqlObj = new MyBatisSql(where);
        Map<String, Object> argMap = sqlObj.getArgMap();
        sql += "from " + tableName + ((argMap==null||argMap.isEmpty())?"":sqlObj.getSql()) +(StringUtils.isEmpty(order)?"":order.toString());
        argMap.put(SQL_NAME, sql);
        List<Map<String, Object>> selectList = sqlSessionTemplate.selectList("Dao.selectList", argMap);
        if(selectList!=null && !selectList.isEmpty()){
        	  List<T> list = new ArrayList<>();
              for (Map<String, Object> map : selectList) {
                  T t = handleResult(map, this.entityClass);
                  list.add(t);
              }
              return list;
        }
        return null;
    }
    
    @Override
    public Integer count(WherePrams where) {
    	 // TODO Auto-generated method stub
        MyBatisSql sqlObj = new MyBatisSql(where);
        Map<String, Object> argMap = sqlObj.getArgMap();
        String sql = "select count(1) from " + tableName + ((argMap==null||argMap.isEmpty())?"":sqlObj.getSql());
        argMap.put(SQL_NAME, sql);
		return sqlSessionTemplate.selectOne("Dao.selectCountByParm", argMap);
	}
    
    
	@Override
	public PagerModel<T> page(WherePrams where, Order order, int offset, int pageSize) {
		MyBatisSql sqlObj = new MyBatisSql(where);
        Map<String, Object> argMap = sqlObj.getArgMap();
        String whereSql=(argMap==null||argMap.isEmpty())?"":sqlObj.getSql();
        String countSql = "select count(1) from " + tableName + whereSql;
        argMap.put(SQL_NAME, countSql);
		int count=sqlSessionTemplate.selectOne("Dao.selectCountByParm", argMap);
		PagerModel<T> pm=null;
		if(count>0l){
			pm=new PagerModel<T>();
			pm.setTotal(count);
			pm.setPageSize(pageSize);
			offset=offset<1?1:offset;
			pm.setOffset(offset);
			 // TODO Auto-generated method stub
	        String sql = "select ";
	        for (int i = 0; i < selectSqlParms.size(); i++) {
	            sql += selectSqlParms.get(i).getFile();
	            if(i < selectSqlParms.size() -1){
	                sql += ",";
	            }else{
	                sql += " ";
	            }
	        }
	        sql += "from " + tableName + whereSql +(StringUtils.isEmpty(order)?"":order.toString())+" limit #{offset},#{pageSize}";
	        argMap.put(SQL_NAME, sql);
	        argMap.put("offset", (offset-1)*pageSize);
	        argMap.put("pageSize", pageSize);
	        List<Map<String, Object>> selectList = sqlSessionTemplate.selectList("Dao.selectList", argMap);
	        if(selectList!=null && !selectList.isEmpty()) {
	        	List<T> list = new ArrayList<T>();
	 	        for (Map<String, Object> map : selectList) {
	 	            T t = handleResult(map, this.entityClass);
	 	            list.add(t);
	 	        }
	 	        pm.setList(list);
	        }
		}
		return pm;
	}

   

    @Override
    public int update(T po, WherePrams where) {
        return update(po, where, false);
    }
    
    public int update(T po, WherePrams where,Boolean updateNull) {
        // TODO Auto-generated method stub
    	List<Pram> prams = sqlUtil.getPramList(po,true);
        String sql = "UPDATE " + tableName + " SET ";
        MyBatisSql sqlObj = new MyBatisSql(where);
        Map<String, Object> map = sqlObj.getArgMap();
        for (int i = 0; i < prams.size(); i++) {
        	String file=prams.get(i).getFile();
        	String field=prams.get(i).getFiled();
        	Object value=prams.get(i).getValue();
            if(null != value){
                sql += file + "=#{" + field+"}";
                if (i < prams.size() -1) {
                    sql += ",";
                }
                map.put(field, value);
            }else{
            	if(updateNull){
            		 sql += file + "= null ";
                     if (i < prams.size() -1) {
                         sql += ",";
                     }
            	}
            }
        }
        sql += sqlObj.getSql();
        map.put(SQL_NAME, sql);
        return sqlSessionTemplate.update("Dao.updateByPram", map);
    }
    
    /** 
     * 更新版本，如果版本不一致则抛出乐观锁异常 
     *  update ACCOUNT set VERSION = VERSION+1 where ACC_ID = #id# AND VERSION=#version#  
     * @param id 
     * @throws SQLException 
     * @throws SQLException 
     */  
    public  void updateVersion(int id,int version)  throws OptimisticLockingFailureException, SQLException {
        Map<String, Object> map=new HashMap<String, Object>(); 
        String idDbName=getIdDbName();
        String sql = "update " + tableName + " set version = version+1 where "+idDbName+"=#{"+idName+"} and version=#{version}";
        map.put(idName, id);  
        map.put("version", version);  
        map.put(SQL_NAME, sql);
        final int count = sqlSessionTemplate.update("Dao.update", map);  
        if (count <= 0) {
            // throw new SQLException("乐观锁异常");  
            throw new OptimisticLockingFailureException("乐观锁异常");//这里把javaee.jar包导入了  
        }
    }
    
    /**
     * 获取数据库的ID值
     * @return
     */
    private String getIdDbName(){
    	 String idDbName=idName;
         try {
         	idDbName=sqlUtil.getDbFiledName(this.entityClass.newInstance(),idName);
 		} catch (InstantiationException | IllegalAccessException e) {
 			// TODO Auto-generated catch block
 			idDbName=idName;
 			e.printStackTrace();
 		}
         return idDbName;
    }


    @Override
    public int del(WherePrams where) {
        // TODO Auto-generated method stub
        MyBatisSql sqlObj = new MyBatisSql(where);
        String sql = "delete from " + tableName +sqlObj.getSql();
        Map<String, Object> map = sqlObj.getArgMap();
        map.put(SQL_NAME, sql);
        return sqlSessionTemplate.delete("Dao.deleteByparm", map);
    }
    

	@Override
	public int deleteByIds(String[] ids) {
		String idDbName=getIdDbName();
		String sql = "delete from " + tableName +" where "+idDbName+" in ";
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put(SQL_NAME, sql);
	    map.put("ids", ids);
		return sqlSessionTemplate.delete("Dao.deleteByIds", map);
	}
  

    /**
	 * 从List<String>集合中检查是否有存在的元素
	 * @param resultMap 对象的map列表
	 * @param tClazz 转换的对象
	 * @return
	 */
    private T handleResult(Map<String, Object> resultMap, Class<T> tClazz) {
        if (null == resultMap) {
            return null;
        }
        T t = null;
        try {
            t = tClazz.newInstance();
        } catch (InstantiationException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        } catch (IllegalAccessException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        }
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            Serializable val = (Serializable) entry.getValue();
            try {
                SqlUtil.setFileValue(t, key, val);
            } catch (Exception e) {
                // TODO: handle exception
                logger.error("/********************************");
                logger.error("/实例化Bean失败(" + this.entityClass + ")不能赋值到字段(" + key + "):"
                        + e.getMessage());
                logger.error("/********************************");
            }
        }
        return t;
    }
    
    /**
	 * 分页查询
	 * @param selectList  记录查询sql
	 * @param selectCount 条数查询sql
	 * @param param
	 * @return
	 */
	public PagerModel selectPageList(String selectList, String selectCount,PageParms param) {
		PagerModel pm = new PagerModel();
		int pageNo=param.getPageNo();
		int pageSize=param.getPageSize();
		param.setPageSize(pageSize);
		param.setPageNo(((pageNo<1?1:pageNo)-1)*pageSize);
		Object oneC = sqlSessionTemplate.selectOne(selectCount, param);
		int total=0;
		if (oneC != null){
			total=Integer.parseInt(oneC.toString());
			pm.setTotal(total);
		}
		if(total!=0)
			pm.setList(sqlSessionTemplate.selectList(selectList, param));
		pm.setOffset(pageNo);
		pm.setOffset(pageSize);
		return pm;
	}
    
	/**
	 * 从List<String>集合中检查是否有存在的元素
	 * @param list
	 * @param tabName
	 * @return
	 */
	private boolean isExcTab(List<String> list, String tabName){
		for (String string : list) {
			if (tabName.equals( string)) {
				return true;
			}
		}
		return false;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	
}