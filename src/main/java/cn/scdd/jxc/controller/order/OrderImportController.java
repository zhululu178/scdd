package cn.scdd.jxc.controller.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.scdd.jxc.entity.ScddOrder;
import cn.scdd.jxc.service.order.OrderService;
import cn.scdd.jxc.util.Context;

@Controller
@RequestMapping(value="/order/import")
public class OrderImportController {
	private final SimpleDateFormat formatter = new SimpleDateFormat(Context.DATE_FORMAT);
	@Autowired
	private OrderService orderService;
	public OrderImportController() {
		System.out.println("Init OrderImportController");
	}
	
	/**
	 * ���������ʼ��
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView modelAndView = new ModelAndView("order/import/index");
		modelAndView.addObject("transDate", formatter.format(new Date()));
		return modelAndView;
	}
	
	/**
	 * ���涩������
	 * @param transDate ��������
	 * @param orders ������Ϣ
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(String transDate, String orderDetails) {
		ModelAndView modelAndView = null;
		if(!StringUtils.isEmpty(orderDetails)) {
			String[] orderArr = orderDetails.split("\r\n");
			for(int i=0;i<orderArr.length;i++) {
				if(!StringUtils.isEmpty(orderArr[i])) {
					//����������Ϣ��������Ʒ��ϸ�Ͷ���ʵ���տ���
					String[] orderInfoArr = orderArr[i].split("\\|");
					if(orderInfoArr.length == 3) {
						String orderInfo = orderInfoArr[0];
						String orderDetail = orderInfoArr[1];
					} else {
						modelAndView = new ModelAndView("order/import/index");
						return modelAndView;
					}
				}
			}
		}
		modelAndView = new ModelAndView("order/import/list");
		return modelAndView;
	}
	
	private ScddOrder geneOrder(String orderInfo, String orderDetails, String actualAmout) {
		ScddOrder scddOrder = new ScddOrder();
		scddOrder.setActualAmount(new BigDecimal(actualAmout));//ʵ���տ���
		
		return scddOrder;
	}
}
