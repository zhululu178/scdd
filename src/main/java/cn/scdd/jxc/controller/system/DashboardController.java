package cn.scdd.jxc.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.scdd.jxc.controller.BaseController;
import cn.scdd.jxc.entity.ScddOrderSearchPage;
import cn.scdd.jxc.util.Context.OrderDailyReportTypeEnum;

@Controller
@RequestMapping(value="/sys/dashboard")
public class DashboardController extends BaseController {
	/** 订单报表类型 */
	private static OrderDailyReportTypeEnum[] orderReportTypeList;
	static {
		orderReportTypeList = OrderDailyReportTypeEnum.values();
	}
	/**
	 * 控制面板首页
	 * @return
	 */
	@RequestMapping(value="/index")
	public ModelAndView index(ScddOrderSearchPage order) {
		ModelAndView modelAndView = new ModelAndView("sys/dashboard/index");
		modelAndView.addObject("orderReportTypeList", orderReportTypeList);
		return modelAndView;
	}
}
