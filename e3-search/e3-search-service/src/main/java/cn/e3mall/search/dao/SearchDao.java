package cn.e3mall.search.dao;

import cn.e3mall.common.pojo.SeachResult;
import cn.e3mall.common.pojo.SearchItem;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cjk on 2017/7/6.
 */
@Repository
public class SearchDao {
    @Autowired
    private SolrServer solrServer;
    //@Autowired
    //private SeachResult seachResult;
    public SeachResult search(SolrQuery query) throws Exception {
        //根据query查询索引库
        QueryResponse queryResponse = solrServer.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //取查询结果总计数
        Long numFound = solrDocumentList.getNumFound();
        SeachResult result = new SeachResult();
        result.setRecordCount(numFound);
        //取商品列表
        Map<String, Map<String, List<String>>> hightlighting = queryResponse.getHighlighting();
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setId((String) solrDocument.get("id"));
            item.setCategroy_name((String) solrDocument.get("item_Categroy_name"));
            item.setImage((String) solrDocument.get("item_Image"));
            item.setPrice((Long) solrDocument.get("item_Price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            List<String> list = hightlighting.get(solrDocument.get("id")).get("item_Categroy_name");
            String title = "";
            if (list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
                itemList.add(item);
            }
            result.setItemList(itemList);
        }
        return result;
    }
}
