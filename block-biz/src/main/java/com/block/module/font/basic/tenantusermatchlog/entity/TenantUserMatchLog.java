/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配日志表-TenantUserMatchLog
 * @date 2017-11-06 14:25:13
 * @version V1.0
 **/
package com.block.module.font.basic.tenantusermatchlog.entity;
import java.util.Date;

import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
/**
 * 商户和用户匹配日志表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_tenant_user_match_log")
public class TenantUserMatchLog  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
	    private Integer status;
	    /**  
	     * 
	     */ 
	    private Date createtime;
	    /**  
	     * 
	     */ 
	    private String remark;
	    /**  
	     * 
	     */ 
    	@FieldName(name="match_id")
	    private Integer matchId;
 
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
	    public String getPayTimeString() {
	        return DateUtil.formatDatetime(getCreatetime());  
	    }
	    
	    
	    
	    public Date getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}

		public void setRemark(String remark) {  
	        this.remark = remark;  
	    }
	      
	    public String getRemark() {
	        return this.remark;  
	    }
	    public void setMatchId(Integer matchId) {  
	        this.matchId = matchId;  
	    }
	      
	    public Integer getMatchId() {
	        return this.matchId;  
	    }
	    
//	    /**  
//	     * 
//	     */ 
//    	@FieldName(name="tenant_invite_time")
//	    private Date tenantInviteTime;
//	    /**  
//	     * 
//	     */ 
//    	@FieldName(name="user_confirm_time")
//	    private Date userConfirmTime;
//	    /**  
//	     * 
//	     */ 
//    	@FieldName(name="tenant_confirm_time")
//	    private Date tenantConfirmTime;
//	    /**  
//	     * 
//	     */ 
//    	@FieldName(name="finish_time")
//	    private Date finishTime;
//	    /**  
//	     * 
//	     */ 
//    	@FieldName(name="pay_time")
//	    private Date payTime;
 
}