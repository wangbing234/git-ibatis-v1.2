package com.block.core.ibatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.block.core.ibatis.beans.PageParms;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.sql.criteria.WherePrams;
import com.block.core.ibatis.sql.order.Order;

/**
 * 公共数据库操作层
 * @author bing.wang
 * @time 2016年5月3日下午2:55:13
 * @email test.qq.com
 * @param <T> 实体PO类型
 * @param <PK> PO主键类型
 */
public interface BaseDao<T extends Po> {

    /**
     * 记录添加
     * @param po
     * @return
     */
    public int add(T po);

    /**
     * 通过主键获取某个记录
     * @param id 主键
     * @return PO
     */
    public T get(Serializable id);
    
    /**
     * 通过主键和对象获取某个记录
     * @param t  实体类
     * @param id 主键
     * @return PO
     */
    public T get(Class<T> t,Serializable id);

    /**
     * 通过主键获取某个字段的值
     * @param id 主键
     * @param fileName 字段名称
     * @return
     */
    public Serializable getField(Serializable id, String fileName);

    /**
     * 条件获取一条记录
     * @param where 条件表达式
     * @return PO
     */
    public T get(WherePrams where);


    /**
     * 条件查询列表
     * @param where 条件表达式
     * @param order 排序
     * @return PO列表
     */
    public List<T> list(WherePrams where,Order order);
    
    /**
     * 条件查询查询个数
     * @param where 条件表达式
     * @return PO个数
     */
    public Integer count(WherePrams where);

    /**
     * 条件查询分页列表
     * @param where 条件表达式
     * @param order 排序
     * @param offset 第几页
     * @param pageSize 分页大小
     * @return 分页PO列表
     */
    public PagerModel<T> page(WherePrams where,Order order,int offset,int pageSize);
    

    /**
     * 更新对象的所有字段
     * @param po 当前对象
     * @param excludeNull 是否更新新对象
     * @return 受影响的行数
     */
    public int update(T po,Boolean  excludeNull);
    
    /**
     * 更新PO的所有字段
     * @param po 当前对象
     * @return 受影响的行数
     */
    public int update(T po);
    
    /**
     * 更新指定字段
     * @param id id
     * @param fieldName 字段名
     * @param fieldValue 字段值
     * @return 受影响的行数
     */
    public int update(Serializable id,String fieldName,Object fieldValue,Object... args);
    
    /**
     * 更新指定字段
     * @param id id
     * @param map 字段值，键值对
     * @return 受影响的行数
     */
    public int update(Serializable id,Map<String ,Object> map);


    /**
     * 条件更新所有字段
     * @param po 当前对象
     * @param where 条件表达式
     * @return 受影响的行数
     */
    public int update(T po, WherePrams where);
    
    /**
     * 条件更新所有字段
     * @param po 当前对象
     * @param where 条件表达式
     * @param updateNull 是否更新空值
     * @return 受影响的行数
     */
    public int update(T po, WherePrams where,Boolean updateNull);

    /**
     * 删除某个记录
     * @param id 主键
     * @return 受影响的行数
     */
    public int del(Serializable id);
    
    /**
     * 批量删除
     * @param ids id数组
     * @return 受影响的行数
     */
    public int deleteByIds(String[] ids);

    /**
     * 条件删除某个记录
     * @param where 条件表达式
     * @return 受影响的行数
     */
    public int del(WherePrams where);
    
    /**
     * 条件删除某个记录
     * @return 下一个序列号
     */
    public long nextId();
    
    /**
   	 * 分页查询
   	 * @param selectList  记录查询sql
   	 * @param selectCount 条数查询sql
   	 * @param param
   	 * @return
   	 */
   	public PagerModel selectPageList(String selectList, String selectCount,PageParms param);
   	
   	
   	public SqlSessionTemplate getSqlSessionTemplate();


}