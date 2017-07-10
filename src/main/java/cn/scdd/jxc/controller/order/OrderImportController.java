package cn.scdd.jxc.controller.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.scdd.jxc.controller.BaseController;
import cn.scdd.jxc.service.order.OrderService;
import cn.scdd.jxc.util.Context;

@Controller
@RequestMapping(value="/order/import")
public class OrderImportController extends BaseController {
	private final SimpleDateFormat formatter = new SimpleDateFormat(Context.DATE_FORMAT);
	@Autowired
	private OrderService orderService;
	
	/**
	 * ���������ʼ��
	 * @return
	 */
	@RequestMapping(value="/index")
	public ModelAndView init(String transDate, String orderDetails) {
		ModelAndView modelAndView = new ModelAndView("order/import/index");
		if(StringUtils.isEmpty(transDate)) {
			transDate = formatter.format(new Date());
		}
		modelAndView.addObject("transDate", transDate);
		modelAndView.addObject("orderDetails", orderDetails);
		return modelAndView;
	}
	public static void main(String[] args) {
		//System.out.println("杭州市桐庐县迎春南路建业大厦1005/郑苏/13082857911/玫瑰花饼12个/云a片糕12个/154（3点）【FIFI】".split("/").length);
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
	 * 导入保存的商品
	 * @param transDate ��������
	 * @param orders ������Ϣ
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(String transDate, String orderDetails) {
		ModelAndView modelAndView = new ModelAndView("order/import/confirm");
		Date transDatet = null;
		try {
			transDatet = formatter.parse(transDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(!StringUtils.isEmpty(orderDetails)) {
			String[] orderArr = orderDetails.split("\r\n");
			Integer operatorId = (Integer)(this.session.getAttribute(Context.SESSION_USER_ID));
			List<String> msg = this.orderService.saveOrders(operatorId, transDatet, orderArr);
			modelAndView.addObject("transDate", transDate);
			modelAndView.addObject("orderDetails", orderDetails);
			modelAndView.addObject("import_error_list", msg);
		}
		return modelAndView;
	}
}
