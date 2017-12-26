/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.privilege.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.beans.Po;
/**
 *   数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="s_privilege")
public class PrivilegeModel  extends Po{  
      
	    /**  
	     *   
	     */ 
	    private Integer id;  
	    /**  
	     *   
	     */ 
	    private Integer rid;  
	    /**  
	     *   
	     */ 
	    private Integer mid;  
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }  
	      
	    public Integer getId() {  
	        return this.id;  
	    }  
	    public void setRid(Integer rid) {  
	        this.rid = rid;  
	    }  
	      
	    public Integer getRid() {  
	        return this.rid;  
	    }  
	    public void setMid(Integer mid) {  
	        this.mid = mid;  
	    }  
	      
	    public Integer getMid() {  
	        return this.mid;  
	    }  
 
}