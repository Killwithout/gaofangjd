package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.content.service.ContentService;

/**
 * Created by cjk on 2017/6/30.
 */
@Controller
public class ContentCatController{
		@Autowired
		private ContentCategoryService contentCategoryService;

		@RequestMapping
	    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(name="id",defaultValue="0")long parseid){
        List<EasyUITreeNode> list=contentCategoryService.getContentCatList(parseid);
        return list;
    }

    /**
     * 添加分类节点
     * @param parenid
     * @param name
     * @return
     */
    @RequestMapping(value = "content/category/creat",method = RequestMethod.POST)
    @ResponseBody
    public E3Result CreateContentCategory(Long parseid,String name){
        E3Result e3Result = contentCategoryService.addContentCategory(parseid,name);
        return e3Result;
    }
}
