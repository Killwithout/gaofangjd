package cn.e3mall.content.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;


/**
 * Created by cjk on 2017-6-29.
 */
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    @Override
    public E3Result addContent(TbContent content) {
        //将内容数据插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入到数据库
        contentMapper.insert(content);
        jedisClient.hdel(CONTENT_LIST,content.getCategoryId().toString());
        return E3Result.ok();
    }

    /**
     * 根据内容分类id查询内容列表
    **/

 @Override
    public List<TbContent> getContentListByCid(long cid) {
         //查询缓存
         //如果缓存中有就直接返回
         try {
             String json = jedisClient.hget(CONTENT_LIST, cid + "");
             if (StringUtils.isNotBlank(json)) {
                 List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                 return list;
             }
         }catch (Exception e){
             e.printStackTrace();
         }
         //如果没有就查询数据库
         TbContentExample example = new TbContentExample();
         Criteria criteria = example.createCriteria();
         //设置查询条件
         criteria.andCategoryIdEqualTo(cid);
          //执行查询
         List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
         //把查询结果添加到缓存中
         try{
             jedisClient.hset(CONTENT_LIST,cid+"",JsonUtils.objectTOJson(list));
         }catch (Exception e) {
             e.printStackTrace();
         }
         return list;
    }
}