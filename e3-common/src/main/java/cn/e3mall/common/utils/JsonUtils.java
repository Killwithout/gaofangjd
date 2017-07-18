package cn.e3mall.common.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;

/**
* @author song:
* @version ：2017年6月27日 下午4:15:08
*/
public class JsonUtils {
	  private static final ObjectMapper MAPPER=  new ObjectMapper();
	  public  static String objectTOJson(Object data){
		 try {
			 String string = MAPPER.writeValueAsString(data);
			 return string; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		  return null;
	  }
	  
   /*******************************************
    * 将json结果集转化为对象
    * *********************************
    * */
    public static <T> T jsonToPojo(String jsonData,Class<T> beanType){
    	try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
    
		return null;
    }  
    
    /*
     * 将json数据转换为pojo和list
     * */
    public static <T>List<T> jsonToList(String jsonData,Class<T> beanType){
    	
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
		 List<T> list= MAPPER.readValue(jsonData, javaType);
		 return list;
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
    	
    	return null;

    }
    
    
    
    
}