package cn.scdd.jxc.service.payment.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddPaymentMapper;
import cn.scdd.jxc.entity.ScddPayment;
import cn.scdd.jxc.entity.ScddPaymentSearchPage;
import cn.scdd.jxc.service.payment.PaymentService;
import cn.scdd.jxc.util.Context.DeleteFlagEnum;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private ScddPaymentMapper scddPaymentMapper;
	
	public void savePayment(ScddPayment payment) {
		payment.setModifyDate(new Date());
		if(payment != null && payment.getId() != null) {
			ScddPayment paymentT = this.scddPaymentMapper.selectByPrimaryKey(payment.getId());
			paymentT.setAmount(payment.getAmount());
			paymentT.setUserId(payment.getUserId());
			paymentT.setPaymentTypeId(payment.getPaymentTypeId());
			paymentT.setPayDate(payment.getPayDate());
			paymentT.setRemark(payment.getRemark());
			this.scddPaymentMapper.updateByPrimaryKey(paymentT);
		} else {
			payment.setDeleteFlag(DeleteFlagEnum.NO.getCode());
			payment.setCreatorId(payment.getModifierId());
			payment.setCreateDate(payment.getModifyDate());
			this.scddPaymentMapper.insert(payment);
		}
	}
	
	public List<ScddPaymentSearchPage> searchByPayment(ScddPaymentSearchPage payment) {
		return this.scddPaymentMapper.selectByPayment(payment);
	}

	public ScddPayment searchPaymentById(int id) {
		return this.scddPaymentMapper.selectByPrimaryKey(id);
	}

	public boolean deletePaymentById(int id) {
		this.scddPaymentMapper.deleteByPrimaryKey(id);
		return true;
	}
}
