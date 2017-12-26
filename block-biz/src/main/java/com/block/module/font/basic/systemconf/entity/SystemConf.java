/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 系统设置-SystemConf
 * @date 2017-11-04 15:50:23
 * @version V1.0
 **/
package com.block.module.font.basic.systemconf.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import java.util.Date;
/**
 * 系统设置数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_system_conf")
public class SystemConf  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
	    @FieldName(name="key_name")
	    private String keyName;
	    /**  
	     * 
	     */ 
	    private String value;
	    /**  
	     * 
	     */ 
	    private String type;
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
	   
	    
	   
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getKeyName() {
			return keyName;
		}

		public void setKeyName(String keyName) {
			this.keyName = keyName;
		}

	 

		public void setType(String type) {  
	        this.type = type;  
	    }
	      
	    public String getType() {
	        return this.type;  
	    }
	    public void setRemark(String remark) {  
	        this.remark = remark;  
	    }
	      
	    public String getRemark() {
	        return this.remark;  
	    }
 
}