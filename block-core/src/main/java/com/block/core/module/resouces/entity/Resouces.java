/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.resouces.entity;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.TempField;
import com.block.core.ibatis.beans.Po;
/**
 *   数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="s_resouces")
public class Resouces  extends Po{  
      
	    /**  
	     * 主键  
	     */ 
		
    	@FieldName(name="id")
	    private Integer id;  
	    /**  
	     * 父级别id  
	     */ 
    	@NotNull(message = "父节点不能为空")
    	@FieldName(name="pid")
	    private Integer pid;  
	    /**  
	     * url地址  
	     */ 
    	@FieldName(name="url")
	    private String url;  
	    /**  
	     * 资源名称  
	     */ 
    	@NotNull(message = "资源不能为空")
    	@FieldName(name="name")
	    private String name;  
	    /**  
	     * 排序  
	     */ 
    	@FieldName(name="order_num")
	    private Integer orderNum;  
	    /**  
	     * 类型module：模块 ; page：页面 ; button：功能  
	     */ 
    	@NotNull(message = "资源类型不能为空")
    	@FieldName(name="type")
	    private String type;  
	    /**  
	     * 图标  
	     */ 
    	@FieldName(name="icon")
	    private String icon;  
	    /**  
	     * 是否可用  
	     */ 
    	@FieldName(name="valid")
	    private String valid;  
	    /**  
	     * 备注  
	     */ 
    	@FieldName(name="remarks")
	    private String remarks;  
    	
    	@TempField
        private List<Resouces> children=new ArrayList<Resouces>();
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }  
	      
	    public Integer getId() {  
	        return this.id;  
	    }  
	    public void setPid(Integer pid) {  
	        this.pid = pid;  
	    }  
	      
	    public Integer getPid() {  
	        return this.pid;  
	    }  
	    public void setUrl(String url) {  
	        this.url = url;  
	    }  
	      
	    public String getUrl() {  
	        return this.url;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	      
	    public String getName() {  
	        return this.name;  
	    }  
	    public void setOrderNum(Integer orderNum) {  
	        this.orderNum = orderNum;  
	    }  
	      
	    public Integer getOrderNum() {  
	        return this.orderNum;  
	    }  
	    public void setType(String type) {  
	        this.type = type;  
	    }  
	      
	    public String getType() {  
	        return this.type;  
	    }  
	    public void setIcon(String icon) {  
	        this.icon = icon;  
	    }  
	      
	    public String getIcon() {  
	        return this.icon;  
	    }  
	    public void setValid(String valid) {  
	        this.valid = valid;  
	    }  
	      
	    public String getValid() {  
	        return this.valid;  
	    }  
	    public void setRemarks(String remarks) {  
	        this.remarks = remarks;  
	    }  
	      
	    public String getRemarks() {  
	        return this.remarks;  
	    }

		public List<Resouces> getChildren() {
			return children;
		}

		public void setChildren(List<Resouces> children) {
			this.children = children;
		}  
	    
	
 
}