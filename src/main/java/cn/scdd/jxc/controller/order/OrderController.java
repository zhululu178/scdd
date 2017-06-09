package cn.scdd.jxc.controller.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.scdd.jxc.controller.BaseController;
import cn.scdd.jxc.entity.ScddOrder;
import cn.scdd.jxc.entity.ScddOrderSearchPage;
import cn.scdd.jxc.entity.ScddUser;
import cn.scdd.jxc.service.order.OrderService;
import cn.scdd.jxc.service.user.UserService;
import cn.scdd.jxc.util.PageVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping(value="/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	/**
	 * 新增订单
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("order/add");
		List<ScddUser> userList = this.userService.searchAll();
		ScddOrder order = new ScddOrder();
		order.setUserId(this.getLoginUserId());
		order.setTransDate(new Date());
		modelAndView.addObject("order", order);
		modelAndView.addObject("userList", userList);
		return modelAndView;
	}
	
	/**
	 * 修改订单
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("order/add");
		ScddOrder order = this.orderService.searchOrderById(id);
		List<ScddUser> userList = this.userService.searchAll();
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("order", order);
		return modelAndView;
	}
	
	/**
	 * 保存订单
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(ScddOrder order) {
		ModelAndView modelAndView = new ModelAndView("order/list");
		order.setModifierId(this.getLoginUserId());//操作人
		this.orderService.saveOrder(order);
		return modelAndView;
	}
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(ScddOrderSearchPage order) {
		ModelAndView modelAndView = new ModelAndView("order/list");
		modelAndView.addObject("order", order);
		return modelAndView;
	}
	
	/**
	 * 查询订单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public PageVo<ScddOrderSearchPage> search(ScddOrderSearchPage order) {
		PageVo<ScddOrderSearchPage> pageVo = new PageVo<ScddOrderSearchPage>();
		this.initPage();
		List<ScddOrderSearchPage> list = this.orderService.searchByOrder(order);
		if(list != null) {
			Page<ScddOrderSearchPage> page = (Page<ScddOrderSearchPage>) list;
			pageVo.setList(list);
			pageVo.setPageCur(page.getPageNum());
			pageVo.setPageNum(page.getPages());
			Map<String, Object> contents = new HashMap<String, Object>(1);
			contents.put("list", list);
			pageVo.setContents(this.getContentFromTemplate("order/records.ftl", contents));
		}
		return pageVo;
	}
}
