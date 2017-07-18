package cn.e3mall.search.service.impl;

import cn.e3mall.common.pojo.SeachResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cjk on 2017/7/7.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    @Override
    public SeachResult search(String keyword, int page, int rows) {
        //创建一个solrQuery对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(keyword);
        //设置分页条件
        if(page<=0){
            page=1;
            query.setStart((page-1)*rows);
            query.setRows(rows);
            //开启默认搜索板
            query.set("df","item_title");
            //开启高亮显示
            query.setHighlight(true);
            query.setHighlightSimplePre("<em style=\"color:red\">");
            query.setHighlightSimplePost("</em>");
            //执行查询
            SeachResult seachResult=null;
            try{
                seachResult = searchDao.search(query);
            }catch (Exception e){
                e.printStackTrace();
            }
            //计算总页数
            Long recordCount = seachResult.getRecordCount();
            int totalpage = (int) (recordCount / rows);
            if (recordCount % rows > 0) {
                totalpage++;
                //添加返回结果
                seachResult.setTotalPages(totalpage);
                return seachResult;
            }
        }
        return null;
    }
}
