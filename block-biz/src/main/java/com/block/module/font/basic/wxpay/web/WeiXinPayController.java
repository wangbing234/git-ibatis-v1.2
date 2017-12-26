/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 公关表扩展表-UserExtend
 * @date 2017-11-04 15:45:10
 * @version V1.0
 **/
package com.block.module.font.basic.wxpay.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.block.module.font.basic.mebbasic.web.MebBasicController;
import com.block.module.font.basic.orderment.service.OrdermentService;


/**
 * 微信支付：押金，定金，付款
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Controller
@ResponseBody
@RequestMapping("/weixin")//域/模块
public class WeiXinPayController  extends MebBasicController{
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="ordermentServiceBasic")
	OrdermentService ordermentService;
	
	 
    /** 
     * @Description:微信支付回调
     * @return 
     * @throws Exception  
     */  
    @ResponseBody
    @RequestMapping(value="/wxNotify")
    public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	logger.info("收到微信回调！"); 
    	ordermentService.wxNotify(request, response);
    }  
	    
	 
}
