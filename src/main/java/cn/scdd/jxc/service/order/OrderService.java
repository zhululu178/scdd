package cn.scdd.jxc.service.order;

import java.util.List;

import cn.scdd.jxc.entity.ScddOrder;

public interface OrderService {
	public void saveOrders(List<ScddOrder> orderList);
	
	public void saveOrder(ScddOrder order);
}
