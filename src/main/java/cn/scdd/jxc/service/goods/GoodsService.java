package cn.scdd.jxc.service.goods;

import java.util.List;

import cn.scdd.jxc.entity.ScddGoods;

public interface GoodsService {
	public void saveGoods(ScddGoods goods);
	
	public List<ScddGoods> searchByGoods(ScddGoods goods);
	
	public ScddGoods searchGoodsById(int id);
	
	public boolean checkGoodsExists(ScddGoods record);
	
	/**
	 * 根据商品简称查找商品信息
	 * @param shortName
	 * @return
	 */
	public ScddGoods searchGoodsByShortName(String shortName);
}
