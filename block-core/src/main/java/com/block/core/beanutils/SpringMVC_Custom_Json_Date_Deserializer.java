package com.block.core.beanutils;
import java.io.IOException;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
import com.fasterxml.jackson.core.JsonParser;  
import com.fasterxml.jackson.core.JsonProcessingException;  
import com.fasterxml.jackson.databind.DeserializationContext;  
import com.fasterxml.jackson.databind.JsonDeserializer;  
  
/** 
 *     
 * 项目名称：    
 * 类名称：SpringMVC_Custom_Json_Date_Deserializer    
 * 类描述：    
 * 创建人：    
 * 创建时间：2017年9月19日 下午11:15:52    
 * 修改人：Administrator    
 * 修改时间：2017年9月19日 下午11:15:52    
 * 修改备注：    
 * @version     
 * 
 */  
public class SpringMVC_Custom_Json_Date_Deserializer extends JsonDeserializer<Date> {  
      
    @Override    
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {    
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
                String date = jp.getText();    
                try {    
                    return format.parse(date);    
                } catch (ParseException e) {    
                    throw new RuntimeException(e);    
                }    
    }    
} 