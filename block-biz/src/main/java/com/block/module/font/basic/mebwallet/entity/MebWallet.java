/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公用会员财务表-MebWallet
 * @date 2017-11-04 15:50:22
 * @version V1.0
 **/
package com.block.module.font.basic.mebwallet.entity;
import com.block.core.ibatis.annotation.po.FieldName;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.beans.Po;
/**
 * 公用会员财务表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_meb_wallet")
public class MebWallet  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 
	     */ 
    	@FieldName(name="user_id")
	    private Integer userId;
    	
    	@FieldName(name="first_pay")
	    private Integer firstPay;
	    /**  
	     * 押金
	     */ 
	    private Float deposit;
	    /**  
	     * 
	     */ 
	    private Float free;
	    /**  
	     * 
	     */ 
	    private Float freeze;
	    
	    /**  
	     * 
	     */ 
	    private String stop;
	    
	    /**  
	     * 信用分
	     */ 
	    private Float credit;
	    /**  
	     * 评分
	     */ 
	    private Float opinion;
	    /**  
	     * 服务次数
	     */ 
	    private Integer times;
	    
	    /**  
	     * 服务次数
	     */ 
	    @FieldName(name="fail_times")
	    private Integer failTimes;
	    /**  
	     * 服务次数
	     */ 
	    @FieldName(name="sucess_times")
	    private Integer sucessTimes;
 
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
	    public void setDeposit(Float deposit) {  
	        this.deposit = deposit;  
	    }
	      
	    public Float getDeposit() {
	        return this.deposit;  
	    }
	    public void setFree(Float free) {  
	        this.free = free;  
	    }
	      
	    public Float getFree() {
	        return this.free;  
	    }
	    public void setFreeze(Float freeze) {  
	        this.freeze = freeze;  
	    }
	      
	    public Float getFreeze() {
	        return this.freeze;  
	    }

		public Float getCredit() {
			return credit;
		}

		public void setCredit(Float credit) {
			this.credit = credit;
		}

		public Float getOpinion() {
			return opinion;
		}

		public void setOpinion(Float opinion) {
			this.opinion = opinion;
		}

	 

		public Integer getTimes() {
			return times;
		}

		public void setTimes(Integer times) {
			this.times = times;
		}

		public Integer getFailTimes() {
			return failTimes;
		}

		public void setFailTimes(Integer failTimes) {
			this.failTimes = failTimes;
		}

		public Integer getSucessTimes() {
			return sucessTimes;
		}

		public void setSucessTimes(Integer sucessTimes) {
			this.sucessTimes = sucessTimes;
		}

		public Integer getFirstPay() {
			return firstPay;
		}

		public void setFirstPay(Integer firstPay) {
			this.firstPay = firstPay;
		}

		public String getStop() {
			return stop;
		}

		public void setStop(String stop) {
			this.stop = stop;
		}
	    
 
}