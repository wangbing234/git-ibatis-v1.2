/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公关发布经营范围-UserPublisScope
 * @date 2017-11-04 15:45:10
 * @version V1.0
 **/
package com.block.module.font.user.userpublisscope.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import java.util.Date;
/**
 * 公关发布经营范围数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_user_publish_scope")
public class UserPublisScope  extends Po{  
      
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
	    
	    public void setScopeId(Integer scopeId) {  
	        this.scopeId = scopeId;  
	    }
	      
	    public Integer getScopeId() {
	        return this.scopeId;  
	    }

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
 
	    
}