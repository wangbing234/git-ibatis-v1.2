/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 邀请表-Invite
 * @date 2017-11-06 14:25:12
 * @version V1.0
 **/
package com.block.module.font.basic.invite.entity;
import java.util.Date;

import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
/**
 * 邀请表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_invite")
public class Invite  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    
	    /**  
	     * 发送邀请者
	     */ 
	    private String name;
	    
	    /**  
	     * 邀请状态
	     */ 
	    private Integer status;
	    
	    /**  
	     * 头像
	     */ 
	    private String profile;
	    
	    /**  
	     * 邀请类型
	     */ 
	    private Integer type;
	    
	    /**  
	     * 是否已经查看 0，未查看，1已经查看
	     */ 
	    private Integer view;
	    
	    /**  
	     * 
	     */ 
    	@FieldName(name="user_id")
	    private Integer userId;
	    /**  
	     * 
	     */ 
    	@FieldName(name="tenant_id")
	    private Integer tenantId;
	    /**  
	     * 
	     */ 
    	@FieldName(name="publish_id")
	    private Integer publishId;
	    /**  
	     * 
	     */ 
	    private String remark;
	    /**  
	     * 
	     */ 
	    protected Date createtime;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
	    }
	    public void setStatus(Integer status) {  
	        this.status = status;  
	    }
	      
	    public Integer getStatus() {
	        return this.status;  
	    }
	    public String getStatusName() {
	    	if(InviteStatus.REJECT.equals(this.status))
	    	{
	    		return "已拒绝";
	    	}
	    	else if(InviteStatus.ALREADY.equals(this.status))
	    	{
	    		return "已接受";
	    	}
	    	 
	    	return "已发送";  
	    }
	    
	    public void setUserId(Integer userId) {  
	        this.userId = userId;  
	    }
	      
	    public Integer getUserId() {
	        return this.userId;  
	    }
	    public void setTenantId(Integer tenantId) {  
	        this.tenantId = tenantId;  
	    }
	      
	    public Integer getTenantId() {
	        return this.tenantId;  
	    }
	    public void setPublishId(Integer publishId) {  
	        this.publishId = publishId;  
	    }
	      
	    public Integer getPublishId() {
	        return this.publishId;  
	    }
	    public void setRemark(String remark) {  
	        this.remark = remark;  
	    }
	      
	    public String getRemark() {
	        return this.remark;  
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

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
		
		public String getProfile() {
			return profile;
		}

		public void setProfile(String profile) {
			this.profile = profile;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getView() {
			return view;
		}

		public void setView(Integer view) {
			this.view = view;
		}

		
		
}