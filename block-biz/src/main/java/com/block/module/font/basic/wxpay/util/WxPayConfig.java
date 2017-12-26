package com.block.module.font.basic.wxpay.util;
/** 
 * http://blog.csdn.net/zhourenfei17/article/details/77765585
 * 小程序微信支付的配置文件 
 * @author  
 * 
 */  
public class WxPayConfig {  
    //小程序appid  
    public static final String appid = "wx4620bef7e96b1c91";  
    //微信支付的商户id  
    public static final String mch_id = "1494116742";
    
    //微信app秘钥  
    public static final String secret = "5d6e39582d18457c4e00aed937ef7006";
    
    //微信支付的商户密钥  
    public static final String key = "huihuangwangbingxiezongh20171218";
    
    //设备号
    public static final String device_info = "013463457045764";
    
    public static final String authorization_code="authorization_code";
    //支付成功后的服务器回调url  http://huihuang99.com.cn/
    public static final String notify_url = "https://huihuang99.com.cn/weixin/wxNotify";
    
 
    //签名方式，固定值  
    public static final String SIGNTYPE = "MD5";  
    //交易类型，小程序支付的固定值为JSAPI  
    public static final String TRADETYPE = "JSAPI";  
    //微信统一下单接口地址  
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //微信统一下单提款接口                                
    public static final String exit_url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    
    public static final String jscode2session = "https://api.weixin.qq.com/sns/jscode2session";
    
}  