package cn.e3mall.content.service.impl;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 ** Created by cjk on 2017/6/30.
**/
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{
       @Autowired
       private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample( );
        TbContentCategoryExample.Criteria criteria = example.createCriteria( );
         //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> nodeList = new ArrayList<>( );
        for (TbContentCategory tbContentCategory:catList){
            EasyUITreeNode node = new EasyUITreeNode( );
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            nodeList.add(node);
        }
        return  nodeList;
    }

    @Override
    public E3Result addContentCategory(long parentId, String name) {
        //创建一个tb_content_category表对应的pojo对象
        TbContentCategory contentCategory = new TbContentCategory( );
        //设置pojo属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //1.正常 2.删除
        contentCategory.setStatus(1);
        //排序
        contentCategory.setSortOrder(1);
        //对添加的节点让他一定是叶子节点
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //插入数据库
        contentCategoryMapper.insert(contentCategory);
        //判断父节点的isparent属性        如果不是true我们改为true
        //根据parentid判断父节点
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()){
            parent.setIsParent(true);
            //更新到数据库
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
         //返回结果返回E3result 包含pojo
        return E3Result.ok(contentCategory);
    }
}

