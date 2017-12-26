package com.block.module.font.guest.guestsextend.entity;

import com.block.module.common.enums.BusiUserType;
import com.block.module.font.basic.mebbasic.entity.MebBasic;

public class GuestExtend extends MebBasic {

	   public Integer getType() {
	        return BusiUserType.GUEST;  
	    }
}
