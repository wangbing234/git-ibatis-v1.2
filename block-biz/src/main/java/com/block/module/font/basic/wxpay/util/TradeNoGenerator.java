package com.block.module.font.basic.wxpay.util;
/** 
 * 交易码生成器 
 * @author wx 
 * 
 */  
public class TradeNoGenerator {  
  
    public static String generator(String tradeType) {  
        StringBuffer tradeNo = new StringBuffer();  
        tradeNo.append(tradeType);  
        tradeNo.append(System.currentTimeMillis());  
        tradeNo.append(RandomStringGenerator.getRandomIntegerByLength(14));  
        return tradeNo.toString();  
    }  
      
    public static void main(String[] args) {  
        System.out.println(TradeNoGenerator.generator("JSAPI"));  
    }  
}  