package com.yr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Leave;
import com.yr.service.LeaveService;

/**
 * 请假 Controller
 * @author zxy
 *
 * 2018年5月23日 上午10:55:45
 *
 */
@Controller
@RequestMapping(value = "leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	final Integer number = 2;
	
	/**
	 * 添加请假
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:44:16
	 * 
	 * @param leave 请假对象
	 * @param request 获取当前登录用户名
	 * @return 返回Json格式的Stirng 数据
	 */
	@Transactional
	@RequestMapping(value = "/Leave", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String add(Leave leave, HttpServletRequest request) {
		String acc = (String) request.getSession().getAttribute("use");
		String str = leaveService.add(leave, acc);
		return str;
	}

	/**
	 * 取消请假
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:45:05
	 * 
	 * @param id 定位到指定的人
	 * @return String(会跳入指定页面)
	 */
	@Transactional
	@RequestMapping(value = "/Leave", method = RequestMethod.DELETE, produces = "text/json;charset=UTF-8")
	public String cancel(Integer id) {
		String str  = leaveService.cancelLeave(id);
		return str;
	}

	/**
	 * 分页查询
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:45:07
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @return Json格式的String数据
	 */
	@RequestMapping(value = "/Leave", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public @ResponseBody String query(Integer page, Integer limit, String name) {
		String str = leaveService.query(page, limit, name);
		return str;
	}
	
	/**
	 * 查询指定学生的假期
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:45:53
	 * 
	 * @param studenCode 学生code
	 * @return Json格式数据
	 */
	@RequestMapping(value = "/Leave", method = RequestMethod.PUT, produces = "text/json;charset=UTF-8")
	public @ResponseBody String query(String studenCode) {
		String str = leaveService.query(studenCode);
		return str;
	}
}
