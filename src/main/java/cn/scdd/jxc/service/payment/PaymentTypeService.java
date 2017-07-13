package cn.scdd.jxc.service.payment;

import java.util.List;

import cn.scdd.jxc.entity.ScddPaymentType;

public interface PaymentTypeService {
	/**
	 * 获得所有的支付类型
	 * @return
	 */
	public List<ScddPaymentType> searchAll();
}
