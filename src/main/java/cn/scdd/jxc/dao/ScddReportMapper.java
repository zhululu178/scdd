package cn.scdd.jxc.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.scdd.jxc.entity.report.OrderAmountDaily;

@Repository("scddReportMapper")
public interface ScddReportMapper {
	List<OrderAmountDaily> selectOrderAmountDaily(@Param(value = "transStartDate") Date transStartDate,   
            @Param(value = "transEndDate") Date transEndDate);
}