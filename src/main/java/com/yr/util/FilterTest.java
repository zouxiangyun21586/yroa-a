package com.yr.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 过滤器
 * @author 周业好
 * 2018年5月28日 上午11:34:37
 */
public class FilterTest implements Filter {
	private FilterConfig config;
	/**
	 * 过滤器的销毁方法,退出的时候会调用
	 */
	@Override
	public void destroy() {
		this.config = null;
	}
	/**
	 * 过滤器的执行方法,一有访问就会进来这个方法
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request; //得到 request 对象
		HttpServletResponse hresponse = (HttpServletResponse) response; //得到 response 对象
		HttpSession session = hrequest.getSession();
		
		String usName = (String) session.getAttribute("use");
		String url = hrequest.getRequestURI();
		//暂时注释掉
		if (url.endsWith("login.html") || url.endsWith("login")) { //判断是不是登录页面
			chain.doFilter(request, response);
		} else if (url.endsWith("index")) {
			hresponse.sendRedirect("/yroa-a/login.html");
		} else if (null != usName) {
			chain.doFilter(request, response);
		} else if (url.endsWith("add.jsp") || url.endsWith("add")) {
			chain.doFilter(request, response);
		} else {
			//chain.doFilter(request,response);
			hresponse.sendRedirect("/yroa-a/login.html");
		}
	}
	/**
	 * 过滤器的初始化方法,在启动的时候调用这个方法
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			String a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		config = filterConfig;
	}
	
}
