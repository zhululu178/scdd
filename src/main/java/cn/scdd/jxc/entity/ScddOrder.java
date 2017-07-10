package cn.scdd.jxc.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ScddOrder {
    private Integer id;

    private Integer memberId;

    private Integer userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transDate;

    private String manualFlag;

    private BigDecimal amount;
    
    private BigDecimal purchaseAmount;
    
    private BigDecimal actualAmount;

    private String deliveryAddr;

    private String expressCompany;

    private String expressNum;
    
    private String deleteFlag;

    private Date createDate;

    private Integer creatorId;

    private Date modifyDate;

    private Integer modifierId;
    
    private ScddUser user;
    
    private ScddMember member;
    
    private List<ScddOrderDetail> details;

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
        this.manualFlag = manualFlag == null ? null : manualFlag.trim();
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
        this.deliveryAddr = deliveryAddr == null ? null : deliveryAddr.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
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

	public List<ScddOrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ScddOrderDetail> details) {
		this.details = details;
	}

	public ScddUser getUser() {
		return user;
	}

	public void setUser(ScddUser user) {
		this.user = user;
	}

	public ScddMember getMember() {
		return member;
	}

	public void setMember(ScddMember member) {
		this.member = member;
	}

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getExpressNum() {
		return expressNum;
	}

	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}

	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
}