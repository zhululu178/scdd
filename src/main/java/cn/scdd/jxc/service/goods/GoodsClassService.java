package cn.scdd.jxc.service.goods;

import java.util.List;

import cn.scdd.jxc.entity.ScddGoodsClass;

public interface GoodsClassService {
	public void saveGoodsClass(ScddGoodsClass goods);
	
	public List<ScddGoodsClass> searchAll();
	
	public ScddGoodsClass searchGoodsClassById(int id);
	
	public boolean checkGoodsClassExists(ScddGoodsClass record);
}
