package cn.e3mall.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * @author cjk:
 * @version ：2017年6月27日 下午4:43:11
 */
public class E3Result implements Serializable {
    private static final ObjectMapper MAPPER = new ObjectMapper( );
    private Integer status;
    private String msg;
    private Object data;

    public static E3Result bulid(Integer status, String msg, Object data) {
        return new E3Result(status, msg, data);
    }

    public static E3Result ok(Object data) {
        return new E3Result(data);
    }

    public static E3Result ok() {

        return new E3Result(null);
    }

    public E3Result() {

    }

    public static E3Result bulid(Integer status, String msg) {

        return new E3Result(status, msg, null);
    }

    public E3Result(Integer status, String msg, Object data) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public E3Result(Object data) {
        this.status = 200;
        this.msg = "ok";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {

        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public static E3Result formatToPojo(String jsonData, Class<?> clazz) {

        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, E3Result.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject( )) {
                    obj = MAPPER.readValue(data.traverse( ), clazz);
                } else if (data.isTextual( )) {
                    obj = MAPPER.readValue(data.asText( ), clazz);
                }
                return bulid(jsonNode.get("status").intValue( ), jsonNode.get("msg").asText( ), obj);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /*
    * 没有object对象的转化
    *
    * */
    public static E3Result format(String json) {
        try {
            return MAPPER.readValue(json, E3Result.class);
        } catch (IOException e) {
            e.printStackTrace( );
        }

        return  null;
    }
    /*
      object 是集合转化
    * */
    public static E3Result formatToList(String jsonData,Class<?> clazz){
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj=null;
            if (data.isArray()&&data.size()>0){
                MAPPER.readValue(data.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class,clazz
                ));
                return bulid(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
            }
        } catch (Exception e) {
               e.printStackTrace();
        }
         return  null;

    }
}