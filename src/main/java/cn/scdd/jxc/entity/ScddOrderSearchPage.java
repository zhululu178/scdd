package cn.scdd.jxc.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ScddOrderSearchPage {
    private Integer id;

    private Integer memberId;

    private Integer userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transDateStart;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transDateEnd;

    private String manualFlag;

    private BigDecimal amount;

    private BigDecimal actualAmount;
    
    private BigDecimal purchaseAmount;

    private String deliveryAddr;

    private String deleteFlag;

    private Date createDate;

    private Integer creatorId;

    private Date modifyDate;

    private Integer modifierId;
    
    private String memberName;
    
    private String memberPhone;
    
    private String userName;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getManualFlag() {
		return manualFlag;
	}

	public void setManualFlag(String manualFlag) {
		this.manualFlag = manualFlag;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getDeliveryAddr() {
		return deliveryAddr;
	}

	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifierId() {
		return modifierId;
	}

	public void setModifierId(Integer modifierId) {
		this.modifierId = modifierId;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Date getTransDateStart() {
		return transDateStart;
	}

	public void setTransDateStart(Date transDateStart) {
		this.transDateStart = transDateStart;
	}

	public Date getTransDateEnd() {
		return transDateEnd;
	}

	public void setTransDateEnd(Date transDateEnd) {
		this.transDateEnd = transDateEnd;
	}

	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
}
