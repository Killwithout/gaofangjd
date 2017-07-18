package cn.e3mall.search.message;
import cn.e3mall.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by cjk on 2017/7/11.
 */
public class ItemAddMessageListener implements MessageListener{
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrServer solrServer;
    @Override
    public void onMessage(Message message){
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            Long itemId = new Long(text);
            Thread.sleep(100);
            //索引库
            SolrInputDocument document = new SolrInputDocument();
            SearchItem searchItem=itemMapper.getItemById();
            //将其添加到索引库
            document.addField("id",searchItem.getId());
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_categroy_name",searchItem.getCategory_name());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}