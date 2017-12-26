package com.block.core.redis;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.block.core.domain.Busi;  
/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  
	
	/**
	 * @param url
	 * @param 对象或map
	 * @param token
	 * @return
	 */
    public static String doPostMap(String url,Map<String,String> map,String token){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new DefaultHttpClient();  
            httpPost = new HttpPost(url);  
            if(StringUtils.isNotBlank(token)){
            	httpPost.addHeader(Busi.TOKEN, token);
			}
       	 //设置参数  
           List<NameValuePair> list = new ArrayList<NameValuePair>();  
           Iterator iterator = map.entrySet().iterator();  
           while(iterator.hasNext()){  
               Entry<String,String> elem = (Entry<String, String>) iterator.next();  
               list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
           }  
           if(list.size() > 0){  
               UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");  
               httpPost.setEntity(entity);  
           }  
            
            HttpResponse response = httpClient.execute(httpPost);  
            int statusCode = response.getStatusLine().getStatusCode();
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,"UTF-8");  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }  
    
    
    /**
  	 *post请求
  	 * @param url
  	 * @param obj
  	 * @return
  	 */
  	public static String doPostObject(String url, Object obj, String token) {
  		DefaultHttpClient httpClient = new DefaultHttpClient();
  		try {
  			HttpPost request = new HttpPost(url);
  		
  			request.addHeader("content-type", "application/json");
  			request.addHeader("Accept", "application/json");
  			if(StringUtils.isNotBlank(token)){
  				request.addHeader(Busi.TOKEN, token);
  			}
  			
  			if(null!=obj){
  				String str = JSON.toJSONString(obj);
  				StringEntity params = new StringEntity(str, "UTF-8");
  				request.setEntity(params);
  			}
  			
  			HttpResponse response = httpClient.execute(request);
  			int statusCode = response.getStatusLine().getStatusCode();
  			if (response != null) {
  				String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
  				System.out.println("返回json： "+responseBody); 
  				return responseBody;
  			}
  		} catch (Exception ex) {
  			ex.printStackTrace();
  			throw new RuntimeException();
  		} finally {
  			httpClient.getConnectionManager().shutdown();
  		}
  		return null;
  	}
}  