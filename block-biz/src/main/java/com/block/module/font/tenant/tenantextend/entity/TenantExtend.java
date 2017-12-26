package com.block.module.font.tenant.tenantextend.entity;

import com.block.module.common.enums.BusiUserType;
import com.block.module.font.basic.mebbasic.entity.MebBasic;

public class TenantExtend extends MebBasic {

	   public Integer getType() {
	        return BusiUserType.TENANT;  
	   }
		
	   
}
