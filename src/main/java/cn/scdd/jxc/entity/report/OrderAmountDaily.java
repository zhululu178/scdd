package cn.scdd.jxc.entity.report;

import java.math.BigDecimal;

/**
 * 每日金额统计 
 * @author Administrator
 *
 */
public class OrderAmountDaily {
	/** 交易日期 */
	private String transDate;
	/** 实际交易金额 */
	private BigDecimal actualAmount;
	/** 交易金额 */
	private BigDecimal amount;
	
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public BigDecimal getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
