/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -Systemlog
 * @date 2017-11-16 16:35:13
 * @version V1.0
 **/
package com.block.core.module.systemlog.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import java.util.Date;
/**
 * 数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="s_systemlog")
public class Systemlog  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
	    private String title;
	    /**  
	     * 
	     */ 
	    private String content;
	    /**  
	     * 
	     */ 
	    private Integer type;
	    /**  
	     * 
	     */ 
	    private String account;
	    /**  
	     * 
	     */ 
    	@FieldName(name="login_ip")
	    private String loginIp;
	    /**  
	     * 
	     */ 
	    private Date logintime;
	    /**  
	     * 
	     */ 
	    private String loginArea;
	    /**  
	     * 
	     */ 
	    private String diffAreaLogin;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
	    }
	    public void setTitle(String title) {  
	        this.title = title;  
	    }
	      
	    public String getTitle() {
	        return this.title;  
	    }
	    public void setContent(String content) {  
	        this.content = content;  
	    }
	      
	    public String getContent() {
	        return this.content;  
	    }
	    public void setType(Integer type) {  
	        this.type = type;  
	    }
	      
	    public Integer getType() {
	        return this.type;  
	    }
	    public void setAccount(String account) {  
	        this.account = account;  
	    }
	      
	    public String getAccount() {
	        return this.account;  
	    }
	    public void setLoginIp(String loginIp) {  
	        this.loginIp = loginIp;  
	    }
	      
	    public String getLoginIp() {
	        return this.loginIp;  
	    }
	    public String getLogintimeString() {
	        return DateUtil.formatDatetime(getLogintime());  
	    }
	    public void setLogintime(Date logintime) {  
	        this.logintime = logintime;  
	    }
	      
	    public Date getLogintime() {
	        return this.logintime;  
	    }
	    public void setLoginArea(String loginArea) {  
	        this.loginArea = loginArea;  
	    }
	      
	    public String getLoginArea() {
	        return this.loginArea;  
	    }
	    public void setDiffAreaLogin(String diffAreaLogin) {  
	        this.diffAreaLogin = diffAreaLogin;  
	    }
	      
	    public String getDiffAreaLogin() {
	        return this.diffAreaLogin;  
	    }
 
}