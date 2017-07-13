package cn.scdd.jxc.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ScddPaymentSearchPage {
	private Integer id;

    private Integer paymentTypeId;
    
    private String paymentType;

    private BigDecimal amount;

    private Integer userId;
    
    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDateStart;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDateEnd;
    
    private String remark;

    private String deleteFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getPayDateStart() {
		return payDateStart;
	}

	public void setPayDateStart(Date payDateStart) {
		this.payDateStart = payDateStart;
	}

	public Date getPayDateEnd() {
		return payDateEnd;
	}

	public void setPayDateEnd(Date payDateEnd) {
		this.payDateEnd = payDateEnd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
