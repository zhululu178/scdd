package cn.scdd.jxc.service.goods.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddGoodsClassMapper;
import cn.scdd.jxc.entity.ScddGoodsClass;
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

	public List<ScddGoodsClass> searchByGoodsClass(ScddGoodsClass goodsClass) {
		// TODO Auto-generated method stub
		return null;
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
