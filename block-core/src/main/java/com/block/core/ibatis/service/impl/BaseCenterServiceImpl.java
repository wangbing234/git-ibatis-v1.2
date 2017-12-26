package com.block.core.ibatis.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.block.core.ibatis.beans.PageParms;
import com.block.core.ibatis.beans.PagerModel;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.dao.BaseDao;
import com.block.core.ibatis.service.BaseService;
import com.block.core.ibatis.sql.criteria.WherePrams;
import com.block.core.ibatis.sql.order.Order;

public class BaseCenterServiceImpl<T extends Po,D extends BaseDao<T>> implements BaseService<T,D> {

	@Autowired
	protected D  baseDao; 
	
	@Resource(name = "sqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int add(T po) {
		// TODO Auto-generated method stub
		return baseDao.add(po);
	}

	@Override
	public Serializable getField(Serializable id, String fileName) {
		// TODO Auto-generated method stub
		return baseDao.getField(id, fileName);
	}

	@Override
	public int update(T po, Boolean hasNull) {
		return baseDao.update(po,hasNull);
	}

	@Override
	public int del(Serializable id) {
		return baseDao.del(id);
	}
	
	@Override
	public T get(Serializable id) {
		return baseDao.get(id);
	}


	@Override
	public List<T> list(WherePrams where,Order order) {
		// TODO Auto-generated method stub
		return baseDao.list(where,order);
	}

	@Override
	public int update(T po) {
		return baseDao.update(po,false);
	}

	@Override
	public T get(Class<T> t, Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.get(t, id);
	}
	
	/**--以下接口没有实现--**/
	
	@Override
	public int update(T po, WherePrams where) {
		// TODO Auto-generated method stub
		return baseDao.update(po, where);
	}
	
	@Override
	public int update(T po, WherePrams where, Boolean updateNull) {
		// TODO Auto-generated method stub
		return baseDao.update(po, where,updateNull);
	}
	
	@Override
	public int del(WherePrams where) {
		// TODO Auto-generated method stub
		return baseDao.del(where);
	}

	
	@Override
	public T get(WherePrams where) {
		// TODO Auto-generated method stub
		return baseDao.get(where);
	}

	@Override
	public PagerModel<T> page(WherePrams where, Order order, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.page(where, order, offset, pageSize);
	}

	@Override
	public Integer count(WherePrams where) {
		// TODO Auto-generated method stub
		return baseDao.count(where); 
	}

	@Override
	public long nextId() {
		// TODO Auto-generated method stub
		return baseDao.nextId(); 
	}

	@Override
	public int deleteByIds(String[] ids) {
		// TODO Auto-generated method stub
		return baseDao.deleteByIds(ids);
	}

	@Override
	public int update(Serializable id,String fieldName,Object fieldValue,Object... args) {
		// TODO Auto-generated method stub
		return baseDao.update(id, fieldName, fieldValue,args);
	}

	@Override
	public int update(Serializable id, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseDao.update(id, map);
	}

	@Override
	public PagerModel selectPageList(String selectList, String selectCount,PageParms param) {
		// TODO Auto-generated method stub
		return baseDao.selectPageList(selectList, selectCount,param);
	}

	@Override
	public SqlSessionTemplate getSqlSessionTemplate() {
		// TODO Auto-generated method stub
		return baseDao.getSqlSessionTemplate();
	}
	
}
