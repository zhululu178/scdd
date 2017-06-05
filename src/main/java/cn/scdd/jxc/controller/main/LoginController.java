package cn.scdd.jxc.controller.main;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.scdd.jxc.controller.BaseController;
import cn.scdd.jxc.entity.ScddUser;
import cn.scdd.jxc.service.user.UserService;
import cn.scdd.jxc.util.Context;

/**
 * 登陆控制器
 */
@Controller
public class LoginController extends BaseController {
	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 * 
	 * @param session
	 *            HttpSession
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(String phone, String password) throws Exception {
		// 在Session里保存信息
		ScddUser user = this.userService.loginUser(phone, password);
		if(user != null) {
			session.setAttribute(Context.SESSION_USER_ID, user.getId());
			session.setAttribute(Context.SESSION_USER_NAME, user.getName());
		}
		
		// 重定向
		return "redirect:index";
	}

	/**
	 * 退出系统
	 * 
	 * @param session
	 *            Session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception {
		// 清除Session
		session.invalidate();
		return "redirect:hello.action";
	}
}
