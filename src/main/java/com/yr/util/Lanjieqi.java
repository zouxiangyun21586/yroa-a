package com.yr.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器类
 * @author 周业好
 * 2018年5月28日 上午11:51:10
 */
public class Lanjieqi implements HandlerInterceptor {
	@PersistenceContext
	private EntityManager em;
//	public static List<Right> list = new ArrayList<>();
	/**
	 * 此方法会在controller的目标方法调用前被调用 ,如果返回false则拦截器的后面两个方法和目标方法都不会再调用,可以做权限,日志,事务等功能
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		 // 得到请求的url
        String url = request.getRequestURI();
//        String[] urls = url.split("/");
//        for (int i = 0; i < urls.length; i++) {
//            url = urls[urls.length - 1];
//        }
		String use = (String) session.getAttribute("use");
		System.err.println("登陆的账号:" + use);
		String urlVal = request.getRequestURI(); //得到请求的路径
		System.err.println("请求的路径:" + urlVal);
		//登录路径 和 登录相关 直接放行
		if ("/yroa-a/login.html".equals(urlVal) || "/yroa-a/layui/font/iconfont.woff".equals(urlVal) 
				|| "/yroa-a/layui/font/iconfont.ttf".equals(urlVal)) {
			return true;
		} else if (null == use || "".equals(use)) {
			 response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        if (!subject.isPermitted(url)) {
            response.sendRedirect(request.getContextPath() + "/shiro-unauthorized");
            return false;
        }
        return true;
		//注册账号,激活账号,验证码存储 放行
		

//		//登录路径 和 登录相关 直接放行
//		if ("/yroa-a/login.html".equals(urlVal) || "/yroa-a/layui/font/iconfont.woff".equals(urlVal) 
//				|| "/yroa-a/layui/font/iconfont.ttf".equals(urlVal)) {
//			return true;
//		} else if (null != use || !"".equals(use)) {
//			
//			return true;
//		}
		//此角色的所有 权限路径
//		List<String> ri = (List<String>) session.getAttribute("right");
//		boolean bol = false;
////		验证权限 是否有
//		for (String str : ri) {
//			if (null != str && !"".equals(str)) {
//				if (str.equals(urlVal)) {
//					bol = true;
//					break;
//				}
//			}
//		}
//		return bol;
//		response.sendRedirect("/yroa-a/login.html");
//		request.getRequestDispatcher("/WEB-INF/admin/login.jsp").forward(request,  
//	                response);
//		return false;
	}
	/**
	 *  调用目标方法之后,渲染视图之前被调用 可以对视做出更改,或修改请求域
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
		//记录日志  
	}
	/**
	 * 渲染视图之后被调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
	}
}
