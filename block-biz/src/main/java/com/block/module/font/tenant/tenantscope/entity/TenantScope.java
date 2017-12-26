/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商家经营范围-TenantScope
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantscope.entity;
import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.beans.Po;
/**
 * 商家经营范围数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_tenant_scope")
public class TenantScope  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
    	@FieldName(name="user_id")
	    private Integer userId;
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
	    public void setUserId(Integer userId) {  
	        this.userId = userId;  
	    }
	      
	    public Integer getUserId() {
	        return this.userId;  
	    }
	    public void setScopeId(Integer scopeId) {  
	        this.scopeId = scopeId;  
	    }
	      
	    public Integer getScopeId() {
	        return this.scopeId;  
	    }
 
}