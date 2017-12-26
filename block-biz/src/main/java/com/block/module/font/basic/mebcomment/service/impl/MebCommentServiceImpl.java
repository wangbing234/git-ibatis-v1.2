/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 评价信息表-MebComment
 * @date 2017-11-13 17:46:37
 * @version V1.0
 **/
package com.block.module.font.basic.mebcomment.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.module.font.basic.mebbasic.service.MebBasicService;
import com.block.module.font.basic.mebcomment.dao.MebCommentDao;
import com.block.module.font.basic.mebcomment.entity.MebComment;
import com.block.module.font.basic.mebcomment.service.MebCommentService;


/**
 * 评价信息表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("mebCommentServiceBasic")
public class MebCommentServiceImpl extends BaseCenterServiceImpl<MebComment,MebCommentDao> implements MebCommentService {

	//注入MebBasicService
	@Resource(name="mebBasicServiceBasic")
	protected MebBasicService mebBasicService;
		
	@Override
	public void addComment(MebComment po) {
		po.setCreatetime(new Date());
		this.baseDao.add(po);
		this.sqlSessionTemplate.update("MebWalletDao.updateOpinion", po.getUserId());
	}

}
