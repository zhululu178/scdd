package cn.scdd.jxc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.github.pagehelper.PageHelper;

import freemarker.template.Template;

public class BaseController {
	@Autowired
	protected FreeMarkerConfigurer freemarkerConfig;
	/** 请求 */
	protected HttpServletRequest request;
	/** 响应 */
	protected HttpServletResponse response;
	/** 回话 */
	protected HttpSession session;
	/** 每页数据 */	
	protected final static int PAGESIZE = 10;
	
	/**
	 * 设置请求基本
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	
	/**
	 * 根据ftl模板获得内容
	 * @param ftlTemplatePath
	 * @param contents
	 * @return
	 */
	protected String getContentFromTemplate(String ftlTemplatePath, Map<String, Object> contents) {
		Template template = null;
		String result = null;
		try {
			template = freemarkerConfig.getConfiguration().getTemplate(ftlTemplatePath);
			result=FreeMarkerTemplateUtils.processTemplateIntoString(template, contents);
		} catch (Exception e) {
			System.out.println("Freemarker模板处理失败:" + ftlTemplatePath);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 初始化分页信息
	 */
	protected void initPage() {
		String pageNo = this.request.getParameter("pageNo");
		if(pageNo != null) {
			PageHelper.startPage(Integer.parseInt(pageNo),PAGESIZE);
		} else {
			PageHelper.startPage(1,PAGESIZE);
		}
	}
}
