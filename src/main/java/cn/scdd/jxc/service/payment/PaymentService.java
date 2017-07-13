package cn.scdd.jxc.service.payment;

import java.util.List;

import cn.scdd.jxc.entity.ScddPayment;
import cn.scdd.jxc.entity.ScddPaymentSearchPage;

public interface PaymentService {
	public void savePayment(ScddPayment payment);
	
	public List<ScddPaymentSearchPage> searchByPayment(ScddPaymentSearchPage payment);
	
	public ScddPayment searchPaymentById(int id);
	
	public boolean deletePaymentById(int id);
}
