/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配表-TenantUserMatch
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantusermatch.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.domain.Busi;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import com.block.module.common.enums.CommonManager;
import com.github.pagehelper.util.StringUtil;

import java.util.Date;
/**
 * 商户和用户匹配表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_tenant_user_match")
public class TenantUserMatch  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 1，商家通知接单（发短信）
            2，用户确认过来上班。
            3，商户确认用户过来上班。（发短信）
            4，商户确认下班
            5，付款。
	     */ 
	    protected Integer status;
	    
	    /**
	     * 是否计算在订单中
	     */
	    @FieldName(name="in_member")
	    private String inMember;
	    /**  
	     * 计算付款
	     */ 
    	@FieldName(name="price_cal")
	    private Float priceCal;
	    /**  
	     * 已经付款
	     */ 
    	@FieldName(name="price_paid")
	    private Float pricePaid;
	    /**  
	     * 应该付款
	     */ 
    	@FieldName(name="price_total")
	    private Float priceTotal;
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
    	@FieldName(name="user_publish_id")
	    private Integer userPublishId;
	    /**  
	     * 
	     */ 
    	@FieldName(name="tenant_publish_id")
	    private Integer tenantPublishId;
    	/**  
	     * 是谁主动
	     */ 
    	@FieldName(name="init_type")
	    private Integer initType;
    	/**  
	     * 服务类型
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
	     * 付款方式
	     * 1，余额
	     * 2，微信
	     * 3，现金
	     * 4，支付宝
	     * 5，银联
	     */ 
    	@FieldName(name="pay_method")
	    private Integer payMethod;
    	
    	
    	@FieldName(name="pay_time")
	    private Date payTime;
    	
    	//创建时间
    	@FieldName(name="create_time")
	    private Date createtime;
    	
    	//商户确认付款时间
    	@FieldName(name="sure_time")
	    private Date suretime;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
	    }
	    
	 
	    
	    

		public Date getPayTime() {
			return payTime;
		}

		public void setPayTime(Date payTime) {
			this.payTime = payTime;
		}

		public void setUserId(Integer userId) {  
	        this.userId = userId;  
	    }
	      
	    public Integer getUserId() {
	        return this.userId;  
	    }


	    
	    public Integer getTenantId() {
			return tenantId;
		}

		public void setTenantId(Integer tenantId) {
			this.tenantId = tenantId;
		}

		public void setUserPublishId(Integer userPublishId) {  
	        this.userPublishId = userPublishId;  
	    }
	      
	    public Integer getUserPublishId() {
	        return this.userPublishId;  
	    }
	    
	    
	    
	    public Integer getTenantPublishId() {
			return tenantPublishId;
		}

		public void setTenantPublishId(Integer tenantPublishId) {
			this.tenantPublishId = tenantPublishId;
		}

		public void setServiceType(Integer serviceType) {  
	        this.serviceType = serviceType;  
	    }
	      
	    public Integer getServiceType() {
	        return this.serviceType;  
	    }

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
			if(status==TenantMatchStatus.TENANT_CANCEL || status==TenantMatchStatus.USER_CANCEL){
				this.inMember=Busi.NO;
			}
			else if(null!=status){
				this.inMember=Busi.YES;
			}
		}

		public Float getPriceCal() {
			return priceCal;
		}

		public void setPriceCal(Float priceCal) {
			this.priceCal = priceCal;
		}

		public Float getPricePaid() {
			return pricePaid;
		}

		public void setPricePaid(Float pricePaid) {
			this.pricePaid = pricePaid;
		}

		public Float getPriceTotal() {
			return priceTotal;
		}

		public void setPriceTotal(Float priceTotal) {
			this.priceTotal = priceTotal;
		}

		public Integer getInitType() {
			return initType;
		}

		public void setInitType(Integer init) {
			this.initType = init;
		}

	

		public String getInMember() {
			return inMember;
		}

		public void setInMember(String inMember) {
			this.inMember = inMember;
		}

		public Integer getPayType() {
			return payType;
		}

		public void setPayType(Integer payType) {
			this.payType = payType;
		}

		public Integer getPayMethod() {
			return payMethod;
		}

		public void setPayMethod(Integer payMethod) {
			this.payMethod = payMethod;
		}

		public Date getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}

		public Date getSuretime() {
			return suretime;
		}

		public void setSuretime(Date suretime) {
			this.suretime = suretime;
		}
		
		
		
//		public String getServiceTypeName(){
//			return CommonManager.getServiceTypeName(serviceType);
//		}

}