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
import cn.scdd.jxc.util.MessageContext;
import cn.scdd.jxc.util.PageVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping(value="/sys/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	/**
	 * �û�����ҳ��
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/user/add");
		return modelAndView;
	}
	
	/**
	 * �û��޸�ҳ��
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("sys/user/add");
		modelAndView.addObject("user", this.userService.searchUserById(id));
		return modelAndView;
	}
	
	/**
	 * �û�����
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView save(ScddUser user) {
		ModelAndView modelAndView = null;
		if("on".equals(user.getAgentFlag())) {//����
			user.setAgentFlag("1");
		} else {
			user.setAgentFlag("0");
		}
		if(userService.checkUserExists(user)) {//�û��Ѿ�����
			modelAndView = new ModelAndView("sys/user/add");
			modelAndView.addObject("user", user);
			modelAndView.addObject(ERR_MSG, MessageContext.USER_ERR_MSG_EXIST);
		} else {
			userService.saveUser(user);
			modelAndView = new ModelAndView("sys/user/list");	
		}
		
		return modelAndView;
	}
	
	/**
	 * �û��б�
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(ScddUser user) {
		ModelAndView modelAndView = new ModelAndView("sys/user/list");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	/**
	 * �û���ѯ
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
