package com.block.module.font.basic.wxpay.util;

import java.security.AlgorithmParameters;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weixin4j.WeixinException;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import com.block.module.font.basic.wxpay.entity.OAuthJsToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import net.sf.json.JSONObject;

/**
 * 微信小程序信息获取
 * https://www.cnblogs.com/tianzhongshan/p/6593012.html
 * @author Administrator
 * @Date 2017年2月16日 11:56:08
 */
public class WXAppletUserInfo {
	
	//日志打印类
	private  static Logger logger = LoggerFactory.getLogger(WXAppletUserInfo.class);
	
	public static OAuthJsToken getSessionKeyOrOpenid(String code) throws Exception {
		//拼接参数    
	     String param = "?grant_type=" + WxPayConfig.authorization_code + "&appid=" + WxPayConfig.appid + "&secret=" + WxPayConfig.secret + "&js_code=" +code;    
	         
	     String openUrl=WxPayConfig.jscode2session + param;    
	     //创建请求对象    
	     HttpsClient http = new HttpsClient();    
	     //调用获取access_token接口    
	     Response res = http.get(openUrl);    
	     //根据请求结果判定，是否验证成功    
	     JSONObject jsonObj = res.asJSONObject();    
	     if (jsonObj != null) {    
	         Object errcode = jsonObj.get("errcode");    
	         if (errcode != null) {     
	             //返回异常信息    
	        	 logger.info("没有获取到用户信息"); 
	             throw new WeixinException(""+Integer.parseInt(errcode.toString()));    
	         }    
	             
	         ObjectMapper mapper = new ObjectMapper();    
	         OAuthJsToken oauthJsToken = mapper.readValue(jsonObj.toString(),OAuthJsToken.class);    
	       return oauthJsToken;
	     }
	     return null;
	}  

	/**
	 * 解密用户敏感数据获取用户信息
	 * 
	 * @param sessionKey
	 *            数据进行加密签名的密钥
	 * @param encryptedData
	 *            包括敏感数据在内的完整用户信息的加密数据
	 * @param iv
	 *            加密算法的初始向量
	 * @return
	 */
	public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {
		// 被加密的数据
		byte[] dataByte = Base64.decode(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decode(sessionKey);
		// 偏移量
		byte[] ivByte = Base64.decode(iv);

		try {
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.fromObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}