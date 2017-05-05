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
	/** ���� */
	protected HttpServletRequest request;
	/** ��Ӧ */
	protected HttpServletResponse response;
	/** �ػ� */
	protected HttpSession session;
	/** ÿҳ��� */	
	protected final static int PAGESIZE = 2;
	/** 页面的出错消息 */	
	protected final static String ERR_MSG = "err_msg";
	
	/**
	 * ���������
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
	 * ���ftlģ��������
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
			System.out.println("Freemarkerģ�崦��ʧ��:" + ftlTemplatePath);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ��ʼ����ҳ��Ϣ
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
