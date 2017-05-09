package cn.scdd.jxc.controller.order;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public static void main(String[] args) {
		//System.out.println("杭州市桐庐县迎春南路建业大厦1005/郑苏建/13082857999/芝麻12个/豆沙12个/冬笋6个/荠菜6个/154（3点）【fi】".split("/").length);
        String input = "jdiwo3495jis90.5jsie4dss56djiw9";
        String regex = "\\d+(\\.\\d+)?";
        Pattern pattern =  Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find())
        {
            System.out.println(matcher.group());
        }
	}
	/**
	 * ���涩�����
	 * @param transDate ��������
	 * @param orders ������Ϣ
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(String transDate, String orderDetails) {
		ModelAndView modelAndView = null;
		Date transDatet = null;
		try {
			transDatet = formatter.parse(transDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(!StringUtils.isEmpty(orderDetails)) {
			String[] orderArr = orderDetails.split("\r\n");
			
		}
		modelAndView = new ModelAndView("order/import/list");
		return modelAndView;
	}
	
	/**
	 * 根据输入的信息产生订单
	 * @param orderInfoArr
	 * @return
	 */
	private ScddOrder geneOrder(String[] orderInfoArr) {
		ScddOrder scddOrder = new ScddOrder();
		scddOrder.setActualAmount(new BigDecimal(11));//ʵ���տ���
		
		return scddOrder;
	}
}
