/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.block.core.module.user.entity;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
/**
 * 数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="s_user")
public class UserModel  extends Po{  
	    /**  
	     *   
	     */ 
	    private Integer id;  
	    /**  
	     *   
	     */ 
	    @NotNull(message = "用户名不能为空")
	    @Pattern(regexp = "[a-zA-Z0-9_]{5,10}", message = "{user.username.illegal}")
	    private String username;
	    /**  
	     *   
	     */ 
	    @NotNull(message = "密码不能为空")
	    @Size(min = 5, max = 10, message = "{password.length.illegal}")
	    private String password; 
	    /**  
	     *   
	     */ 
	    private Date createtime;  
	    /**  
	     *   
	     */ 
	    private Date updatetime;  
	    /**  
	     *   
	     */ 
	    @FieldName(name="create_account")
	    private String createAccount;  
	    /**  
	     *   
	     */ 
	    @FieldName(name="update_account")
	    private String updateAccount;  
	    /**  
	     *   
	     */ 
	    private String status;  
	    
	    /**  
	     *   
	     */ 
	    private String rid;  
	    /**  
	     *   
	     */ 
	    private String nickname;  
	    /**  
	     *   
	     */ 
	    private String phone;  
	    /**  
	     *   
	     */ 
	    private String email;  
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }  
	      
	    public Integer getId() {  
	        return this.id;  
	    }  
	    public void setUsername(String username) {  
	        this.username = username;  
	    }  
	      
	    public String getUsername() {  
	        return this.username;  
	    }  
	    public void setPassword(String password) {  
	        this.password = password;  
	    }  
	      
	    public String getPassword() {  
	        return this.password;  
	    }  
	    public String getCreatetimeString() {  
	        return DateUtil.formatDatetime(getCreatetime());  
	    }  
	    public void setCreatetime(Date createtime) {  
	        this.createtime = createtime;  
	    }  
	      
	    public Date getCreatetime() {  
	        return this.createtime;  
	    }  
	    public String getUpdatetimeString() {  
	        return DateUtil.formatDatetime(getUpdatetime());  
	    }  
	    public void setUpdatetime(Date updatetime) {  
	        this.updatetime = updatetime;  
	    }  
	      
	    public Date getUpdatetime() {  
	        return this.updatetime;  
	    }  
	    public void setCreateAccount(String createAccount) {  
	        this.createAccount = createAccount;  
	    }  
	      
	    public String getCreateAccount() {  
	        return this.createAccount;  
	    }  
	    public void setUpdateAccount(String updateAccount) {  
	        this.updateAccount = updateAccount;  
	    }  
	      
	    public String getUpdateAccount() {  
	        return this.updateAccount;  
	    }  
	    public void setStatus(String status) {  
	        this.status = status;  
	    }  
	      
	    public String getStatus() {  
	        return this.status;  
	    }  
	    public void setRid(String rid) {  
	        this.rid = rid;  
	    }  
	      
	    public String getRid() {  
	        return this.rid;  
	    }  
	    public void setNickname(String nickname) {  
	        this.nickname = nickname;  
	    }  
	      
	    public String getNickname() {  
	        return this.nickname;  
	    }  
	    public void setEmail(String email) {  
	        this.email = email;  
	    }  
	      
	    public String getEmail() {  
	        return this.email;  
	    }

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
	    

}