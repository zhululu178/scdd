package cn.scdd.jxc.service.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.scdd.jxc.dao.ScddMemberMapper;
import cn.scdd.jxc.dao.ScddOrderDetailMapper;
import cn.scdd.jxc.dao.ScddOrderMapper;
import cn.scdd.jxc.entity.ScddGoods;
import cn.scdd.jxc.entity.ScddMember;
import cn.scdd.jxc.entity.ScddOrder;
import cn.scdd.jxc.entity.ScddOrderDetail;
import cn.scdd.jxc.entity.ScddOrderDetailExample;
import cn.scdd.jxc.entity.ScddOrderDetailExample.Criteria;
import cn.scdd.jxc.entity.ScddOrderSearchPage;
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
	private ScddMemberMapper scddMemberMapper;
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
		Date today = new Date();
		List<ScddOrderDetail> details = order.getDetails();
		BigDecimal allAmount = new BigDecimal(0);//订单的总金额
		if(details != null && details.size() > 0) {
			for(ScddOrderDetail detail : details) {
				if(detail != null && detail.getGoodsId() != null) {
					BigDecimal amout = detail.getUnitPrice().multiply(new BigDecimal(detail.getQuantity()));
					if(detail.getDiscount() != null && detail.getDiscount().doubleValue() != 0) {//有折扣
						amout = amout.multiply(detail.getDiscount());
					}
					allAmount = allAmount.add(amout);
				}
			}
		}
		//积分
		Integer points = allAmount.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		if(order.getId() != null) {
			ScddOrder orderT = this.scddOrderMapper.selectByPrimaryKey(order.getId());
			orderT.setMemberId(order.getMemberId());
			orderT.setUserId(order.getUserId());
			BigDecimal oldActualAmount = orderT.getActualAmount();
			orderT.setActualAmount(order.getActualAmount());
			orderT.setDeliveryAddr(order.getDeliveryAddr());
			orderT.setTransDate(order.getTransDate());
			orderT.setExpressCompany(order.getExpressCompany());
			orderT.setExpressNum(order.getExpressNum());
			orderT.setModifyDate(today);
			orderT.setModifierId(order.getModifierId());
			orderT.setAmount(allAmount);
			//新的金额-旧金额，获得差金额，形成新积分
			points = orderT.getActualAmount().subtract(oldActualAmount).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			this.scddOrderMapper.updateByPrimaryKey(orderT);
			//删除已存在的订单明细
			ScddOrderDetailExample example = new ScddOrderDetailExample();
			Criteria criteria = example.createCriteria();
			criteria.andOrderIdEqualTo(order.getId());
			this.scddOrderDetailMapper.deleteByExample(example);
		} else {
			order.setAmount(allAmount);
			order.setDeleteFlag(DeleteFlagEnum.NO.getCode());
			order.setCreatorId(order.getModifierId());
			order.setCreateDate(today);
			order.setModifyDate(today);
			this.scddOrderMapper.insert(order);
		}
		if(details != null && details.size() > 0) {
			for(ScddOrderDetail detail : details) {
				if(detail != null && detail.getGoodsId() != null) {
					detail.setOrderId(order.getId());
					this.scddOrderDetailMapper.insert(detail);
				}
			}
		}
		//更新会员积分
		ScddMember member = this.memberService.searchMemberById(order.getMemberId());
		if(member != null) {
			//金额四舍五入
			member.setPoints(member.getPoints() + points);
			this.scddMemberMapper.updateByPrimaryKey(member);
		}
	}

	/**
	 * 验证订单信息
	 */
	public List<String> saveOrders(Date transDate, String[] orderArr) {
		List<String> errMsgList = new ArrayList<String>();
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
//						errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单会员的手机号码" + orderInfoArr[2] + "不存在.");
						member = new ScddMember();
						member.setPhone(orderInfoArr[2]);
						member.setName(orderInfoArr[1]);
						member.setAddress(orderInfoArr[0]);
						member.setModifyDate(new Date());
						memberService.saveMember(member);
					}
					order.setMemberId(member.getId());
					//2. 系统销售人员信息
					boolean isActivityOrder = false;
					String salesInfo = orderInfoArr[orderInfoArr.length - 1];
					String actualAmountStr = StringTool.subSetFirstNum(salesInfo);
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
					//遍历订单中的商品信息
					BigDecimal allGoodsAmount = new BigDecimal(0);//所有商品总价值
					for(int j=3;j<orderInfoArr.length - 1;j++) {
						String goodsInfo = orderInfoArr[j];
						String num = StringTool.subSetLastNum(goodsInfo);
						if(!StringUtils.isEmpty(num)) {
							int startNum = goodsInfo.indexOf(num);//数字的起始下标
							if(startNum == 0) {
								errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单商品信息[" + goodsInfo + "]格式不正确.");
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
									allGoodsAmount = allGoodsAmount.add(unitPrice.multiply(new BigDecimal(orderDetail.getQuantity())));//计算总金额
									order.getDetails().add(orderDetail);
								} else {
									errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单中商品名称[" + goodsShortName + "]不存在.");	
								}
							}
						} else {
							errMsgList.add("订单[" + (i+1) +"]-" + orderArr[i] + "  此订单商品信息[" + goodsInfo + "]格式不正确.");
						}
					}
					order.setAmount(allGoodsAmount);
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
				this.saveOrder(order);
			}
		}
	}

	public List<ScddOrderSearchPage> searchByOrder(ScddOrderSearchPage order) {
		return this.scddOrderMapper.selectByOrder(order);
	}

	public ScddOrder searchOrderById(int id) {
		return this.scddOrderMapper.selectHeavyOrderById(id);
	}

	public boolean deleteOrder(Integer orderId) {
		if(orderId != null) {
			ScddOrder order = this.scddOrderMapper.selectByPrimaryKey(orderId);
			if(order != null) {
				//1. 删除会员积分
				ScddMember member = this.memberService.searchMemberById(order.getMemberId());
				if(member != null) {
					//金额四舍五入
					Integer points = order.getActualAmount().setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
					//删除订单的积分
					member.setPoints(member.getPoints() - points);
					this.scddMemberMapper.updateByPrimaryKey(member);
				}
				//2. 删除订单明细
				ScddOrderDetailExample example = new ScddOrderDetailExample();
				ScddOrderDetailExample.Criteria criteria = example.createCriteria();
				criteria.andOrderIdEqualTo(orderId);
				this.scddOrderDetailMapper.deleteByExample(example);
				//3. 删除订单
				this.scddOrderMapper.deleteByPrimaryKey(orderId);
				return true;
			}
		}
		return false;
	}
}
