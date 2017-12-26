/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 角色表-Orderment
 * @date 2017-11-21 14:36:18
 * @version V1.0
 **/
package com.block.module.font.basic.orderment.entity;
import java.util.Date;

import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
/**
 * 角色表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="t_orderment")
public class Orderment  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
    	@FieldName(name="order_no")
	    private String orderNo;
    	
    	@FieldName(name="nonce_str")
    	private String nonceStr;
	    /**  
	     * 
	     */ 
	    private String type;
	    /**  
	     * 
	     */ 
    	@FieldName(name="busi_id")
	    private Integer busiId;
	    /**  
	     * 
	     */ 
	    private String status;
	    /**  
	     * 
	     */ 
	    private Float amount;
	    /**  
	     * 
	     */ 
	    private Date createtime;
	    
	    /**  
	     * 
	     */ 
	    private Date updatetime;
	    /**  
	     * 
	     */ 
    	@FieldName(name="return_code")
	    private String returnCode;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
	    }
	    public void setOrderNo(String orderNo) {  
	        this.orderNo = orderNo;  
	    }
	      
	    public String getOrderNo() {
	        return this.orderNo;  
	    }
	    public void setType(String type) {  
	        this.type = type;  
	    }
	      
	    public String getType() {
	        return this.type;  
	    }
	    public void setBusiId(Integer busiId) {  
	        this.busiId = busiId;  
	    }
	      
	    public Integer getBusiId() {
	        return this.busiId;  
	    }
	    public void setStatus(String status) {  
	        this.status = status;  
	    }
	      
	    public String getStatus() {
	        return this.status;  
	    }
	    public void setAmount(Float amount) {  
	        this.amount = amount;  
	    }
	      
	    public Float getAmount() {
	        return this.amount;  
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
	    public void setReturnCode(String returnCode) {  
	        this.returnCode = returnCode;  
	    }
	      
	    public String getReturnCode() {
	        return this.returnCode;  
	    }

		public Date getUpdatetime() {
			return updatetime;
		}

		public void setUpdatetime(Date updatetime) {
			this.updatetime = updatetime;
		}

		public String getNonceStr() {
			return nonceStr;
		}

		public void setNonceStr(String nonceStr) {
			this.nonceStr = nonceStr;
		}
 
	    
}