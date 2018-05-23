package com.yr.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	 * @param roleCode 角色code
	 * @param requ get
	 * @param resp POST
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	//@Valid   BindingResult result
	public void addId(Account account, String roleCode, HttpServletRequest requ, HttpServletResponse resp) {
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
		
		Integer val  =  0;
//		if (passW.equals(oldpassW)) {
			try  {
				val  =  acc.addId(account,roleCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	/**
	 * 删除
	 * @param i 1 
	 * @param request 3
	 * @param resp 4
	 */
	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	public void del(@PathVariable(value = "id") Integer i, HttpServletRequest request, HttpServletResponse resp) {
		String pagesize = request.getParameter("pagesize");
//		acc.delAccRole(i); //解除关系
//		int z = acc.del(i); //删除
//		if (0 == z) {
//			mo.addAttribute("z", "删除失败,没有这个编号");
//		}
		try {
			resp.getWriter().write(pagesize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改账户信息
	 * @param account 3
	 * @param result 2 
	 * @param map 1
	 * @return modelandview
	 */
	@RequestMapping(value = "upda", method = RequestMethod.PUT)
	public ModelAndView upd(@Valid @ModelAttribute("accou")Account account, BindingResult result, 
			Map<String, Object>map) {
		ModelAndView mv = new ModelAndView();
		if (result.getErrorCount() > 0) {
            List<ObjectError> allErrors = result.getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)) {
                for (ObjectError allError : allErrors) {
                	//allError.getDefaultMessage() 可看见错误
                	String er = allError.getDefaultMessage();
                }
//                map.put("user", y);
                map.put("accou", account);
                mv.setViewName("update");
                return mv;
            }
        }
		int i  = acc.upd(account);
		if (0 == i) {
			mv.addObject("z", "修改失败,检查输入");
		}
		mv.setViewName("redirect:/jsp/list.jsp");
		return mv;
	}
	/**
	 * 查询单个(修改数据回显)
	 * @param i 要修改的id
	 * @param map 2
	 * @return 查出来的json
	 */ 
	@RequestMapping(value = "upd_echo/{id}")
	public String query(@PathVariable(value = "id")Integer i, Map<String, Object> map) {
		Account e = acc.query(i);
		map.put("users", e);
		return "update";
	}
	/**
	 * 
	 * 修改密码
	 * @param id 账号id
	 * @param userN 账号
	 * @param oldpassword 旧密码
	 * @param passW 新密码
	 * @param resp 1
	 *  0.修改成功  1.账号不存在  2.旧密码错误  
	 */
	@RequestMapping(value = "/updatePass", method = RequestMethod.POST)
	public void updatePass(String oldpassword, Integer id, String passW, String userN, HttpServletResponse resp) {
		String val = acc.updatePass(oldpassword, userN, id, passW);
		try {
			resp.getWriter().write(val);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
