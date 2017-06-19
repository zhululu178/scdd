package cn.scdd.jxc.service.order;

import java.util.Date;
import java.util.List;

import cn.scdd.jxc.entity.ScddOrder;
import cn.scdd.jxc.entity.ScddOrderSearchPage;

public interface OrderService {
	public void saveOrders(List<ScddOrder> orderList);
	
	public void saveOrder(ScddOrder order);
	
	public List<ScddOrderSearchPage> searchByOrder(ScddOrderSearchPage order);
	
	public ScddOrder searchOrderById(int id);
	
	/**
	 * 批量保存
	 * @param orderArr
	 * @return
	 */
	public List<String> saveOrders(Date transDate, String[] orderArr);
	
	/**
	 * 删除订单
	 * @param orderId
	 */
	public boolean deleteOrder(Integer orderId);
}
