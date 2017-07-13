package cn.scdd.jxc.service.payment.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddPaymentTypeMapper;
import cn.scdd.jxc.entity.ScddPaymentType;
import cn.scdd.jxc.entity.ScddPaymentTypeExample;
import cn.scdd.jxc.service.payment.PaymentTypeService;

@Service("paymentTypeService")
public class PaymentTypeServiceImpl implements PaymentTypeService {
	@Autowired
	private ScddPaymentTypeMapper scddPaymentTypeMapper;
	
	public List<ScddPaymentType> searchAll() {
		ScddPaymentTypeExample example = new ScddPaymentTypeExample();
		example.setOrderByClause("PARENT_ID, id");
		List<ScddPaymentType> list = this.scddPaymentTypeMapper.selectByExample(example);
		
		List<ScddPaymentType> reList = new ArrayList<ScddPaymentType>();
		if(list != null && list.size() >0) {
			for(ScddPaymentType goodsClass : list) {
				if(goodsClass.getParentId() == null) {//跟节点
					reList.add(goodsClass);
				} else {
					this.addNode(reList, goodsClass);
				}
			}
		}
		return reList;
	}

	/**
	 * 将子节点放入列表中
	 * @param list
	 * @param goodsClass
	 */
	private void addNode(List<ScddPaymentType> list, ScddPaymentType goodsClass) {
		for(ScddPaymentType parent : list) {
			if(parent.getId() == goodsClass.getParentId()) {//当前节点的父节点
				if(parent.getChildren() == null) {//新增节点信息
					parent.setChildren(new ArrayList<ScddPaymentType>());
				}
				parent.getChildren().add(goodsClass);
			} else {
				if(parent.getChildren() != null && parent.getChildren().size() >0) {//从子节点中查找
					this.addNode(parent.getChildren(), goodsClass);
				}
			}
		}
	}
}
