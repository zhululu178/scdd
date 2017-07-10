package cn.scdd.jxc.service.goods.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddGoodsMapper;
import cn.scdd.jxc.entity.ScddGoods;
import cn.scdd.jxc.entity.ScddGoodsExample;
import cn.scdd.jxc.service.goods.GoodsService;
import cn.scdd.jxc.util.Context.DeleteFlagEnum;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private ScddGoodsMapper scddGoodsMapper;
	
	@CacheEvict(value="goods",allEntries=true) 
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
			goods.setDeleteFlag(DeleteFlagEnum.NO.getCode());
			goods.setCreatorId(goods.getModifierId());
			goods.setCreateDate(goods.getModifyDate());
			this.scddGoodsMapper.insert(goods);
		}
	}

	public List<ScddGoods> searchByGoods(ScddGoods goods) {
		ScddGoodsExample example = new ScddGoodsExample("g");
		example.setOrderByClause("g.modify_date desc");
		ScddGoodsExample.Criteria criteria = example.createCriteria();
		if(goods != null) {
			if(StringUtils.isNotBlank(goods.getName())) {
				criteria.andNameLike("%" + goods.getName() + "%");
			}
			if(StringUtils.isNotBlank(goods.getShortName())) {
				criteria.andShortNameLike("%" + goods.getShortName() + "%");
			}
			criteria.andDeleteFlagEqualTo(DeleteFlagEnum.NO.getCode());
		}
		
		List<ScddGoods> list = this.scddGoodsMapper.selectByExample(example);
		return list;
	}

	public ScddGoods searchGoodsById(int id) {
		return this.scddGoodsMapper.selectByPrimaryKey(id);
	}

	public boolean checkGoodsExists(ScddGoods record) {
		int count =  this.scddGoodsMapper.checkGoodsExists(record);
		return count > 0?true:false;
	}
	
	/**
	 * 根据商品简称查找商品信息
	 * @param shortName
	 * @return
	 */
	public ScddGoods searchGoodsByShortName(String shortName) {
		ScddGoodsExample example = new ScddGoodsExample("g");
		ScddGoodsExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(shortName)) {
			criteria.andShortNameEqualTo(shortName);
		}
		List<ScddGoods> list = this.scddGoodsMapper.selectByExample(example);
		if(list != null && list.size() != 1) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Cacheable(value="goods")
	public List<ScddGoods> searchAll() {
		return this.searchByGoods(null);
	}
}
