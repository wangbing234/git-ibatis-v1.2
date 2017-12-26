/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配表-TenantUserMatch
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantusermatch.entity;

import com.block.core.beanutils.BlockBeanUtils;
import com.block.core.ibatis.util.date.DateUtil;
import com.block.module.common.enums.CommonManager;

/**
 * 商户和用户匹配表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public class TenantUserMatchResult  extends TenantUserMatch{  
      
	 /**  
     * 昵称
     */ 
    private String nickname;
    
    /**  
     * 头像
     */ 
    private String profile;
    
    /**
     * 手机号
     */
    private String phone;
    
    public String getPayTypeName()
    {
    	return CommonManager.getPayTypeName(getPayType());
    }
    
    public String getCreatetimeString() {
        return DateUtil.getDateTimeString(getCreatetime());  
    }
    
    
//	public static int USER_ACCEPT=1;//用户接受-
//	public static int TENANT_ACCEPT=2;//商户接受
//	public static int USER_ARRIVE_NOTICE=3;//用户确认到达-
//	public static int TENANT_ARRIVE_NOTICE=4;//商户确认达到（开始计数消费）
//	public static int TENANT_FINISH_NOTICE=5;//商户确认完成（待付款）
//	public static int TENANT_PAYD_NOTICE=6;//已经完成，待评价
//	public static int TENANT_COMMENT=9;//已评价
//	
//	public static int TENANT_CANCEL=7;//商户取消
//	public static int USER_CANCEL=8;//用户取消-
    public String getStatusName() {
    	if(status==null)
    		return "未知";
    	switch (status) {
		case 1:
			return "用户接受";
		case 2:
			return "商户接受";
		case 3:
			return "用户到达";
		case 4:
			return "商户确认到达";
		case 5:
			return "确认完成";
		case 6:
			return "待评价";
		case 7:
			return "商户取消";
		case 8:
			return "用户取消";
		case 9:
			return "已评价";
		}
		return "未知";
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}