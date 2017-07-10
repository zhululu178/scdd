package cn.scdd.jxc.entity;

import java.math.BigDecimal;

public class ScddOrderDetail {
    private Integer id;

    private Integer orderId;

    private Integer goodsId;

    private BigDecimal discount;

    private BigDecimal unitPrice;

    private BigDecimal purchasePrice;

    private Integer quantity;

    private ScddGoods goods;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public ScddGoods getGoods() {
		return goods;
	}

	public void setGoods(ScddGoods goods) {
		this.goods = goods;
	}
}