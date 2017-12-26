/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户发布信息表-TenantPublish
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantpublish.entity;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.block.core.beanutils.SpringMVC_Custom_Json_Date_Deserializer;
import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.TempField;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import com.block.module.common.enums.CommonManager;
import com.block.module.font.basic.servicetype.entity.ServiceType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
/**
 * 商户发布信息表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_tenant_publish")
public class TenantPublish  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
	    @FieldName(name="tenant_id")
	    private Integer tenantId;
	    
	    /**  
	     * 邀请人id，并且发送邀请
	     */ 
	    @TempField
	    private Integer inviteId;
	    /**  
	     * 状态
	     */ 
	    private Integer status;
	    /**  
	     * 
	     */ 
	    private String address;
	    /**  
	     * 
	     */ 
	    private Float longitude;
	    /**  
	     * 
	     */ 
	    private Float latitude;
	    /**  
	     * 
	     */ 
	    private Integer number;
	    /**  
	     * 需要人数
	     */ 
	    @NotNull(message = "需求人数不能为空！")
	    @FieldName(name="need_number")
	    private Integer needNumber;
	    /**  
	     * 
	     */ 
    	@FieldName(name="service_type")
	    private Integer serviceType;
    	
    	/**  
	     * 付款类型
	     * 1:按小时计费，2，按次计费
	     */ 
    	@FieldName(name="pay_type")
	    private Integer payType;
	    /**  
	     * 
	     */ 
	    private Float price;
	    /**  
	     * 
	     */ 
	    private Date deadline;
	    /**  
	     * 
	     */ 
	    private Date createtime;
	    /**  
	     * 
	     */ 
    	@FieldName(name="scope_id")
	    private Integer scopeId;
 
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
	    public void setAddress(String address) {  
	        this.address = address;  
	    }
	      
	    public String getAddress() {
	        return this.address;  
	    }
	    public void setNumber(Integer number) {  
	        this.number = number;  
	    }
	      
	    public Integer getNumber() {
	        return this.number;  
	    }
	    public void setServiceType(Integer serviceType) {  
	        this.serviceType = serviceType;  
	    }
	      
	    public Integer getServiceType() {
	        return this.serviceType;  
	    }
	    public void setPrice(Float price) {  
	        this.price = price;  
	    }
	      
	    public Float getPrice() {
	        return this.price;  
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
	    public String getCreatetimeString() {
	        return DateUtil.getDateTimeString(getCreatetime());  
	    }
	    public void setCreatetime(Date createtime) {  
	        this.createtime = createtime;  
	    }
	      
	    public Date getCreatetime() {
	        return this.createtime;  
	    }
	    public void setScopeId(Integer scopeId) {  
	        this.scopeId = scopeId;  
	    }
	      
	    public Integer getScopeId() {
	        return this.scopeId;  
	    }

		

		public Integer getTenantId() {
			return tenantId;
		}

		public void setTenantId(Integer tenantId) {
			this.tenantId = tenantId;
		}

		public Integer getNeedNumber() {
			return needNumber;
		}

		public void setNeedNumber(Integer needNumber) {
			this.needNumber = needNumber;
		}

		public Integer getInviteId() {
			return inviteId;
		}

		public void setInviteId(Integer inviteId) {
			this.inviteId = inviteId;
		}

		public Integer getPayType() {
			return payType;
		}

		public void setPayType(Integer payType) {
			this.payType = payType;
		}

		public Float getLongitude() {
			return longitude;
		}

		public void setLongitude(Float longitude) {
			this.longitude = longitude;
		}

		public Float getLatitude() {
			return latitude;
		}

		public void setLatitude(Float latitude) {
			this.latitude = latitude;
		}

		
		public String getServiceTypeName()
		{
			return CommonManager.getServiceTypeName(serviceType);
		}
	    
 
}