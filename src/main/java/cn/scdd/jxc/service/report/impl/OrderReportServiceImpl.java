package cn.scdd.jxc.service.report.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddReportMapper;
import cn.scdd.jxc.entity.report.OrderAmountDaily;
import cn.scdd.jxc.service.report.OrderReportService;
import cn.scdd.jxc.util.Context.OrderDailyReportTypeEnum;
import cn.scdd.jxc.util.DateUtil;

@Service("orderReportService")
public class OrderReportServiceImpl implements OrderReportService {
	@Autowired
	private ScddReportMapper scddReportMapper;
	
	public List<OrderAmountDaily> searchOrderAmountDailyByType(String type) {
		Date transEndDate = DateUtil.addDays(new Date(), 1);
		Date transStartDate = DateUtil.addDays(transEndDate, -7);
		if(OrderDailyReportTypeEnum.MONTH_DAY.getCode().equals(type)) {//7日
			transStartDate = DateUtil.addMonths(transEndDate, -1);
		} else if(OrderDailyReportTypeEnum.CUR_WEEK.getCode().equals(type)) {
			transStartDate = DateUtil.getCurrentWeekBegin();
		} else if(OrderDailyReportTypeEnum.CUR_MONTH.getCode().equals(type)) {
			transStartDate = DateUtil.getCurrentMonthBegin();
		}
		List<OrderAmountDaily> list = this.scddReportMapper.selectOrderAmountDaily(transStartDate, transEndDate);
		List<OrderAmountDaily> reList = new ArrayList<OrderAmountDaily>();
		while(transEndDate.after(transStartDate)) {
			String dateStr = DateUtil.date2Str(transStartDate, "yyyyMMdd");
			OrderAmountDaily omd = this.getOrderAmountByDate(list, dateStr);
			if(omd == null) {
				omd = new OrderAmountDaily();
				omd.setActualAmount(new BigDecimal("0"));
				omd.setAmount(omd.getActualAmount());
				omd.setTransDate(dateStr);
			}
			//月份，日期只显示到日
			if(OrderDailyReportTypeEnum.MONTH_DAY.getCode().equals(type) || 
					OrderDailyReportTypeEnum.CUR_MONTH.getCode().equals(type)) {
				omd.setTransDate(omd.getTransDate().substring(6));
			}
			reList.add(omd);
			transStartDate = DateUtil.addDays(transStartDate, 1);
		}
		return reList;
	}
	
	/**
	 * 根据日期字符，获得指定日期的金额信息
	 * @param list
	 * @param curDateStr
	 * @return
	 */
	private OrderAmountDaily getOrderAmountByDate(List<OrderAmountDaily> list, String curDateStr) {
		if(list != null && list.size() > 0) {
			for(OrderAmountDaily o : list) {
				if(o != null && o.getTransDate().equals(curDateStr)) {
					return o;
				}
			}
		}
		return null;
	}
}
