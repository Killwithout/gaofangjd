package cn.e3mall.service.impl;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
*
*/
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public List<EasyUITreeNode> getItemCatlist(long parentid) {
	    TbItemCatExample example = new TbItemCatExample();
	    Criteria criteria = example.createCriteria();
	    criteria.andParentIdEqualTo(parentid);
	    List<TbItemCat> list = itemCatMapper.selectByExample(example);
	    List<EasyUITreeNode> resultList = new ArrayList<>();
	    for(TbItemCat tbItemCat :list){
	    	EasyUITreeNode node = new EasyUITreeNode();
	    	node.setId(tbItemCat.getId());
	    	node.setText(tbItemCat.getName());
	    	node.setState(tbItemCat.getIsParent()?"closed":"open");
	    	resultList.add(node);
	    }
		return resultList;
	}
}
