package cn.scdd.jxc.service.goods.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddGoodsMapper;
import cn.scdd.jxc.entity.ScddGoods;
import cn.scdd.jxc.entity.ScddGoodsExample;
import cn.scdd.jxc.service.goods.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private ScddGoodsMapper scddGoodsMapper;
	
	public void saveGoods(ScddGoods goods) {
		goods.setModifyDate(new Date());
		if(goods != null && goods.getId() != null) {
			ScddGoods goodsT = this.scddGoodsMapper.selectByPrimaryKey(goods.getId());
			goodsT.setName(goods.getName());
			goodsT.setShortName(goods.getShortName());
			goodsT.setPrice(goods.getPrice());
			goodsT.setActivityPrice(goods.getActivityPrice());
			goodsT.setAgentPrice(goods.getAgentPrice());
			goodsT.setPurchasePrice(goods.getPurchasePrice());
			goodsT.setSupplierId(goods.getSupplierId());
			goodsT.setStockNum(goods.getStockNum());
			goodsT.setClassId(goods.getClassId());
			this.scddGoodsMapper.updateByPrimaryKey(goodsT);
		} else {
			goods.setCreateDate(goods.getModifyDate());
			this.scddGoodsMapper.insert(goods);
		}
	}

	public List<ScddGoods> searchByGoods(ScddGoods member) {
		ScddGoodsExample example = new ScddGoodsExample("g");
		example.setOrderByClause("g.modify_date desc");
		ScddGoodsExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(member.getName())) {
			criteria.andNameLike("%" + member.getName() + "%");
		}
		List<ScddGoods> list = this.scddGoodsMapper.selectByExample(example);
		return list;
	}

	public ScddGoods searchGoodsById(int id) {
		return this.scddGoodsMapper.selectByPrimaryKey(id);
	}

	public boolean checkGoodsExists(ScddGoods record) {
		return false;
	}
}
