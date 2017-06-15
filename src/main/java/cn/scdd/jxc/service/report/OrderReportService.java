package cn.scdd.jxc.service.report;

import java.util.List;

import cn.scdd.jxc.entity.report.OrderAmountDaily;

public interface OrderReportService {
	List<OrderAmountDaily> searchOrderAmountDailyByType(String type);
}
