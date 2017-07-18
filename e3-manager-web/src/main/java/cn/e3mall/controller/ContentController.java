package cn.e3mall.controller;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cjk on 2017/6/30.
**/
@Controller
public class ContentController {
	@Autowired
    private ContentService contentService;
    
    @RequestMapping(value = "/content/save",method = RequestMethod.POST)
    @ResponseBody
    
    public E3Result addContent(TbContent content){
        E3Result e3Result = contentService.addContent(content);
        return  e3Result;
    }
}
