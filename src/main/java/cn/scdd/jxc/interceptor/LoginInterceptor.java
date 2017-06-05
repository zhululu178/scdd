package cn.scdd.jxc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.scdd.jxc.util.Context;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	/**
	 * Handler执行完成之后调用这个方法
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exc)
			throws Exception {
		System.out.println("afterCompletion........");
	}

	/**
	 * Handler执行之后，ModelAndView返回之前调用这个方法
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle........");
	}

	/**
	 * Handler执行之前调用这个方法
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 获取请求的URL
		String url = request.getRequestURI();
		// URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
		if (url.indexOf("login") >= 0) {
			return true;
		}
		// 获取Session
		HttpSession session = request.getSession();
		if (session.getAttribute(Context.SESSION_USER_ID) != null) {
			return true;
		}
		
		// 不符合条件的，跳转到登录界面
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request,
				response);

		return false;
	}
}
