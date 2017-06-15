package cn.scdd.jxc.controller.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.scdd.jxc.controller.BaseController;
import cn.scdd.jxc.entity.report.OrderAmountDaily;
import cn.scdd.jxc.service.report.OrderReportService;

@Controller
@RequestMapping(value="/report/order")
public class OrderStaticsController extends BaseController {
	@Autowired
	private OrderReportService orderReportService;
	/**
	 * 订单按天统计
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/dailyamount")
	public List<OrderAmountDaily> orderAmountDaily(String type) {
		return orderReportService.searchOrderAmountDailyByType(type);
	}
}
