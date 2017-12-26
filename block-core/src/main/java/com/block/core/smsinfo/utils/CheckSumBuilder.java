package com.block.core.smsinfo.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.block.core.common.CoreParamCache;
import com.block.core.smsinfo.service.impl.SmsInfoServiceImpl;

public class CheckSumBuilder {
	// 计算并获取CheckSum
	private static String getCheckSum(String appSecret, String nonce, String curTime) {
		return encode("sha1", appSecret + nonce + curTime);
	}

	// 计算并获取md5值
	private static String getMD5(String requestBody) {
		return encode("md5", requestBody);
	}

	private static String encode(String algorithm, String value) {
		if (value == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(value.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String sendSms(String url, Map<String, Object> args) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		CoreParamCache coreParamCache = CoreParamCache.getInstance();
		String appKey = coreParamCache.getString("smsAppKey");
		String appSecret = coreParamCache.getString("smsAppSecret");
		String nonce = "0987654321";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);// 参考计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> entry : args.entrySet()) {
			nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);

		// 返回执行结果
		return EntityUtils.toString(response.getEntity(), "utf-8");
	}
	
	public static void main(String[] args) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(SmsInfoServiceImpl.SMS_SEND_URL);
		String appKey = "9db4008dd4635cfa645ff36e14aa0efd";
		String appSecret = "9bea3f3a0635";
		String nonce = "0987654321";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);// 参考计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		Map<String, Object> smsArgs = new HashMap<String, Object>();
		smsArgs.put("templateid", "3058450");
		smsArgs.put("mobiles", JSON.toJSONString(new String[] { "13521491410" }));
		smsArgs.put("params", JSON.toJSONString(new String[] {"aaaa"}));

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> entry : smsArgs.entrySet()) {
			nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);

		// 返回执行结果
		System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
	}
}
