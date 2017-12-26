/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 客人预定信息表-GuestPublish
 * @date 2017-11-04 15:45:20
 * @version V1.0
 **/
package com.block.module.font.guest.guestpublish.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.beanutils.SpringMVC_Custom_Json_Date_Deserializer;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
/**
 * 客人预定信息表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_guest_publish")
public class GuestPublish  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
	    @NotNull(message = "需求人数不能为空！")
	    private Integer number;
	    /**  
	     * 1发送订单2用户确认过来上班3商户确认用户过来上班4商户确认下班5付款
	     */ 
	    private Integer status;
	    /**  
	     * 
	     */ 
	    private Date createtime;
	    /**  
	     * 
	     */ 
    	@FieldName(name="service_type")
	    private Integer serviceType;
	    /**  
	     * 
	     */ 
    	@NotNull(message = "商户id不能为空！")
    	@FieldName(name="tenant_id")
	    private Integer tenantId;
    	
    	/**
    	 * 商户名称
    	 */
    	@FieldName(name="tenant_name")
    	private String tenantName;
    	
    	@FieldName(name="tenant_phone")
    	private String tenantPhone;
    	
    	private String profile;
    	  /**  
	     * 
	     */ 
    	@FieldName(name="tenant_publish_id")
	    private Integer tenantPublishId;
	    /**  
	     * 
	     */ 
    	@FieldName(name="guest_id")
	    private Integer guestId;
	    /**  
	     * 
	     */ 
    	@FieldName(name="price_paid")
	    private Float pricePaid;
	    /**  
	     * 
	     */ 
    	@FieldName(name="price_total")
	    private Float priceTotal;
    	
    	/**  
	     * 预定时间
	     */ 
	    private Date deadline;
    	
	    /**  
	     * 
	     */ 
    	@FieldName(name="finish_time")
	    private Date finishTime;
	    /**  
	     * 
	     */ 
    	@FieldName(name="pay_time")
	    private Date payTime;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
	    }
	   
	    public void setNumber(Integer number) {  
	        this.number = number;  
	    }
	      
	    public Integer getNumber() {
	        return this.number;  
	    }
	    public void setStatus(Integer status) {  
	        this.status = status;  
	    }
	      
	    public Integer getStatus() {
	        return this.status;  
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


	   
	    public Integer getServiceType() {
			return serviceType;
		}

		public void setServiceType(Integer serviceType) {
			this.serviceType = serviceType;
		}

		public Integer getTenantId() {
			return tenantId;
		}

		public void setTenantId(Integer tenantId) {
			this.tenantId = tenantId;
		}

		public Integer getGuestId() {
			return guestId;
		}

		public void setGuestId(Integer guestId) {
			this.guestId = guestId;
		}

		public void setPricePaid(Float pricePaid) {  
	        this.pricePaid = pricePaid;  
	    }
	      
	    public Float getPricePaid() {
	        return this.pricePaid;  
	    }
	    public void setPriceTotal(Float priceTotal) {  
	        this.priceTotal = priceTotal;  
	    }
	      
	    public Float getPriceTotal() {
	        return this.priceTotal;  
	    }
	    public String getFinishTimeString() {
	        return DateUtil.formatDatetime(getFinishTime());  
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

		@JsonDeserialize(using = SpringMVC_Custom_Json_Date_Deserializer.class)   
	    public void setFinishTime(Date finishTime) {  
	        this.finishTime = finishTime;  
	    }
	      
	    public Date getFinishTime() {
	        return this.finishTime;  
	    }
	    public String getPayTimeString() {
	        return DateUtil.formatDatetime(getPayTime());  
	    }
	    public void setPayTime(Date payTime) {  
	        this.payTime = payTime;  
	    }
	      
	    public Date getPayTime() {
	        return this.payTime;  
	    }

		public Integer getTenantPublishId() {
			return tenantPublishId;
		}

		public void setTenantPublishId(Integer tenantPublishId) {
			this.tenantPublishId = tenantPublishId;
		}

		public String getTenantName() {
			return tenantName;
		}

		public void setTenantName(String tenantName) {
			this.tenantName = tenantName;
		}

		public String getTenantPhone() {
			return tenantPhone;
		}

		public void setTenantPhone(String tenantPhone) {
			this.tenantPhone = tenantPhone;
		}

		public String getProfile() {
			return profile;
		}

		public void setProfile(String profile) {
			this.profile = profile;
		}

		 
	    
 
}