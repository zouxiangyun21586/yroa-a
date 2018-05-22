package com.yr.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yr.entity.Account;
import com.yr.service.AccountService;

/**
 * 用户Conntroller
 * @author 周业好
 * 2018年5月22日 上午10:34:00
 */
@Controller
@RequestMapping(value  =  "acc")
public class AccountController  {
	@Autowired
	private AccountService acc;
	
	private static final int THREE = 3;
	/**
	 * 添加
	 * @param account 账户实体类
	 * @param requ get
	 * @param resp POST
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	//@Valid   BindingResult result
	public void addId(Account account, HttpServletRequest requ, HttpServletResponse resp)  {
		resp.setContentType("text/json");
		resp.setCharacterEncoding("utf-8");
		String oldpassW = requ.getParameter("oldpassW"); //旧密码
//		Users users  =  new Users();
//		users.setName(name);
//		users.setUserName(userN);
//		users.setPassWord(passW);
//		users.setState("1");//添加的账户默认未激活
//		users.setMail(mail);
//		users.setAddTime(new Date());
		
//		Integer val  =  0;
//		if (passW.equals(oldpassW)) {
//			try  {
//				val  =  acc.addId(users);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else {
//			val  =  THREE; //表示两次密码不一样
//		}
		try {
			resp.getWriter().write(""); //val.toString()
		} catch (IOException e) {
			e.printStackTrace();
		}
//		mv.setViewName("redirect:/jsp/list.jsp");
		//return mv;
	}

}
