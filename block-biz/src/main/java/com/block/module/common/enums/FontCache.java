package com.block.module.common.enums;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.block.module.font.basic.servicetype.service.ServiceTypeService;
import com.block.module.font.basic.systemconf.service.SystemConfService;

@Service("fontCache")
public class FontCache {
	@Resource(name="serviceTypeServiceBasic")
	ServiceTypeService serviceTypeService;
	
	//注入SystemConfService
	@Resource(name="systemConfServiceBasic")
	SystemConfService systemConfService;
	public void refresh(){
		serviceTypeService.refreshServiceType();
		systemConfService.refreshSystemConf();
	}
}
