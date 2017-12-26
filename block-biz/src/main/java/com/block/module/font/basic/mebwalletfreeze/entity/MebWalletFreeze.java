/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公用会员财务表-MebWalletFreeze
 * @date 2017-11-19 18:10:41
 * @version V1.0
 **/
package com.block.module.font.basic.mebwalletfreeze.entity;
import com.block.core.ibatis.annotation.po.TableName;
import com.block.core.ibatis.annotation.po.FieldName;
import javax.validation.constraints.NotNull;
import com.block.core.ibatis.beans.Po;
import com.block.core.ibatis.util.date.DateUtil;
import java.util.Date;
/**
 * 公用会员财务表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@TableName(name="f_meb_wallet_freeze")
public class MebWalletFreeze  extends Po{  
      
	    /**  
	     * 
	     */ 
	    private Integer id;
	    /**  
	     * 用户id
	     */ 
    	@FieldName(name="user_id")
	    private Integer userId;
	    /**  
	     * 
	     */ 
    	@FieldName(name="user_type")
	    private Integer userType;
	    /**  
	     * 资金多少
	     */ 
	    private Float amount;
	    /**  
	     * 是否解冻 y是，n否
	     */ 
	    private String freeze;
	    
	    /**  
	     * 停止解冻 y是，n否
	     */ 
	    private String stop;
	    /**  
	     * 到期解冻时间
	     */ 
	    private Date freezetime;
	    
	    /**  
	     * 
	     */ 
	    private Date createtime;
 
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
	    public void setUserType(Integer userType) {  
	        this.userType = userType;  
	    }
	      
	    public Integer getUserType() {
	        return this.userType;  
	    }
	    public void setAmount(Float amount) {  
	        this.amount = amount;  
	    }
	      
	    public Float getAmount() {
	        return this.amount;  
	    }
	    public void setFreeze(String freeze) {  
	        this.freeze = freeze;  
	    }
	      
	    public String getFreeze() {
	        return this.freeze;  
	    }
	    public String getFreezetimeString() {
	        return DateUtil.formatDatetime(getFreezetime());  
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

		public String getStop() {
			return stop;
		}

		public void setStop(String stop) {
			this.stop = stop;
		}

		public Date getFreezetime() {
			return freezetime;
		}

		public void setFreezetime(Date freezetime) {
			this.freezetime = freezetime;
		}
 
	    
}