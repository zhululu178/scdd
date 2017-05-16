package cn.scdd.jxc.service.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.scdd.jxc.dao.ScddOrderDetailMapper;
import cn.scdd.jxc.dao.ScddOrderMapper;
import cn.scdd.jxc.entity.ScddGoods;
import cn.scdd.jxc.entity.ScddMember;
import cn.scdd.jxc.entity.ScddOrder;
import cn.scdd.jxc.entity.ScddOrderDetail;
import cn.scdd.jxc.entity.ScddUser;
import cn.scdd.jxc.service.goods.GoodsService;
import cn.scdd.jxc.service.member.MemberService;
import cn.scdd.jxc.service.order.OrderService;
import cn.scdd.jxc.service.user.UserService;
import cn.scdd.jxc.util.Context.DeleteFlagEnum;
import cn.scdd.jxc.util.StringTool;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private MemberService memberService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserService userService;
	@Autowired
	private ScddOrderMapper scddOrderMapper;
	@Autowired
	private ScddOrderDetailMapper scddOrderDetailMapper;

	public void saveOrders(List<ScddOrder> orderList) {
		if(orderList != null && orderList.size() >0) {
			for(ScddOrder order : orderList) {
				this.saveOrder(order);
			}
		}
	}

	public void saveOrder(ScddOrder order) {
		this.scddOrderMapper.insert(order);
	}
	
	/**
	 * 验证订单信息
	 */
	public List<String> validOrder(Date transDate, String[] orderArr) {
		List<String> errMsgList = new ArrayList<String>();
		Date createDate = new Date();
		List<ScddOrder> orderList = new ArrayList<ScddOrder>(orderArr.length);
		for(int i=0;i<orderArr.length;i++) {
			if(!StringUtils.isEmpty(orderArr[i])) {
				//遍历每一笔订单
				String[] orderInfoArr = orderArr[i].trim().split("/");
				if(orderInfoArr.length >= 5) {
					ScddOrder order = new ScddOrder();
					order.setDetails(new ArrayList<ScddOrderDetail>(orderInfoArr.length - 4));
					orderList.add(order);
					
					//1. 会员信息
					order.setDeliveryAddr(orderInfoArr[0]);
//					String memberName = orderInfoArr[1];
					ScddMember member = this.memberService.searchMemberByPhone(orderInfoArr[2]);
					if(member == null) {//会员不存在
						errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单会员的手机号码" + orderInfoArr[2] + "不存在.");
					} else {
						order.setMemberId(member.getId());
					}
					//2. 系统销售人员信息
					boolean isActivityOrder = false;
					String salesInfo = orderInfoArr[orderInfoArr.length - 1];
					String actualAmountStr = StringTool.subSetNum(salesInfo);
					if(StringUtils.isEmpty(actualAmountStr)) {
						errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单实际销售金额有误.");
					} else {
						order.setActualAmount(new BigDecimal(actualAmountStr));//实际销售金额
					}
					ScddUser user = null;
					if(salesInfo.indexOf("【") > 0 && salesInfo.indexOf("】") > 0) {
						String userCode = salesInfo.substring(salesInfo.indexOf("【") + 1, salesInfo.indexOf("】"));
						if(!StringUtils.isEmpty(userCode)) {
							user = this.userService.searchByCode(userCode);
						}
					}
					if(salesInfo.indexOf(")") > 0 || salesInfo.indexOf("）") > 0) {//是否包括(3点),当前订单是否为活动订单
						isActivityOrder = true;
					}
					if(user != null) {
						order.setUserId(user.getId());	
					} else {
						errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单销售人员编码不存在.");
						continue;
					}
					
					order.setTransDate(transDate);
					order.setDeleteFlag(DeleteFlagEnum.NO.getCode());
					order.setCreateDate(createDate);
					order.setModifyDate(createDate);
					//遍历订单中的商品信息
					for(int j=3;j<orderInfoArr.length - 2;j++) {
						String goodsInfo = orderInfoArr[j];
						String num = StringTool.subSetNum(goodsInfo);
						int startNum = goodsInfo.indexOf(num);//数字的起始下标
						if(StringUtils.isEmpty(num) || startNum == 0) {
							errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单商品格式不正确.");
						} else {
							String goodsShortName = goodsInfo.substring(0, startNum);
							ScddGoods goods = this.goodsService.searchGoodsByShortName(goodsShortName);
							if(goods != null) {
								ScddOrderDetail orderDetail = new ScddOrderDetail();
								orderDetail.setGoodsId(goods.getId());
								orderDetail.setQuantity(Integer.parseInt(num));
								BigDecimal unitPrice = null;
								if(isActivityOrder) {//活动价
									unitPrice = goods.getActivityPrice();
								} else {
									if("1".equals(user.getAgentFlag())) {//是否代理
										unitPrice = goods.getAgentPrice();//代理价
									} else {
										unitPrice = goods.getPrice();//正常价格
									}
								}
								orderDetail.setUnitPrice(unitPrice);
								order.getDetails().add(orderDetail);
							} else {
								errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单中商品名称[" + goodsShortName + "]不存在.");	
							}
						}
					}
				} else {
					errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单格式不正确不存在.");
				}
			}
		}
		if(errMsgList.size() == 0) {
			this.saveOrderList(orderList);
		}
		return errMsgList;
	}
	
	/**
	 * 保存订单列表
	 * @param orderList
	 */
	private void saveOrderList(List<ScddOrder> orderList) {
		if(orderList != null && orderList.size() > 0) {
			for(ScddOrder order : orderList) {
				this.scddOrderMapper.insert(order);
				List<ScddOrderDetail> details = order.getDetails();
				if(details != null && details.size() > 0) {
					for(ScddOrderDetail detail : details) {
						detail.setOrderId(order.getId());
						this.scddOrderDetailMapper.insert(detail);
					}
				}
			}
		}
	}
}
