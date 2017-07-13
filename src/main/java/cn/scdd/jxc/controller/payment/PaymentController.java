package cn.scdd.jxc.controller.payment;

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
import cn.scdd.jxc.entity.ScddPayment;
import cn.scdd.jxc.entity.ScddPaymentSearchPage;
import cn.scdd.jxc.entity.ScddPaymentType;
import cn.scdd.jxc.entity.ScddUser;
import cn.scdd.jxc.service.payment.PaymentService;
import cn.scdd.jxc.service.payment.PaymentTypeService;
import cn.scdd.jxc.service.user.UserService;
import cn.scdd.jxc.util.JsonResult;
import cn.scdd.jxc.util.PageVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping(value="/payment")
public class PaymentController extends BaseController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private PaymentTypeService paymentTypeService;
	@Autowired
	private UserService userService;
	
	/**
	 * 新增支出
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("payment/add");
		List<ScddUser> userList = this.userService.searchAll();
		List<ScddPaymentType> paymentTypeList = this.paymentTypeService.searchAll();
		ScddPayment payment = new ScddPayment();
		payment.setUserId(this.getLoginUserId());
		payment.setPayDate(new Date());
		modelAndView.addObject("payment", payment);
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("paymentTypeList", paymentTypeList);
		return modelAndView;
	}
	
	/**
	 * 修改支出
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("payment/add");
		ScddPayment payment = this.paymentService.searchPaymentById(id);
		List<ScddUser> userList = this.userService.searchAll();
		List<ScddPaymentType> paymentTypeList = this.paymentTypeService.searchAll();
		modelAndView.addObject("payment", payment);
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("paymentTypeList", paymentTypeList);
		return modelAndView;
	}
	
	/**
	 * 保存支出
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(ScddPayment payment) {
		ModelAndView modelAndView = new ModelAndView("payment/list");
		payment.setModifierId(this.getLoginUserId());//操作人
		this.paymentService.savePayment(payment);
		return modelAndView;
	}
	
	/**
	 * 删除支出
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public JsonResult delete(Integer paymentId) {
		JsonResult result = new JsonResult();
		boolean success = this.paymentService.deletePaymentById(paymentId);
		if(success) {
			result.setCode(1);
			result.setMsg("删除支出成功.");
		} else {
			result.setCode(2);
			result.setMsg("删除支出失败.");
		}
		return result;
	}
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(ScddPaymentSearchPage payment) {
		ModelAndView modelAndView = new ModelAndView("payment/list");
		List<ScddUser> userList = this.userService.searchAll();
		modelAndView.addObject("payment", payment);
		modelAndView.addObject("userList", userList);
		return modelAndView;
	}
	
	/**
	 * 查询支出
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public PageVo<ScddPaymentSearchPage> search(ScddPaymentSearchPage payment) {
		PageVo<ScddPaymentSearchPage> pageVo = new PageVo<ScddPaymentSearchPage>();
		this.initPage();
		List<ScddPaymentSearchPage> list = this.paymentService.searchByPayment(payment);
		if(list != null) {
			Page<ScddPaymentSearchPage> page = (Page<ScddPaymentSearchPage>) list;
			pageVo.setList(list);
			pageVo.setPageCur(page.getPageNum());
			pageVo.setPageNum(page.getPages());
			Map<String, Object> contents = new HashMap<String, Object>(1);
			contents.put("list", list);
			pageVo.setContents(this.getContentFromTemplate("payment/records.ftl", contents));
		}
		return pageVo;
	}
}
