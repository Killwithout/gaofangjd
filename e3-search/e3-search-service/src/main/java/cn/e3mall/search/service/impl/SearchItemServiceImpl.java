package cn.e3mall.search.service.impl;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cjk on 2017/7/6.
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public E3Result importAllItems() {
        try {
            //查询商品列表
            List<SearchItem> itemList = itemMapper.getItemList( );
            //遍历商品列表
            for (SearchItem searchItem : itemList) {
                SolrInputDocument document = new SolrInputDocument( );
                document.addField("id", searchItem.getId( ));
                document.addField("item_title", searchItem.getTitle( ));
                document.addField("item_price", searchItem.getPrice( ));
                document.addField("item_image", searchItem.getImage( ));
                document.addField("item_categroy_name", searchItem.getCategory_name());
                solrServer.add(document);
            }
            solrServer.commit( );
            return E3Result.ok( );
        }catch (Exception e){
            e.printStackTrace();
            return E3Result.bulid(500,"数据导入时发生异常");
        }
        }
}


