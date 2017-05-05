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
import cn.scdd.jxc.entity.ScddSupplier;
import cn.scdd.jxc.service.supplier.SupplierService;
import cn.scdd.jxc.util.MessageContext;
import cn.scdd.jxc.util.PageVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping(value="/sys/supplier")
public class SupplierController extends BaseController {
	@Autowired
	private SupplierService supplierService;
	/**
	 * ��Ӧ������ҳ��
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/supplier/add");
		return modelAndView;
	}
	
	/**
	 * ��Ӧ���޸�ҳ��
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("sys/supplier/add");
		modelAndView.addObject("supplier", this.supplierService.searchSupplierById(id));
		return modelAndView;
	}
	
	/**
	 * ��Ӧ�̱���
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(ScddSupplier supplier) {
		ModelAndView modelAndView = null;
		if(supplierService.checkSupplierExists(supplier)) {//��Ӧ���Ѿ�����
			modelAndView = new ModelAndView("sys/supplier/add");
			modelAndView.addObject("supplier", supplier);
			modelAndView.addObject(ERR_MSG, MessageContext.SUPPLIER_ERR_MSG_EXIST);
		} else {
			supplierService.saveSupplier(supplier);
			modelAndView = new ModelAndView("sys/supplier/list");
		}
		
		return modelAndView;
	}
	
	/**
	 * ��Ӧ���б�
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(ScddSupplier supplier) {
		ModelAndView modelAndView = new ModelAndView("sys/supplier/list");
		modelAndView.addObject("supplier", supplier);
		return modelAndView;
	}
	
	/**
	 * ��Ӧ�̲�ѯ
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public PageVo<ScddSupplier> search(ScddSupplier supplier) {
		PageVo<ScddSupplier> pageVo = new PageVo<ScddSupplier>();
		this.initPage();
		List<ScddSupplier> list = this.supplierService.searchBySupplier(supplier);
		if(list != null) {
			Page<ScddSupplier> page = (Page<ScddSupplier>) list;
			pageVo.setList(list);
			pageVo.setPageCur(page.getPageNum());
			pageVo.setPageNum(page.getPages());
			Map<String, Object> contents = new HashMap<String, Object>(1);
			contents.put("list", list);
			pageVo.setContents(this.getContentFromTemplate("sys/supplier/records.ftl", contents));
		}
		return pageVo;
	}
}
