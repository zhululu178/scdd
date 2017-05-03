package cn.scdd.jxc.service.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddOrderMapper;
import cn.scdd.jxc.entity.ScddOrder;
import cn.scdd.jxc.service.order.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ScddOrderMapper scddOrderMapper;

	public void saveOrders(List<ScddOrder> orderList) {
		if(orderList != null && orderList.size() >0) {
			for(ScddOrder order : orderList) {
				this.saveOrder(order);
			}
		}
	}

	public void saveOrder(ScddOrder order) {
		this.scddOrderMapper.insert(order);
	}
}
