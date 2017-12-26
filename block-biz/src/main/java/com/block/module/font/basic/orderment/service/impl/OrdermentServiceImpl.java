/**
 * Powered By [rapid-framework]
 * Copyright  © 趋快科技(武汉)有限公司
 * @author :bing.wang
 * @Description: 角色表-Orderment
 * @date 2017-11-21 14:36:18
 * @version V1.0
 **/
package com.block.module.font.basic.orderment.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.block.core.domain.Busi;
import com.block.core.ibatis.service.impl.BaseCenterServiceImpl;
import com.block.core.ibatis.sql.criteria.And;
import com.block.core.ibatis.sql.criteria.Restrictions;
import com.block.module.font.basic.orderment.dao.OrdermentDao;
import com.block.module.font.basic.orderment.entity.OrderType;
import com.block.module.font.basic.orderment.entity.Orderment;
import com.block.module.font.basic.orderment.service.OrdermentService;
import com.block.module.font.basic.wxpay.util.PayUtil;
import com.block.module.font.basic.wxpay.util.RandomStringGenerator;
import com.block.module.font.basic.wxpay.util.WxPayConfig;
import com.block.module.font.tenant.tenantusermatch.entity.TenantUserMatch;
import com.block.module.font.tenant.tenantusermatch.service.TenantUserMatchService;


/**
 * 角色表Service实现类
 * @author rapid-code
 * @version 1.0
 * @since 1.0
 * */
@Service("ordermentServiceBasic")
public class OrdermentServiceImpl extends BaseCenterServiceImpl<Orderment,OrdermentDao> implements OrdermentService {
	
	//日志打印类
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//匹配类
	@Resource(name="tenantUserMatchServiceTenant")
	TenantUserMatchService tenantUserMatchService;
	
	
	/** 
     * @Description: 发起微信支付 
     * @param request 
     */  
	 public Map<String, Object> wxPayUserMatch(String openid, HttpServletRequest request,TenantUserMatch tenantUserMatch){  
		 	logger.info("进入微信支付， openid"+openid +",request"+request);
		 	Map<String, Object> result = wxCreateOrder(request, openid, getAmountByFloat(tenantUserMatch.getPriceCal()), OrderType.TENANT_PAY_MATCH, "兼职匹配的订单", tenantUserMatch.getId());
	        return result;   
	}
	 
	 
	 private Integer getAmountByFloat(float fAmount) {
		    int resultAmount=(int)fAmount*100;
		    logger.info("实际付款金额："+resultAmount);
		 	return (int)1*100;
	 }

	
	 private Map<String, Object> wxCreateOrder(HttpServletRequest request,String openid, Integer amount,String type,String productName,Integer busiId) {
	        try{  
	        	Orderment orderment=addOrderInfo(amount, type, productName, busiId);
	            //获取客户端的ip地址  
	            String spbill_create_ip = getIpAddr(request);  
	            String orderNo=orderment.getOrderNo();
	            String nonce_str=orderment.getNonceStr();
	            //组装参数，用户生成统一下单接口的签名  
	            Map<String, String> packageParams = new HashMap<String, String>();  
	            packageParams.put("appid", WxPayConfig.appid);  
	            packageParams.put("body", productName);  
	            packageParams.put("device_info", WxPayConfig.device_info);  
	            packageParams.put("mch_id", WxPayConfig.mch_id);  
	            packageParams.put("nonce_str", nonce_str);  
	            packageParams.put("notify_url", WxPayConfig.notify_url);//支付成功后的回调地址  
	            packageParams.put("openid", openid);  
	            packageParams.put("out_trade_no", orderNo);//商户订单号  
	            packageParams.put("spbill_create_ip", spbill_create_ip);
	            packageParams.put("sign_type", WxPayConfig.SIGNTYPE);
	            packageParams.put("total_fee", amount+"");//支付金额，这边需要转成字符串类型，否则后面的签名会失败  
	            packageParams.put("trade_type", WxPayConfig.TRADETYPE);//支付方式  
	            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串   
	             //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口  
	            String mysign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();  
	             
	            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去  
	            packageParams.put("sign", mysign);
                String xml=PayUtil.map2XmlString(packageParams);
	             
	            logger.info("调试模式_统一下单接口 请求XML数据：" + xml);  
	  
	            //调用统一下单接口，并接受返回的结果  
	            String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);  
	              
	            logger.info("调试模式_统一下单接口 返回XML数据：" + result);
	              
	            // 将解析结果存储在HashMap中     
	            Map<String,String> map = PayUtil.doXMLParse(result);  
	              
	            String return_code = (String) map.get("return_code");//返回状态码  
	              
	            Map<String, Object> response = new HashMap<String, Object>();//返回给小程序端需要的参数  
	            if(return_code=="SUCCESS"||return_code.equals(return_code)){     
	                String prepay_id =  map.get("prepay_id");//返回的预付单信息     
					response.put("nonceStr", nonce_str);  
	                response.put("package", "prepay_id=" + prepay_id);  
	                Long timeStamp = System.currentTimeMillis() / 1000;     
	                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误  
	                //拼接签名需要的参数  
	                String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=MD5&timeStamp=" + timeStamp;     
	                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法  
	                String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();  
	                  
	                response.put("paySign", paySign);  
	            }  
	            response.put("appid", WxPayConfig.appid);  
	            return response;  
	        }catch(Exception e){
	            e.printStackTrace();  
	        }  
	        return null;  
	}
	 

	@Override
	public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		// sb为微信返回的xml
		String notityXml = sb.toString();
		String resXml = "";
		logger.info("接收到的报文：" + notityXml);
		Map<String, String> map = PayUtil.doXMLParse(notityXml);
		String returnCode = (String) map.get("return_code");
		if ("SUCCESS".equals(returnCode)) {
			boolean signResult = PayUtil.isWechatSign(map, WxPayConfig.key);
			System.out.println("签名结果：" + signResult);
			// 验证的意义在于，是没有登录的，需要签名验证
			if (signResult) {
				// 订单号
				boolean isSuccess = updateStatuByNo(map.get("out_trade_no"));
				String SUCCESS = isSuccess ? "SUCCESS" : "FAIL";
				/** 此处添加自己的业务逻辑代码end **/
				// 通知微信服务器已经支付成功
				resXml = "<xml><return_code><![CDATA[" + SUCCESS + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml> ";
			}
		} else {
			resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
		}
		logger.info("微信支付回调数据结束 ：" + resXml);

		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(resXml.getBytes());
		out.flush();
		out.close();
	}
	
	
	public Orderment getOrderByBusiType(String type,Integer busiId){
		And where =new And("busiId", busiId,Restrictions.EQ);
		where.add("type", type, Restrictions.EQ);
		return this.baseDao.get(where);
	}
	
	
	public Orderment getOrderByNo(String orderNo){
		And where =new And("orderNo", orderNo,Restrictions.EQ);
		return this.baseDao.get(where);
	}
	
	public Boolean updateStatuByNo(String orderNo){
		And where =new And("orderNo", orderNo,Restrictions.EQ);
		Orderment orderment= this.baseDao.get(where);
		if(orderment!=null && Busi.NO_STRING.equalsIgnoreCase(orderment.getStatus())){
			String orderType=orderment.getType();
			switch (orderType) {
			case OrderType.TENANT_PAY_MATCH://租户完成付款
				tenantUserMatchService.payMatch(orderment.getBusiId());
				break;
			case OrderType.USER_DEPOSIT://用户押金
				
				break;
			case OrderType.TENANT_DEPOSIT://租户押金
							
				break;
			case OrderType.GUEST_DEPOSIT://客户押金
				
				break;
			default:
				break;
			}
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("status", Busi.YES_STRING);
			map.put("updatetime", new Date());
			baseDao.update(orderment.getId(), map);
		}
		return false;
	}
	
	
	public Orderment addOrderInfo(float amount,String type,String productName,Integer busiId){
		Orderment orderment=getOrderByBusiType(type, busiId);
		if(orderment==null){
			orderment=new Orderment();
			orderment.setAmount(amount);
			orderment.setBusiId(busiId);
			orderment.setCreatetime(new Date());
			orderment.setOrderNo(RandomStringGenerator.getRandomStringByLength(3)+RandomStringGenerator.getRandomIntegerByLength(26)); 
			orderment.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
			orderment.setStatus(Busi.NO_STRING);
		}
		else {
			//如果有更新付款
			this.baseDao.update(orderment.getId(), "createtime", new Date());
		}
		return orderment;
	}
	
    /** 
     * IpUtils工具类方法 
     * 获取真实的ip地址 
     * @param request 
     * @return 
     */  
    public static String getIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){  
             //多次反向代理后会有多个ip值，第一个ip才是真实ip  
            int index = ip.indexOf(",");  
            if(index != -1){  
                return ip.substring(0,index);  
            }else{  
                return ip;  
            }  
        }  
        ip = request.getHeader("X-Real-IP");  
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){  
           return ip;  
        }  
        return request.getRemoteAddr();  
    }  
}
