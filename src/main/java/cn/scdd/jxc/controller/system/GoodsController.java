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
import cn.scdd.jxc.util.MessageContext;
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
	 * ��Ա����ҳ��
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
	 * ��Ա�޸�ҳ��
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id) {
		ScddGoods goods = this.goodsService.searchGoodsById(id);
		return this.initEditPage(goods, false);
	}
	
	/**
	 * 初始化新增/编辑页面
	 * @param modelAndView
	 * @param hasErr
	 */
	private ModelAndView initEditPage(ScddGoods goods, boolean hasErr) {
		ModelAndView modelAndView = new ModelAndView("sys/goods/add");
		List<ScddSupplier> supplierList = this.supplierService.searchBySupplier(null);
		modelAndView.addObject("goods", goods);
		modelAndView.addObject("supplierList", supplierList);
		if(hasErr) {
			modelAndView.addObject(ERR_MSG, MessageContext.GOODS_ERR_MSG_EXIST);
		}
		return modelAndView;
	}
	
	/**
	 * ��Ա����
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(ScddGoods goods) {
		ModelAndView modelAndView = null;
		if(this.goodsService.checkGoodsExists(goods)) {//��Ա�Ѿ�����
			modelAndView = this.initEditPage(goods, true);
		} else {
			goodsService.saveGoods(goods);
			modelAndView = new ModelAndView("sys/goods/list");
		}
		
		return modelAndView;
	}
	
	/**
	 * ��Ա�б�
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(ScddGoods goods) {
		ModelAndView modelAndView = new ModelAndView("sys/goods/list");
		modelAndView.addObject("goods", goods);
		return modelAndView;
	}
	
	/**
	 * ��Ա��ѯ
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
