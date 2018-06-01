package com.yr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.service.MenuService;

/**
 * 菜单相关 Controller
 * @author 周业好
 * 2018年5月31日 上午10:51:39
 */
@Controller
@RequestMapping(value = "menu")
public class MenuController {
	@Autowired
	private MenuService mser;
	/**
	 * 页面初始化菜单方法
	 * @author 周业好
	 * @param request 1
	 * @return 所显示的菜单 json
	 */
	@ResponseBody
	@RequestMapping(value = "/query", produces = "text/json;charset=UTF-8")
	public String query(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("use");
		return mser.query(name);
	}
}
