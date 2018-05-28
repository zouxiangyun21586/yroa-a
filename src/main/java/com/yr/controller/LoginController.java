package com.yr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Account;
import com.yr.service.AccountService;

import net.sf.json.JSONObject;

/**
 * 登录 Controller
 * @author 周业好
 * 2018年5月27日 上午9:32:53
 */
@Controller
public class LoginController {
	private static Account u = new Account();
	@Autowired
	private AccountService accService;
	/**
	 * 验证登录方法
	 * @author 周业好
	 * @param acc 账户实体类
	 * @param request 1
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "qrole", produces = "text/json;charset=UTF-8")
	public String verifyLogin(Account acc, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		String errNumce = request.getParameter("errNumce"); //输入错误次数
		//判断输入框是否输入
		if (null == acc.getUserName() || "".equals(acc.getUserName()) || null == acc.getPassWord() 
				|| "".equals(acc.getPassWord())) {
			map.put("code", 1);
			map.put("msg", "输入框不能不填");
			return JSONObject.fromObject(map).toString();
		}
		UsernamePasswordToken token = new UsernamePasswordToken(acc.getUserName(), acc.getPassWord());  
        Subject currentUser = SecurityUtils.getSubject();
        try {
        	currentUser.login(token);
        	u = accService.query(acc.getUserName()); //根据账户查权限
			final int i = 30;
			final int j = 60;
			session.setMaxInactiveInterval(i * j); //设置session 的时间
			session.setAttribute("right", u);
			session.setAttribute("use", acc.getUserName());
//			mav.setViewName("redirect:/my/queryAll");
			map.put("code", 0);
			map.put("msg", "登录成功");
        } catch (NullPointerException e) {
        	map.put("code", 1);
			map.put("msg", "账号不存在");
        } catch (Exception e) {
        	Integer er = Integer.valueOf(errNumce);
			er += er;
			map.put("code", 1);
			map.put("msg", "密码错误");
//        	e.printStackTrace();
        }
		return JSONObject.fromObject(map).toString();
	}
	
	
	/**
	 * 设置 验证码session 
	 * @param request 1
	 * @param resp 1
	 */
	@RequestMapping(value = "/yanCode", method = RequestMethod.POST)
	public void yanzhengCode(HttpServletRequest request, HttpServletResponse resp) {
		try {
			String yancode = request.getParameter("yancode"); //页面输入验证码并且大写转换
			HttpSession session = request.getSession();
			session.removeAttribute("yancode");
			final int i = 30;
			final int j = 6;
			session.setMaxInactiveInterval(i * j); //设置session 的时间
			session.setAttribute("yancode", yancode);
			resp.getWriter().write("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
