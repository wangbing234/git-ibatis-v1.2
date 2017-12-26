package com.block.core.redis;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.block.core.domain.Busi;
import com.block.core.redis.baseuser.UserCenter;

/**
 * redis服务
 * @author bing.wang
 *
 */
public class RedisSevice {
		//日志模板
		private RedisTemplate<String, Object> redisTemplate;
		//用户的标识字段
		public static final String USER_CENTER="USER_CENTER";
		//用户可访问类型
		public static final String USER_ACCESS_TYPE="USER_ACCESS_TYPE";
	    //商城系统用户的标识字段
	    public static final String USER_TOKEN_MAPPING="USER_TOKEN_MAPPING";
	    //session 超时时间
	    private static final Integer SESSION_TIMEOUT=7;
	    

		public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
			this.redisTemplate = redisTemplate;
		}
		
		/**
		 * 创建token
		 * @param token
		 * @param content  json字符串
		 */
		public String createToken(UserCenter user,String accessSystem){
			String token=UUID.randomUUID().toString().replaceAll("-", "");
			user.setToken(token);
			String userJson = JSON.toJSONString(user);
			// 添加 一个 hash集合
			HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			hash.put(token, USER_CENTER, userJson);
			if(StringUtils.isNotBlank(accessSystem)){
				hash.put(token, USER_ACCESS_TYPE, accessSystem);
			}
			//设置失效时间
			redisTemplate.boundHashOps(token).expire(SESSION_TIMEOUT, TimeUnit.DAYS);
			//增加商户token匹配
			addUserTokenMapping(user.getId()+"", token);
			return userJson;
		}
		
		/**
		 * 创建token
		 * @param token
		 * @param mebId
		 */
		public Boolean checkToken(HttpServletRequest request,String systemType){
			 String token=getToken(request);
			 if (StringUtils.isEmpty(token)) {
		            return false;
		      }
			 UserCenter _user = getUser(token,systemType);
			 if (null==_user) {
		            return false;
		     }
			 redisTemplate.boundHashOps(token).expire(SESSION_TIMEOUT, TimeUnit.DAYS);
		     return true;
		}
		
		/**
		 * 登录退出
		 * @param token
		 * @param mebId
		 */
		 public void deleteToken(HttpServletRequest request) {
			deleteToken(getToken(request));
		 }
		 
		 /**
		 * 登录退出
		 * @param token
		 * @param mebId
		 */
		 public void deleteToken(String token) {
			 UserCenter _user = getUserByToken(token);
			 if(_user!=null){
				 //清空用户记录
				 redisTemplate.delete(token);
				 //清空用户对应的token对
				 removeSupplierTokenMapping(_user.getId()+"");
			 }
		 }
		 
		 
		 
		 public String getToken(HttpServletRequest request) {
			 String token=request.getHeader(Busi.TOKEN);
			 if(StringUtils.isBlank(token)){
					token=request.getParameter("api_key");
				}
//			 token="s_faae18f28ab5471889748253f1d62fb3";
//			 token="a439660c5aa041599909a6af0032a20f";
			 return token;
		 }
		 
		 /**
		  * 内部用户使用，不要调用次方法
		  * @param request
		  * @return
		  */
		 public UserCenter getUserByToken(String token) {
			 UserCenter _user =null;
			 if(StringUtils.isNotBlank(token)) {
				 HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
				 String sAccount = (String)hash.get(token, USER_CENTER);
				 _user = JSONObject.parseObject(sAccount, UserCenter.class);
			 }
			 return _user;
		 }
		 
		 /**
		  * 获取互捐用户
		  * @param request
		  * @return
		  */
		 public UserCenter getUser(String token,String systemType) {
			 UserCenter _user =null;
			 if(StringUtils.isNotBlank(token)) {
				 HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
				 String sAccount = (String)hash.get(token, USER_CENTER);
				 _user = JSONObject.parseObject(sAccount, UserCenter.class);
				 //如果包含就删掉
				 String accessStr = (String)hash.get(token, USER_ACCESS_TYPE);
				 if(!systemType.equals(_user.getSystemType()) 
						 && (StringUtils.isBlank(accessStr) || accessStr.indexOf(systemType)==-1) ) {
					return null;
				 }
			 }
			 return _user;
		 }
		 
		 /**
		  * 获取互捐用户
		  * @param request
		  * @return
		  */
		 public String getUserId(String token,String systemType) {
			 UserCenter _user =getUser(token, systemType);
			 if(_user!=null)
				 return _user.getSystemId();
			 return null;
		 }
		 
		 /**
		  * 获取虚拟化对象
		  * @param token
		  * @param key
		  * @param t
		  * @return
		  */
		 public <T> T getRedisObject(String token,String key,Class<T> t){
			HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			Object sAccount = hash.get(token, key);
			T tObj=null;
			if(null!=sAccount) {
				RedisSerializer<T> hashValueSize = (RedisSerializer<T>) redisTemplate.getHashValueSerializer();
				tObj =hashValueSize.deserialize((byte[]) sAccount);
			}
			return tObj;
		 }
		 
		 
		 /**
		  * 设置redis对象信息
		  * @param token
		  * @param key
		  * @param obj
		  */
		 public void setRedisUserObject(HttpServletRequest request,String key,Object obj,int timeOut){
			 String token=getToken(request);;
			 setRedisObject(token, key, obj);
		 }
		 
		 /**
		  * 设置redis对象信息
		  * @param token
		  * @param key
		  * @param obj
		  */
		 private void setRedisObject(String token,String key,Object obj){
			 byte[] accountByte =null;
			 if(null!=obj) {
				 RedisSerializer<Object> hashValueSize = (RedisSerializer<Object>) redisTemplate.getHashValueSerializer();
				 accountByte = hashValueSize.serialize(obj);
			 }
			HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			hash.put(token, key, accountByte);
		 }
		 
		 /**
		  * 增加用户和token对应
		  * @param token
		  * @param key
		  * @param obj
		  */
		 private void addUserTokenMapping(String userID,String token){
			HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			Object oldToken=hash.get(USER_TOKEN_MAPPING, userID);
			if(oldToken!=null)
				redisTemplate.delete(oldToken.toString());
			hash.delete(USER_TOKEN_MAPPING, userID);
			hash.put(USER_TOKEN_MAPPING, userID, token);
		 }
		 
		 /**
		  * 增加用户和token对应
		  * @param token
		  * @param key
		  * @param obj
		  */
		 public void removeSupplierTokenMapping(String userID){
			 //清空用户对应的token对
			 HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			 hash.delete(USER_TOKEN_MAPPING, userID);
		 }
	

	 
	
	 
}
