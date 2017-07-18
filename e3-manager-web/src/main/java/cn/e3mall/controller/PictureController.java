package cn.e3mall.controller;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by song on 2017-6-29.
 */
@Controller
public class PictureController {
     @Value("${IMGE_SERVER_URL}")
     private  String IMGE_SERVER_URL;
     @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
     @ResponseBody
     public String uploadFile(MultipartFile uploadFile){
         try {
             FastDFSClient fastDFSClient = new FastDFSClient("clsspath:conf/client.conf");
             String originalFilename = uploadFile.getOriginalFilename( );
             String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
             String url = fastDFSClient.uploadFile(uploadFile.getBytes( ), extName);
             url=IMGE_SERVER_URL+url;
             Map result = new HashMap<>();
             result.put("error",0);
             result.put("url",url);
             return JsonUtils.objectTOJson(result);
         } catch (Exception e) {
             e.printStackTrace( );
             Map result=new HashMap<>();
             result.put("error",1);
             result.put("message","图片上传失败");
             return  JsonUtils.objectTOJson(result);

         }



     }





}
