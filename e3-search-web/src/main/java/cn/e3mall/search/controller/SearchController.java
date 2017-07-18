package cn.e3mall.search.controller;

import cn.e3mall.common.pojo.SeachResult;
import cn.e3mall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by cjk on 2017/7/7.
 */

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    //@Autowired
    //private SeachResult seachResult;
    @Value("${SEARCH_RESULT_ROWS}")
    private Integer SEARCH_RESULT_ROWS;

    @RequestMapping("/search")
    public String searchItemList(String keyword , @RequestParam(defaultValue = "1") Integer page , Model model)throws Exception{
        keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
        SeachResult seachResult = searchService.search(keyword, page, SEARCH_RESULT_ROWS);
        model.addAttribute("query",keyword);
        model.addAttribute("totalPages",seachResult.getTotalPages());
        model.addAttribute("page",page);
        model.addAttribute("recordCount",seachResult.getRecordCount());
        model.addAttribute("itemList",seachResult.getItemList());
        return "search";
    }
}
