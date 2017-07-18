package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SeachResult;

/**
 * Created by cjk on 2017/7/6.
 */
public interface SearchService {
    SeachResult search(String keyword, int page, int rows);
}
