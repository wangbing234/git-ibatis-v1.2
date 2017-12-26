/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -MebBasic
 * @date 2017-11-04 15:50:22
 * @version V1.0
 **/
package com.block.module.font.basic.mebbasic.entity;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.TempField;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
/**
 * 数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_meb_basic")
public class MebBasic  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
	    private String username;
	    /**  
	     * 
	     */ 
	    private Date createtime;
	    /**  
	     * 
	     */ 
	    private Date updatetime;
	    /**  
	     * 推荐人
	     */ 
	    private Integer referer;
	    /**  
	     * 锁定
	     */ 
	    private Integer status;
	    /**  
	     * 商户或用户
	     */ 
	    private Integer type;
	    
	    /**
	     * 应用唯一标识
	     */
	    private String openid;
	    
	    /**
	     * 微信会话
	     */
	    @FieldName(name="session_key")
	    private String sessionKey;
	    /**  
	     * 
	     */ 
	    private String nickname;
	    /**  
	     * 
	     */ 
	    private String qq;
	    
	    /**  
	     * 是否已经初始化类型
	     */ 
	    private String first;
	    
	    /**  
	     * 
	     */ 
	    @TempField
	    private String code;
	    /**  
	     * 在商户中微信别当做用户范围
	     */ 
	    private String weixin;
	    /**  
	     * 手机号
	     */ 
	    @NotNull(message = "手机号不能为空！")
	    @Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message="手机号格式不正确")
	    private String phone;
	    /**  
	     * 
	     */ 
	    private String address;
	    /**  
	     * 
	     */ 
	    private Float longitude;
	    /**  
	     * 
	     */ 
	    private Float latitude;
	    /**  
	     * 
	     */ 
	    private String images;
	    
	    /**  
	     * 头像
	     */ 
	    private String profile;
	    
	    /**  
	     * 
	     */ 
	    private Integer age;
	    /**  
	     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	     */ 
	    private String gender;
	    /**  
	     * 
	     */ 
	    private Float heigh;
	    
	    
	    /**  
	     * 
	     */ 
	    private String remark;
	    
	    @TempField
		private MultipartFile file;
	    
		//用户范围
	   @TempField
	   private String scope;
	    
		@TempField
		public String yzm;
 
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
	    public String getCreatetimeString() {
	        return DateUtil.getDateTimeString(getCreatetime());  
	    }
	    public void setCreatetime(Date createtime) {  
	        this.createtime = createtime;  
	    }
	      
	    public Date getCreatetime() {
	        return this.createtime;  
	    }
	    public String getUpdatetimeString() {
	        return DateUtil.getDateTimeString(getUpdatetime());  
	    }
	    public void setUpdatetime(Date updatetime) {  
	        this.updatetime = updatetime;  
	    }
	      
	    public Date getUpdatetime() {
	        return this.updatetime;  
	    }
	    public void setReferer(Integer referer) {  
	        this.referer = referer;  
	    }
	      
	    public Integer getReferer() {
	        return this.referer;  
	    }
	    public void setStatus(Integer status) {  
	        this.status = status;  
	    }
	      
	    public Integer getStatus() {
	        return this.status;  
	    }
	    public void setType(Integer type) {  
	        this.type = type;  
	    }
	      
	    public Integer getType() {
	        return this.type;  
	    }
	    public void setNickname(String nickname) {  
	        this.nickname = nickname;  
	    }
	      
	    public String getNickname() {
	        return this.nickname;  
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
	    public void setPhone(String phone) {  
	        this.phone = phone;  
	    }
	      
	    public String getPhone() {
	        return this.phone;  
	    }
	    public void setAddress(String address) {  
	        this.address = address;  
	    }
	      
	    public String getAddress() {
	        return this.address;  
	    }
	    public void setLongitude(Float longitude) {  
	        this.longitude = longitude;  
	    }
	      
	    public Float getLongitude() {
	        return this.longitude;  
	    }
	    public void setLatitude(Float latitude) {  
	        this.latitude = latitude;  
	    }
	      
	    public Float getLatitude() {
	        return this.latitude;  
	    }
	    public void setImages(String images) {  
	        this.images = images;  
	    }
	      
	    public String getImages() {
	        return this.images;  
	    }
	    public void setProfile(String profile) {  
	        this.profile = profile;  
	    }
	      
	    public String getProfile() {
	        return this.profile;  
	    }

	

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public Float getHeigh() {
			return heigh;
		}

		public void setHeigh(Float heigh) {
			this.heigh = heigh;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		public String getYzm() {
			return yzm;
		}

		public void setYzm(String yzm) {
			this.yzm = yzm;
		}
		
		
		public MultipartFile getFile() {
			return file;
		}

		public void setFile(MultipartFile file) {
			this.file = file;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getSessionKey() {
			return sessionKey;
		}

		public void setSessionKey(String sessionKey) {
			this.sessionKey = sessionKey;
		}

		public String getFirst() {
			return first;
		}

		public void setFirst(String first) {
			this.first = first;
		}
		
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		
}