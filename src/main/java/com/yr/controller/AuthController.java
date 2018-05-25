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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yr.entity.Account;
import com.yr.service.AccountService;

/**
 * 用户Conntroller
 * @author 周业好
 * 2018年5月22日 上午10:34:00
 */
@Controller
@RequestMapping(value  =  "auth")
public class AuthController  {
	@Autowired
	private AccountService acc;
	
	private static final int THREE = 3;
	/**
	 * 
	 * @author 周业好
	 * 分页方法
	 * @param page 当前第几页
	 * @param limit 每页多少条
	 * @param name 搜索条件
	 * @return x
	 */
	@ResponseBody
	@RequestMapping(value = "queryfy", produces = "text/json;charset=UTF-8")
	public String queryFenye(int page, int limit, String name) {
		String json = acc.getFenye(page, limit, name);
		return json;
	}
	/**
	 * 查出所有的角色
	 * @author 周业好
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "qrole", produces = "text/json;charset=UTF-8")
	public String queryRoleAll() {
		String json = acc.queryRoleAll();
		return json;
	}
	
	/**
	 * 添加  (多余功能,作废)
	 * @param account 账户实体类
	 * @param code 角色code
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	//@Valid   BindingResult result
	public String addId(Account account, String code) {
		return acc.addId(account, code);
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
	@RequestMapping(value = "upd_echo")
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
	 /**
     * 重置密码
     * @author 周业好
     * @param username 账号
     * @return json
     */
	@ResponseBody
	@RequestMapping(value = "reset", method = RequestMethod.PUT, produces = "text/json;charset=UTF-8")
	public String resetPassWord(String username) {
		return acc.resetPassWord(username);
	}
	 /**
     * 启用停用
     * @author 周业好
     * @param name 账号
     * @return json
     */
	@ResponseBody
	@RequestMapping(value = "switchs", method = RequestMethod.PUT, produces = "text/json;charset=UTF-8")
	public String kaiguan(String name) {
		return acc.kaiguan(name);
	}
}
