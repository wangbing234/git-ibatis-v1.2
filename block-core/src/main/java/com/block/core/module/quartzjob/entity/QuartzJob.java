/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: -QuartzJob
 * @date 2017-07-25 18:27:20
 * @version V1.0
 **/
package com.block.core.module.quartzjob.entity;
import java.util.Date;

import com.block.core.ibatis.annotation.po.FID;
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
@TableName(name="ai_quartz_job")
@FID(name = "jobId")
public class QuartzJob  extends Po{
      
	    /**  
	     *   
	     */ 
    	@FieldName(name="job_id")
	    private String jobId;  
	    /**  
	     *   
	     */ 
    	@FieldName(name="job_desc")
	    private String jobDesc;  
	    /**  
	     *   
	     */ 
    	@FieldName(name="bean_id")
	    private String beanId;  
	    /**  
	     *   
	     */ 
    	@FieldName(name="method_name")
	    private String methodName;  
	    /**  
	     *   
	     */ 
    	@FieldName(name="cron_expression")
	    private String cronExpression;  
	    /**  
	     *   
	     */ 
    	@FieldName(name="first_fire_time")
	    private Date firstFireTime;  
	    /**  
	     *   
	     */ 
    	@FieldName(name="last_fire_time")
	    private Date lastFireTime;  
	    /**  
	     *   
	     */ 
    	@FieldName(name="fire_times")
	    private Integer fireTimes;  
	    /**  
	     *   
	     */ 
    	@FieldName(name="succ_times")
	    private Integer succTimes;  
	    /**  
	     *   
	     */ 
    	@FieldName(name="is_stop")
	    private Boolean stop;  
 
	    public void setJobId(String jobId) {  
	        this.jobId = jobId;  
	    }  
	      
	    public String getJobId() {  
	        return this.jobId;  
	    }  
	    public void setJobDesc(String jobDesc) {  
	        this.jobDesc = jobDesc;  
	    }  
	      
	    public String getJobDesc() {  
	        return this.jobDesc;  
	    }  
	    public void setBeanId(String beanId) {  
	        this.beanId = beanId;  
	    }  
	      
	    public String getBeanId() {  
	        return this.beanId;  
	    }  
	    public void setMethodName(String methodName) {  
	        this.methodName = methodName;  
	    }  
	      
	    public String getMethodName() {  
	        return this.methodName;  
	    }  
	    public void setCronExpression(String cronExpression) {  
	        this.cronExpression = cronExpression;  
	    }  
	      
	    public String getCronExpression() {  
	        return this.cronExpression;  
	    }  
	    public String getFirstFireTimeString() {  
	        return DateUtil.formatDatetime(getFirstFireTime());  
	    }  
	    public void setFirstFireTime(Date firstFireTime) {  
	        this.firstFireTime = firstFireTime;  
	    }  
	      
	    public Date getFirstFireTime() {  
	        return this.firstFireTime;  
	    }  
	    public String getLastFireTimeString() {  
	        return DateUtil.formatDatetime(getLastFireTime());  
	    }  
	    public void setLastFireTime(Date lastFireTime) {  
	        this.lastFireTime = lastFireTime;  
	    }  
	      
	    public Date getLastFireTime() {  
	        return this.lastFireTime;  
	    }  
	    public void setFireTimes(Integer fireTimes) {  
	        this.fireTimes = fireTimes;  
	    }  
	      
	    public Integer getFireTimes() {  
	        return this.fireTimes;  
	    }  
	    public void setSuccTimes(Integer succTimes) {  
	        this.succTimes = succTimes;  
	    }  
	      
	    public Integer getSuccTimes() {  
	        return this.succTimes;  
	    }  
	    public void setStop(Boolean isStop) {  
	        this.stop = isStop;  
	    }  
	      
	    public Boolean getStop() {  
	        return this.stop;  
	    }  
 
}