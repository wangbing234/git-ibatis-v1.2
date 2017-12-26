/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 商户和用户匹配表-TenantUserMatch
 * @date 2017-11-04 15:44:52
 * @version V1.0
 **/
package com.block.module.font.tenant.tenantusermatch.entity;

import com.block.core.ibatis.beans.PageParms;

/**
 * 商户和用户匹配表数据实体类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
public class TenantUserMatchParms  extends PageParms{  
      
	 /**  
     * 昵称
     */ 
    private Integer tenantPublishId;
    
    /**  
     * 昵称
     */ 
    private Integer tenantId;

	public Integer getTenantPublishId() {
		return tenantPublishId;
	}

	public void setTenantPublishId(Integer tenantPublishId) {
		this.tenantPublishId = tenantPublishId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	

	
    

}