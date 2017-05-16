package cn.scdd.jxc.service.order;

import java.util.Date;
import java.util.List;

import cn.scdd.jxc.entity.ScddOrder;

public interface OrderService {
	public void saveOrders(List<ScddOrder> orderList);
	
	public void saveOrder(ScddOrder order);
	
	/**
	 * 验证订单的有效性，比如商品名称是否合法等
	 * @param orderArr
	 * @return
	 */
	public List<String> saveOrders(Date transDate, String[] orderArr);
}
