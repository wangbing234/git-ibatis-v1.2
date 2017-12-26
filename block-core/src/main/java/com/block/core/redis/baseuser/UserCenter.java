/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 用户基础信息表-User
 * @date 2017-10-30 15:56:59
 * @version V1.0
 **/
package com.block.core.redis.baseuser;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import java.util.Date;
/**
 * 用户基础信息表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="u_user")
public class UserCenter  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 昵称（不是实时更新）
	     */ 
	    private String name;
	    /**  
	     * 邮件
	     */ 
	    private String mail;
	    /**  
	     * 手机号
	     */ 
	    private String phone;
	    /**  
	     * 密码
	     */ 
    	@NotNull(message = "密码不能为空！")
	    private String password;
	    /**  
	     * 
	     */ 
	    private String qq;
	    /**  
	     * 微信
	     */ 
	    private String weixin;
	    /**  
	     * 系统类型
	     */ 
    	@FieldName(name="system_type")
	    private String systemType;
	    /**  
	     * 第三方系统id
	     */ 
    	@FieldName(name="system_id")
	    private String systemId;
	    /**  
	     * 手机号
	     */ 
    	@FieldName(name="json_str")
	    private String jsonStr;
    	
    	/**  
	     *   
	     */ 
	    private String token; 
	    
	    /**  
	     *   登录字段
	     */ 
	    private String field; 
	    
	    /**  
	     * 创建时间
	     */ 
	    private Date createtime;
 
	    public void setId(Integer id) {  
	        this.id = id;  
	    }
	      
	    public Integer getId() {
	        return this.id;  
	    }
	    public void setName(String name) {  
	        this.name = name;  
	    }
	      
	    public String getName() {
	        return this.name;  
	    }
	    public void setMail(String mail) {  
	        this.mail = mail;  
	    }
	      
	    public String getMail() {
	        return this.mail;  
	    }
	    public void setPhone(String phone) {  
	        this.phone = phone;  
	    }
	      
	    public String getPhone() {
	        return this.phone;  
	    }
	    public void setPassword(String password) {  
	        this.password = password;  
	    }
	      
	    public String getPassword() {
	        return this.password;  
	    }
	    public void setQq(String qq) {  
	        this.qq = qq;  
	    }
	      
	    public String getQq() {
	        return this.qq;  
	    }
	    public void setWeixin(String weixin) {  
	        this.weixin = weixin;  
	    }
	      
	    public String getWeixin() {
	        return this.weixin;  
	    }
	    public void setSystemType(String systemType) {  
	        this.systemType = systemType;  
	    }
	      
	    public String getSystemType() {
	        return this.systemType;  
	    }
	    public void setSystemId(String systemId) {  
	        this.systemId = systemId;  
	    }
	      
	    public String getSystemId() {
	        return this.systemId;  
	    }
	    public void setJsonStr(String jsonStr) {  
	        this.jsonStr = jsonStr;  
	    }
	      
	    public String getJsonStr() {
	        return this.jsonStr;  
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

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}
	    
		
}