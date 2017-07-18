package cn.e3mall.item.controller;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by cjk on 2017/7/17.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    public String showItemInfo(@PathVariable Long itemid, Model model){
        TbItem tbItem = itemService.getItemById(itemid);
        Item item = new Item(tbItem);
        TbItem itemDesc = itemService.getItemById(itemid);
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }
}
