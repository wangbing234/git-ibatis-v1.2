/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 评价信息表-MebComment
 * @date 2017-11-13 17:46:37
 * @version V1.0
 **/
package com.block.module.font.basic.mebcomment.service;

import com.block.core.ibatis.service.BaseService;
import com.block.module.font.basic.mebcomment.dao.MebCommentDao;
import com.block.module.font.basic.mebcomment.entity.MebComment;


/**
 * 评价信息表Service接口
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public interface MebCommentService  extends BaseService<MebComment,MebCommentDao>{
	  
	public void addComment(MebComment po);
}
