package cn.e3mall.search.mapper;

import cn.e3mall.pojo.SearchItem;

import java.util.List;

/**
 * Created by cjk on 2017/7/6.
 */
public interface ItemMapper {
    List<SearchItem> getItemList();
    SearchItem getItemById();
}
