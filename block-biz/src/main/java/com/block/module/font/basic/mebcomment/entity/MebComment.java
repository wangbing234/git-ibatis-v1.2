/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 评价信息表-MebComment
 * @date 2017-11-13 17:46:37
 * @version V1.0
 **/
package com.block.module.font.basic.mebcomment.entity;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
/**
 * 评价信息表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_meb_comment")
public class MebComment  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    
	    /**  
	     * 评论者
	     */ 
	    private Integer commenter;
	    /**  
	     * 用户的是谁（id）
	     */ 
    	@FieldName(name="user_id")
	    private Integer userId;
    	 /**  
	     * 业务id
	     */ 
    	@NotNull(message = "业务id不能为空！")
    	@FieldName(name="business_id")
	    private Integer businessId;
    	 /**  
	     * 业务类型
	     */ 
    	@NotNull(message = "评论类型不能为空！")
    	@FieldName(name="business_type")
	    private Integer businessType;
	    /**  
	     * 评论内容
	     */ 
	    private String comment;
	    
	    
	    public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		/**  
	     * 评分
	     */ 
	    @NotNull(message = "评分不能为空！")
	    private Float opinion;
	    /**  
	     * 
	     */ 
	    private Date createtime;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
	    }
	    public void setUserId(Integer userId) {  
	        this.userId = userId;  
	    }
	      
	    public Integer getUserId() {
	        return this.userId;  
	    }


	    public void setOpinion(Float opinion) {  
	        this.opinion = opinion;  
	    }
	      
	    public Float getOpinion() {
	        return this.opinion;  
	    }
	    public String getCreatetimeString() {
	        return DateUtil.getDateTimeString(getCreatetime());  
	    }
	    public void setCreatetime(Date createtime) {  
	        this.createtime = createtime;  
	    }
	      
	    public Date getCreatetime() {
	        return this.createtime;  
	    }

		public Integer getBusinessId() {
			return businessId;
		}

		public void setBusinessId(Integer businessId) {
			this.businessId = businessId;
		}

		public Integer getCommenter() {
			return commenter;
		}

		public void setCommenter(Integer commenter) {
			this.commenter = commenter;
		}

		public Integer getBusinessType() {
			return businessType;
		}

		public void setBusinessType(Integer businessType) {
			this.businessType = businessType;
		}
	    
		
 
}