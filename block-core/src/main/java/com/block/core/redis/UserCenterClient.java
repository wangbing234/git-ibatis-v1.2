package com.block.core.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.block.core.beanutils.ReflectionUtils;
import com.block.core.common.CoreParamCache;
import com.block.core.framework.dto.ResultBean;
import com.block.core.framework.dto.ResultEnum;
import com.block.core.redis.baseuser.UserCenter;

public class UserCenterClient {
	
	private static String url=null;
//	private static String getUrl(){
////		 url="http://127.0.0.1:9999/user/";
////		 url="http://111.231.146.57:10086/user/";
//		if(null==url)
//			url=CoreParamCache.getInstance().getString("user_center_url");
//		return url;
//	}
//
//	/**
//	 * 注册信息
//	 * @param user 用户对象
//	 * @param token
//	 * @return
//	 */
//	public static ResultBean register(UserCenter user){
//		String jsonStr=HttpClientUtil.doPostObject(getUrl()+"register", user, null);
//		if(StringUtils.isNotBlank(jsonStr))
//			return JSONObject.parseObject(jsonStr, ResultBean.class);
//		return null;
//	}
//	
//	/**
//	 * 手机登录
//	 * @param user 用户对象
//	 * @return
//	 */
//	public static ResultBean login(UserCenter user){
//		String jsonStr=HttpClientUtil.doPostObject(getUrl()+"login", user, null); 
//		ResultBean rb=new ResultBean();
//		rb.setCode(ResultEnum.SERVER_ERROR.getCode());
//		rb.setMsg(ResultEnum.SERVER_ERROR.getMsg());
//		if(StringUtils.isNotBlank(jsonStr))
//		{
//			try {
//				rb= JSONObject.parseObject(jsonStr, ResultBean.class);
//			} catch (Exception e) {
//			}
//			
//		}
//		return rb;
//	}
//	
//	/**
//	 * 用户退出
//	 * @param user 用户对象
//	 * @return
//	 */
//	public static ResultBean exit(String token){
//		String jsonStr=HttpClientUtil.doPostObject(getUrl()+"exit", null, token); 
//		ResultBean rb= null;
//		if(StringUtils.isNotBlank(jsonStr))
//		{
//			try {
//				rb= JSONObject.parseObject(jsonStr, ResultBean.class);
//			} catch (Exception e) {
//			}
//			return rb;
//			
//		}
//		return rb;
//	}
//	
//	/**
//	 * 找回密码
//	 * @param user 用户对象
//	 * @return
//	 */
//	public static ResultBean findPwd(String phone,String password,String systemType,String yzm){
//		Map<String, String> parmsMap=new HashMap<String, String>();
//		parmsMap.put("phone", phone);
//		parmsMap.put("password", password);
//		parmsMap.put("systemType", systemType);
//		parmsMap.put("yzm", yzm);
//		parmsMap.put("email", "");
//		String jsonStr=HttpClientUtil.doPostMap(getUrl()+"findPwd", parmsMap,null); 
//		ResultBean rb= null;
//		if(StringUtils.isNotBlank(jsonStr)){
//			try {
//				rb= JSONObject.parseObject(jsonStr, ResultBean.class);
//			} catch (Exception e) {
//			}
//			return rb;
//		}
//		return rb;
//	}
//	
//	/**
//	 * 发送验证
//	 * @param user 用户对象
//	 * @return
//	 */
//	public static ResultBean yzm(String phone,String systemType){
//		Map<String, String> parmsMap=new HashMap<String, String>();
//		parmsMap.put("phone", phone);
//		parmsMap.put("systemType", systemType);
//		String jsonStr=HttpClientUtil.doPostMap(getUrl()+"yzm", parmsMap, null); 
//		ResultBean rb= null;
//		if(StringUtils.isNotBlank(jsonStr)){
//			try {
//				rb= JSONObject.parseObject(jsonStr, ResultBean.class);
//			} catch (Exception e) {
//			}
//			return rb;
//		}
//		return rb;
//	}
//	
//	/**
//	 * 验证码
//	 * @param user 用户对象
//	 * @return
//	 */
//	public static ResultBean verifycode(String phone,String yzm){
//		Map<String, String> parmsMap=new HashMap<String, String>();
//		parmsMap.put("phone", phone);
//		parmsMap.put("yzm", yzm);
//		String jsonStr=HttpClientUtil.doPostMap(getUrl()+"verifycode", parmsMap, null); 
//		ResultBean rb= null;
//		if(StringUtils.isNotBlank(jsonStr)){
//			try {
//				rb= JSONObject.parseObject(jsonStr, ResultBean.class);
//			} catch (Exception e) {
//			}
//			return rb;
//		}
//		return rb;
//	}
//	
//	/**
//	 * 手机注册
//	 * @param phone 手机类型
//	 * @param password 密码
//	 * @param systemType 默认写 UserType.BLOCK_OA.getType()
//	 * @param systemId  系统唯一id
//	 * @param createToken  1:创建token，0不创建
//	 * @param loginMethod  登录方式见类：loginMethod
//	 * @return
//	 */
//	public static ResultBean register(String phone,String password,String systemType,String systemId,String createToken,String loginMethod){
//		UserCenter user=new UserCenter();
//		user.setPassword(password);
//		user.setSystemType(systemType);
//		user.setSystemId(systemId);
//		user.setToken(createToken);
//		if(StringUtils.isBlank(loginMethod)){
//			loginMethod="phone";
//		}
//		user.setField(loginMethod);
//		ReflectionUtils.setFieldValue(user, loginMethod, phone);
//		return register(user);
//	}
//	
//	/**
//	 * 手机登录
//	 * @param phone 手机类型
//	 * @param password 密码
//	 * @param systemType 默认写 UserType.BLOCK_OA.getType()
//	 * @return
//	 */
//	public static ResultBean login(String phone,String password,String systemType,String loginMethod){
//		UserCenter user=new UserCenter();
//		user.setPassword(password);
//		user.setSystemType(systemType);
//		if(StringUtils.isBlank(loginMethod)){
//			loginMethod="phone";
//		}
//		user.setField(loginMethod);
//		ReflectionUtils.setFieldValue(user, loginMethod, phone);
//		return login(user);
//	}
//	
//	/**
//	 * 修改密码
//	 * @param oldPassword
//	 * @param newPassword
//	 * @param token
//	 * @return
//	 */
//	public static ResultBean changePwd(String oldPassword,String newPassword,String systemType,String token){
//		Map<String, String> parmsMap=new HashMap<String, String>();
//		parmsMap.put("oldPassword", oldPassword);
//		parmsMap.put("newPassword", newPassword);
//		parmsMap.put("systemType", systemType);
//		String jsonStr=HttpClientUtil.doPostMap(getUrl()+"changePwd", parmsMap, token); 
//		ResultBean resultBean=JSONObject.parseObject(jsonStr, ResultBean.class);
//		return resultBean;
//	}
//	
//	public static void main(String[] args) {
//		//测试登录
////				User u = login("13521491410", "123456", "A");
//		//测试登录
////		ResultBean resultBean= changePwd("12345678", "12345678", "7f61b78c5cfe4e259d7903dee8a32e71");
////		System.out.println(resultBean.getMsg());
////		ResultBean resultBean= exit("7f61b78c5cfe4e259d7903dee8a32e71");
//		
////		ResultBean resultBean= yzm("13521491410","A");
////		ResultBean resultBean= findPwd("135214914101","123456","A","999999");
//		ResultBean resultBean=verifycode("13521491410", "4645");
//		System.out.println(resultBean);
//	}
	
	
}
