/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 短信记录表-SmsInfo
 * @date 2017-10-31 10:20:52
 * @version V1.0
 **/
package com.block.core.smsinfo.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import java.util.Date;
/**
 * 短信记录表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="s_sms_info")
public class SmsInfo  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 业务系统id
	     */ 
    	@FieldName(name="system_id")
    	@NotNull(message = "业务系统id不能为空！")
	    private Integer systemId;
	    /**  
	     * 手机号
	     */ 
    	@FieldName(name="sms_phone")
    	@NotNull(message = "手机号不能为空！")
	    private String smsPhone;
	    /**  
	     * 消息编号
	     */ 
    	@FieldName(name="sms_no")
	    private String smsNo;
	    /**  
	     * 消息参数
	     */ 
    	@FieldName(name="sms_args")
    	@NotNull(message = "消息参数不能为空！")
	    private String smsArgs;
	    /**  
	     * 消息类型
	     */ 
    	@FieldName(name="sms_type")
	    private String smsType;
	    /**  
	     * 发送时间
	     */ 
    	@FieldName(name="send_time")
	    private Date sendTime;
	    /**  
	     * 返回编码
	     */ 
    	@FieldName(name="ret_code")
	    private String retCode;
	    /**  
	     * 子系统类型
	     */ 
    	@FieldName(name="system_type")
	    private String systemType;
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
	    public void setSystemId(Integer systemId) {  
	        this.systemId = systemId;  
	    }
	      
	    public Integer getSystemId() {
	        return this.systemId;  
	    }
	    public void setSmsPhone(String smsPhone) {  
	        this.smsPhone = smsPhone;  
	    }
	      
	    public String getSmsPhone() {
	        return this.smsPhone;  
	    }
	    public void setSmsNo(String smsNo) {  
	        this.smsNo = smsNo;  
	    }
	      
	    public String getSmsNo() {
	        return this.smsNo;  
	    }
	    public void setSmsArgs(String smsArgs) {  
	        this.smsArgs = smsArgs;  
	    }
	      
	    public String getSmsArgs() {
	        return this.smsArgs;  
	    }
	    public void setSmsType(String smsType) {  
	        this.smsType = smsType;  
	    }
	      
	    public String getSmsType() {
	        return this.smsType;  
	    }
	    public void setSendTime(Date sendTime) {  
	        this.sendTime = sendTime;  
	    }
	      
	    public Date getSendTime() {
	        return this.sendTime;  
	    }
	    public void setRetCode(String retCode) {  
	        this.retCode = retCode;  
	    }
	      
	    public String getRetCode() {
	        return this.retCode;  
	    }
	    public void setSystemType(String systemType) {  
	        this.systemType = systemType;  
	    }
	      
	    public String getSystemType() {
	        return this.systemType;  
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
 
}