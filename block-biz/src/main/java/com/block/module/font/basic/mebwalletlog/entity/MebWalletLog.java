/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公用会员财务表变更表-MebWalletLog
 * @date 2017-11-04 15:50:22
 * @version V1.0
 **/
package com.block.module.font.basic.mebwalletlog.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import java.util.Date;
/**
 * 公用会员财务表变更表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_meb_wallet_log")
public class MebWalletLog  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
    	@FieldName(name="new_amount")
	    private Float newAmount;
	    /**  
	     * 
	     */ 
    	@FieldName(name="change_amount")
	    private Float changeAmount;
	    /**  
	     * 
	     */ 
    	@FieldName(name="user_id")
	    private Integer userId;
    	
    	/**  
	     * 
	     */ 
    	@FieldName(name="user_type")
	    private Integer userType;
	    /**  
	     * 
	     */ 
	    private Integer reason;
	    /**  
	     * 1, 可用资金，
	     * 2，冻结资金
	     * 2，押金
	     */ 
	    private Integer type;
	    /**  
	     * 
	     */ 
	    private Date createtime;
	    /**  
	     * 
	     */ 
	    private String remark;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
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
	    public void setRemark(String remark) {  
	        this.remark = remark;  
	    }
	      
	    public String getRemark() {
	        return this.remark;  
	    }

		public Float getNewAmount() {
			return newAmount;
		}

		public void setNewAmount(Float newAmount) {
			this.newAmount = newAmount;
		}

		public Float getChangeAmount() {
			return changeAmount;
		}

		public void setChangeAmount(Float changeAmount) {
			this.changeAmount = changeAmount;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public Integer getReason() {
			return reason;
		}

		public void setReason(Integer reason) {
			this.reason = reason;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getUserType() {
			return userType;
		}

		public void setUserType(Integer userType) {
			this.userType = userType;
		}
	    
 
}