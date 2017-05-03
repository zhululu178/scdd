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
import cn.scdd.jxc.entity.ScddUser;
import cn.scdd.jxc.service.user.UserService;
import cn.scdd.jxc.util.PageVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping(value="/sys/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	/**
	 * 用户新增页面
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/user/add");
		return modelAndView;
	}
	
	/**
	 * 用户修改页面
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("sys/user/add");
		modelAndView.addObject("user", this.userService.searchUserById(id));
		return modelAndView;
	}
	
	/**
	 * 用户保存
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(ScddUser user) {
		ModelAndView modelAndView = null;
		if("on".equals(user.getAgentFlag())) {//代理
			user.setAgentFlag("1");
		} else {
			user.setAgentFlag("0");
		}
		if(userService.checkUserExists(user)) {//用户已经存在
			modelAndView = new ModelAndView("sys/user/add");
			modelAndView.addObject("user", user);
		} else {
			userService.saveUser(user);
			modelAndView = new ModelAndView("sys/user/list");	
		}
		
		return modelAndView;
	}
	
	/**
	 * 用户列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(ScddUser user) {
		ModelAndView modelAndView = new ModelAndView("sys/user/list");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	/**
	 * 用户查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public PageVo<ScddUser> search(ScddUser user) {
		PageVo<ScddUser> pageVo = new PageVo<ScddUser>();
		this.initPage();
		List<ScddUser> list = userService.searchByUser(user);
		if(list != null) {
			Page<ScddUser> page = (Page<ScddUser>) list;
			pageVo.setList(list);
			pageVo.setPageCur(page.getPageNum());
			pageVo.setPageNum(page.getPages());
			Map<String, Object> contents = new HashMap<String, Object>(1);
			contents.put("list", list);
			pageVo.setContents(this.getContentFromTemplate("sys/user/records.ftl", contents));
		}
		return pageVo;
	}
}
