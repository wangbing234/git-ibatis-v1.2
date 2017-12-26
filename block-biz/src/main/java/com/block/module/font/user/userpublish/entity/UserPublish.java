/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公关发布信息表-UserPublish
 * @date 2017-11-04 15:45:10
 * @version V1.0
 **/
package com.block.module.font.user.userpublish.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.beanutils.SpringMVC_Custom_Json_Date_Deserializer;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
/**
 * 公关发布信息表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_user_publish")
public class UserPublish  extends Po{  
      
	    /**  
	     * 用户id
	     */ 
	    private Integer id;
	   
	    private String address;
	    
	    /**  
	     * 地理位置
	     */ 
    	@NotNull(message = "地理位置不能为空！")
	    private Float longitude;
	    
    	/**  
	     * 地理位置
	     */ 
    	@NotNull(message = "地理位置不能为空！")
	    private Float latitude;
	    
    	/**  
	     * 服务类型
	     */ 
    	@NotNull(message = "服务类型不能为空！")
    	@FieldName(name="service_type")
	    private String serviceType;
	    /**  
	     * 
	     */ 
    	@FieldName(name="service_price")
	    private Float servicePrice;
    	
    	/**  
	     * 
	     */ 
    	@FieldName(name="deposit")
	    private Float deposit;
    	
    	/**
    	 * 付款类型
    	 */
    	@FieldName(name="pay_type")
    	private Integer payType;
	    /**  
	     * 关联用户的id
	     */ 
    	@FieldName(name="tenant_publish_id")
	    private Integer tenantPublishId;
    	
	    /**  
	     * 
	     */ 
	    private Date createtime;
	    /**  
	     * 
	     */ 
	    private Date deadline;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
	    }
	    
	    public void setAddress(String address) {  
	        this.address = address;  
	    }
	      
	    public String getAddress() {
	        return this.address;  
	    }
	    public void setLongitude(Float longitude) {  
	        this.longitude = longitude;  
	    }
	      
	    public Float getLongitude() {
	        return this.longitude;  
	    }
	    public void setLatitude(Float latitude) {  
	        this.latitude = latitude;  
	    }
	      
	    public Float getLatitude() {
	        return this.latitude;  
	    }
	  
	    
	    
	    public String getServiceType() {
			return serviceType;
		}

		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}

		 
	    public Float getServicePrice() {
			return servicePrice;
		}

		public void setServicePrice(Float servicePrice) {
			this.servicePrice = servicePrice;
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
	    public String getDeadlineString() {
	        return DateUtil.formatDateMinute(getDeadline());  
	    }
	    
	    @JsonDeserialize(using = SpringMVC_Custom_Json_Date_Deserializer.class)   
	    public void setDeadline(Date deadline) {  
	        this.deadline = deadline;  
	    }
	      
	    public Date getDeadline() {
	        return this.deadline;  
	    }

		public Integer getTenantPublishId() {
			return tenantPublishId;
		}

		public void setTenantPublishId(Integer tenantPublishId) {
			this.tenantPublishId = tenantPublishId;
		}

		public Float getDeposit() {
			return deposit;
		}

		public void setDeposit(Float deposit) {
			this.deposit = deposit;
		}

		public Integer getPayType() {
			return payType;
		}

		public void setPayType(Integer payType) {
			this.payType = payType;
		}
	    
	    
 
}