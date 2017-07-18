package cn.e3mall.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by cjk on 2017/7/12.
 */
public class MySolrTest{
    @Test
    public void addDocument()throws Exception{
        //1.创建solr对象
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.30.196:8080/solr/connection1");
        //2.创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        //3.向文档对象添加域
        document.addField("id","doc1");
        document.addField("item_name","测试商品");
        document.addField("item_price",1000);

        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void deleteDocument()throws Exception{
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.30.196:8080/solr/connection1");
        solrServer.deleteByQuery("dec1");
        solrServer.commit();
    }

    @Test
    public void queryIndex() throws Exception {
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.30.196:8080");
        SolrQuery query = new SolrQuery();
        query.set("q",":");
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList results = queryResponse.getResults();
        long numFound = results.getNumFound();
        System.out.println("查询的记录数："+numFound);
        for (SolrDocument solrDocument: results) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println(solrDocument.get("item_categrory_name"));
        }
    }

    @Test
    public void queryIndexFuza() throws SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.30.196:8080");
        SolrQuery query = new SolrQuery();
        query.setQuery("手机");
        query.setStart(0);
        query.setRows(20);
        query.set("df","item_title");
        query.setHighlight(true);
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList results = queryResponse.getResults();
        long resultsNumFound = results.getNumFound();
        System.out.println("查询结果："+resultsNumFound);
        Map<String, Map<String,List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument solrDocument:results) {
            System.out.println(solrDocument.get("id"));
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title="";
            if (list != null && list.size()>0){
                title=list.get(0);
            }else{
                title=(String)solrDocument.get("item_title");
            }
            System.out.println(title);
            System.out.println("item_sell_point");
            System.out.println("item_price");
            System.out.println("item_image");
            System.out.println("item_category_name");
        }
    }
}



