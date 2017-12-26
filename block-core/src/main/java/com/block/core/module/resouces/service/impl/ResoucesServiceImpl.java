/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.resouces.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.module.resouces.dao.ResoucesDao;
import com.block.core.module.resouces.entity.Resouces;
import com.block.core.module.resouces.service.ResoucesService;


/**
 *   Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service
@Component("resoucesServiceSystem")
public class ResoucesServiceImpl extends BaseCenterServiceImpl<Resouces,ResoucesDao> implements ResoucesService {

	@Override
	public List<Resouces> getMenus(String user) {
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("id", user);
		List<Resouces> list = baseDao.selectAuthMenus(map);
		List<Resouces> rList=new ArrayList<Resouces>();
		for (Resouces resouces : list) {
			 if(resouces.getPid()==0){
				 rList.add(resouces);
			 }
			 else
			 {
				 for (Resouces pSub : rList) {
					 if(Integer.compare(resouces.getPid(), pSub.getId())==0){
						 pSub.getChildren().add(resouces);
					 }
				 }
			 }
		}
		return rList;
	}

}
