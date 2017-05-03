package cn.scdd.jxc.controller.system;

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
import cn.scdd.jxc.entity.ScddGoods;
import cn.scdd.jxc.entity.ScddSupplier;
import cn.scdd.jxc.service.goods.GoodsService;
import cn.scdd.jxc.service.supplier.SupplierService;
import cn.scdd.jxc.util.PageVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping(value="/sys/goods")
public class GoodsController extends BaseController {
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 会员新增页面
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/goods/add");
		List<ScddSupplier> supplierList = this.supplierService.searchBySupplier(null);
		modelAndView.addObject("supplierList", supplierList);
		return modelAndView;
	}
	
	/**
	 * 会员修改页面
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("sys/goods/add");
		List<ScddSupplier> supplierList = this.supplierService.searchBySupplier(null);
		modelAndView.addObject("goods", this.goodsService.searchGoodsById(id));
		modelAndView.addObject("supplierList", supplierList);
		return modelAndView;
	}
	
	/**
	 * 会员保存
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(ScddGoods goods) {
		ModelAndView modelAndView = null;
		if(this.goodsService.checkGoodsExists(goods)) {//会员已经存在
			modelAndView = new ModelAndView("sys/goods/add");
			modelAndView.addObject("goods", goods);
		} else {
			goodsService.saveGoods(goods);
			modelAndView = new ModelAndView("sys/goods/list");
		}
		
		return modelAndView;
	}
	
	/**
	 * 会员列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(ScddGoods goods) {
		ModelAndView modelAndView = new ModelAndView("sys/goods/list");
		modelAndView.addObject("goods", goods);
		return modelAndView;
	}
	
	/**
	 * 会员查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public PageVo<ScddGoods> search(ScddGoods goods) {
		PageVo<ScddGoods> pageVo = new PageVo<ScddGoods>();
		this.initPage();
		List<ScddGoods> list = this.goodsService.searchByGoods(goods);
		if(list != null) {
			Page<ScddGoods> page = (Page<ScddGoods>) list;
			pageVo.setList(list);
			pageVo.setPageCur(page.getPageNum());
			pageVo.setPageNum(page.getPages());
			Map<String, Object> contents = new HashMap<String, Object>(1);
			contents.put("list", list);
			pageVo.setContents(this.getContentFromTemplate("sys/goods/records.ftl", contents));
		}
		return pageVo;
	}
}
