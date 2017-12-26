/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 服务类型字典表-ServiceType
 * @date 2017-11-04 15:50:23
 * @version V1.0
 **/
package com.block.module.font.basic.servicetype.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import java.util.Date;
/**
 * 服务类型字典表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_service_type")
public class ServiceType  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
	    private String type;
	    /**  
	     * 
	     */ 
	    private String name;
	    /**  
	     * 
	     */ 
	    private Float price;
	    
	    /**  
	     * 
	     */ 
	    @FieldName(name="order_no")
	    private Integer orderNo;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
	    }
	    public void setType(String type) {  
	        this.type = type;  
	    }
	      
	    public String getType() {
	        return this.type;  
	    }
	    public void setName(String name) {  
	        this.name = name;  
	    }
	      
	    public String getName() {
	        return this.name;  
	    }
	    public void setPrice(Float price) {  
	        this.price = price;  
	    }
	      
	    public Float getPrice() {
	        return this.price;  
	    }

		public Integer getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(Integer orderNo) {
			this.orderNo = orderNo;
		}
 
	    
}