package cn.scdd.jxc.service.goods.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddGoodsClassMapper;
import cn.scdd.jxc.entity.ScddGoodsClass;
import cn.scdd.jxc.entity.ScddGoodsClassExample;
import cn.scdd.jxc.service.goods.GoodsClassService;

@Service("goodsClassService")
public class GoodsClassServiceImpl implements GoodsClassService {
	@Autowired
	private ScddGoodsClassMapper scddGoodsClassMapper;
	
	public void saveGoodsClass(ScddGoodsClass goodsClass) {
		if(goodsClass != null && goodsClass.getId() != null) {
			ScddGoodsClass goodsClassT = this.scddGoodsClassMapper.selectByPrimaryKey(goodsClass.getId());
			goodsClassT.setName(goodsClass.getName());
			goodsClassT.setParentId(goodsClass.getParentId());
			this.scddGoodsClassMapper.updateByPrimaryKey(goodsClassT);
		} else {
			this.scddGoodsClassMapper.insert(goodsClass);
		}
	}

	public List<ScddGoodsClass> searchAll() {
		ScddGoodsClassExample example = new ScddGoodsClassExample();
		example.setOrderByClause("PARENT_ID, id");
		List<ScddGoodsClass> list = this.scddGoodsClassMapper.selectByExample(example);
		
		List<ScddGoodsClass> reList = new ArrayList<ScddGoodsClass>();
		if(list != null && list.size() >0) {
			for(ScddGoodsClass goodsClass : list) {
				if(goodsClass.getParentId() == null) {//跟节点
					reList.add(goodsClass);
				} else {
					this.addNode(reList, goodsClass);
				}
			}
		}
		return reList;
	}

	/**
	 * 将子节点放入列表中
	 * @param list
	 * @param goodsClass
	 */
	private void addNode(List<ScddGoodsClass> list, ScddGoodsClass goodsClass) {
		for(ScddGoodsClass parent : list) {
			if(parent.getId() == goodsClass.getParentId()) {//当前节点的父节点
				if(parent.getChildren() == null) {//新增节点信息
					parent.setChildren(new ArrayList<ScddGoodsClass>());
				}
				parent.getChildren().add(goodsClass);
			} else {
				if(parent.getChildren() != null && parent.getChildren().size() >0) {//从子节点中查找
					this.addNode(parent.getChildren(), goodsClass);
				}
			}
		}
	}
	
	public ScddGoodsClass searchGoodsClassById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkGoodsClassExists(ScddGoodsClass record) {
		// TODO Auto-generated method stub
		return false;
	}

}
