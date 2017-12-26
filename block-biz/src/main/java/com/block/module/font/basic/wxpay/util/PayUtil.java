package com.block.module.font.basic.wxpay.util;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;  
  
public class PayUtil {  
     /**   
     * 签名字符串   
     * @param text需要签名的字符串   
     * @param key 密钥   
     * @param input_charset编码格式   
     * @return 签名结果   
     */     
    public static String sign(String text, String key, String input_charset) {     
        return DigestUtils.md5Hex(getContentBytes(text + "&key=" + key, input_charset));     
    }     
    
    /**
     * 微信回调签名
     * @param smap
     * @param apiKey
     * @return
     */
    public static boolean isWechatSign(Map<String, String> smap,String apiKey) {
    	StringBuffer sb = new StringBuffer(createLinkStringNotSign(smap));
        sb.append("&key=" + apiKey);
        /** 验证的签名 */
        String sign =MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
        /** 微信端返回的合法签名 */
        String validSign = ((String) smap.get("sign")).toUpperCase();
        return validSign.equals(sign);
    }
    /**   
     * @param content   
     * @param charset   
     * @return   
     * @throws SignatureException   
     * @throws UnsupportedEncodingException   
     */     
    public static byte[] getContentBytes(String content, String charset) {     
        if (charset == null || "".equals(charset)) {     
            return content.getBytes();     
        }     
        try {     
            return content.getBytes(charset);     
        } catch (UnsupportedEncodingException e) {     
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);     
        }     
    }     
      
    private static boolean isValidChar(char ch) {     
        if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))     
            return true;     
        if ((ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f))     
            return true;// 简体中文汉字编码     
        return false;     
    }     
    
    /**   
     * 除去数组中的空值和签名参数   
     * @param sArray 签名参数组   
     * @return 去掉空值与签名参数后的新签名参数组   
     */     
    public static Map<String, String> paraFilter(Map<String, String> sArray) {     
        Map<String, String> result = new HashMap<String, String>();     
        if (sArray == null || sArray.size() <= 0) {     
            return result;     
        }     
        for (String key : sArray.keySet()) {     
            String value = sArray.get(key);     
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")     
                    || key.equalsIgnoreCase("sign_type")) {     
                continue;     
            }     
            result.put(key, value);     
        }     
        return result;     
    }     
    
    /**   
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串   
     * @param params 需要排序并参与字符拼接的参数组   
     * @return 拼接后字符串   
     */     
    public static String createLinkString(Map<String, String> params) {     
        List<String> keys = new ArrayList<String>(params.keySet());     
        Collections.sort(keys);     
        String prestr = "";     
        for (int i = 0; i < keys.size(); i++) {     
            String key = keys.get(i);     
            String value = params.get(key);     
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符     
                prestr = prestr + key + "=" + value;     
            } else {     
                prestr = prestr + key + "=" + value + "&";     
            }     
        }     
        return prestr;     
    }     
    
    
    /**   
     * 去掉签名和key
     * @param params 需要排序并参与字符拼接的参数组   
     * @return 拼接后字符串   
     */     
    public static String createLinkStringNotSign(Map<String, String> params) {     
        List<String> keys = new ArrayList<String>(params.keySet());     
        Collections.sort(keys);     
        String prestr = "";     
        for (int i = 0; i < keys.size(); i++) {     
            String key = keys.get(i);     
            String value = params.get(key);    
            if (!"sign".equals(key) && null != value && !"".equals(value) && !"key".equals(key)) {
		            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符     
		                prestr = prestr + key + "=" + value;     
		            } else {     
		                prestr = prestr + key + "=" + value + "&";     
		            }   
            }
        }     
        return prestr;     
    }     
    
    /**   
     *   
     * @param requestUrl请求地址   
     * @param requestMethod请求方法   
     * @param outputStr参数   
     */     
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){     
        // 创建SSLContext     
        StringBuffer buffer = null;     
        try{     
            URL url = new URL(requestUrl);     
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();     
            conn.setRequestMethod(requestMethod);     
            conn.setDoOutput(true);     
            conn.setDoInput(true);     
            conn.connect();     
            //往服务器端写内容     
            if(null !=outputStr){     
                OutputStream os=conn.getOutputStream();     
                os.write(outputStr.getBytes("utf-8"));     
                os.close();     
            }     
            // 读取服务器端返回的内容     
            InputStream is = conn.getInputStream();     
            InputStreamReader isr = new InputStreamReader(is, "utf-8");     
            BufferedReader br = new BufferedReader(isr);     
            buffer = new StringBuffer();     
            String line = null;     
            while ((line = br.readLine()) != null) {     
                buffer.append(line);     
            }     
        }catch(Exception e){     
            e.printStackTrace();     
        }  
        return buffer.toString();  
    }       
    
    public static String urlEncodeUTF8(String source){     
        String result=source;     
        try {     
            result=java.net.URLEncoder.encode(source, "UTF-8");     
        } catch (UnsupportedEncodingException e) {     
            // TODO Auto-generated catch block     
            e.printStackTrace();     
        }     
        return result;     
    }   
    /** 
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。 
     * @param strxml 
     * @return 
     * @throws JDOMException 
     * @throws IOException 
     */  
    public static Map<String,String> doXMLParse(String strxml) throws Exception {  
        if(null == strxml || "".equals(strxml)) {  
            return null;  
        }  
        Map<String,String> map = new HashMap<String,String>();  
        InputStream in = String2Inputstream(strxml);  
        SAXBuilder builder = new SAXBuilder();  
        Document doc = builder.build(in);  
        Element root = doc.getRootElement();  
        List list = root.getChildren();  
        Iterator it = list.iterator();  
        while(it.hasNext()) {  
            Element e = (Element) it.next();  
            String k = e.getName();  
            String v = "";  
            List children = e.getChildren();  
            if(children.isEmpty()) {  
                v = e.getTextNormalize();  
            } else {  
                v = getChildrenText(children);  
            }  
            map.put(k, v);  
        }  
        //关闭流  
        in.close();  
        return map;  
    }  
    /** 
     * 获取子结点的xml 
     * @param children 
     * @return String 
     */  
    public static String getChildrenText(List children) {  
        StringBuffer sb = new StringBuffer();  
        if(!children.isEmpty()) {  
            Iterator it = children.iterator();  
            while(it.hasNext()) {  
                Element e = (Element) it.next();  
                String name = e.getName();  
                String value = e.getTextNormalize();  
                List list = e.getChildren();  
                sb.append("<" + name + ">");  
                if(!list.isEmpty()) {  
                    sb.append(getChildrenText(list));  
                }  
                sb.append(value);  
                sb.append("</" + name + ">");  
            }  
        }  
        return sb.toString();  
    }  
    
    
    public static String map2XmlString(Map<String, String> map) { 
		List<String> keys = new ArrayList<String>(map.keySet());
		String xmlResult = "";
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		for (String key : keys) {
			String value = map.get(key);
			sb.append("<" + key + ">" + value + "</" + key + ">");
		}
		sb.append("</xml>");
		xmlResult = sb.toString();
        return xmlResult;
    }

    
    
    @SuppressWarnings("rawtypes")  
    public static String beanToXML(Object obj)  throws IllegalArgumentException, IllegalAccessException  
    {  
        StringBuffer sb = new StringBuffer("<xml>\n");  
  
        Class clazz = obj.getClass();  
        Field[] fields = clazz.getDeclaredFields();  
  
        //父类Class  
        Class superClass = clazz.getSuperclass();  
        Field[] superFields = superClass.getDeclaredFields();  
  
        //拼装自身的字段和字段值  
        String fieldName = null;  
        Object mapObj = null;  
        for (Field field : fields)  
        {  
            field.setAccessible(true);  
            fieldName = field.getName();//获得字段名  
            mapObj = field.get(obj);//获得字段值  
  
            //如果字段是Map类型，形如ImageRspMsg类中Map字段  
            if (mapObj instanceof Map)  
            {  
                //迭代map集合  
                StringBuffer mapFieldValue = new StringBuffer("");  
                String key = "";  
                Map castMap = (Map)mapObj;  
                Iterator iterator = castMap.keySet().iterator();  
                while (iterator.hasNext())  
                {  
                    //迭代  
                    key = (String)iterator.next();                     
                    mapFieldValue.append("<").append(key).append(">");  
                    //调用value的toString方法  
                    mapFieldValue.append("<![CDATA[").append(castMap.get(key).toString()).append("]]>");  
                    mapFieldValue.append("</").append(key).append(">\n");  
                }  
                sb.append("<").append(fieldName).append(">\n");  
                sb.append(mapFieldValue); //map集合内的迭代结果，勿加CDATA  
                sb.append("</").append(fieldName).append(">\n");  
            }  
            //字段非Map类型，则按照String类型处理（获得value时直接调用toString方法）  
            else  
            {  
                 sb.append("<").append(fieldName).append(">");  
                 sb.append("<![CDATA[").append(mapObj.toString()).append("]]>");  
                 sb.append("</").append(fieldName).append(">\n");  
            }  
        }  
          
        //拼装父类的字段和字段值  
        String superFieldName = "";  
        for (Field field : superFields)  
        {  
            field.setAccessible(true);  
            superFieldName = field.getName();  
  
            sb.append("<").append(superFieldName).append(">");  
            sb.append("<![CDATA[").append(field.get(obj).toString()).append("]]>");  
            sb.append("</").append(superFieldName).append(">\n");  
              
        }  
        sb.append("</xml>");  
  
        return sb.toString();  
    }  
    
    public static InputStream String2Inputstream(String str) {  
        return new ByteArrayInputStream(str.getBytes());  
    }  
}  