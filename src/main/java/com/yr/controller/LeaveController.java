package com.yr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Leave;
import com.yr.service.LeaveService;
import com.yr.util.JsonUtils;

/**
 * 请假 Controller
 * @author zxy
 *
 * 2018年5月23日 上午10:55:45
 *
 */
@Controller
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
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return 返回Json格式的Stirng 数据
	 */
	@Transactional
	@RequestMapping(value = "/Leave", method = RequestMethod.POST)
	public String add(Leave leave, ModelMap map) {
		Boolean boo = leaveService.add(leave);
		if (boo) {
			map.put("succ", number);
			return "show";
		} else {
			map.put("error", 1);
			return "show";
		}
	}

	/**
	 * 取消请假
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:45:05
	 * 
	 * @param leave 请假实体对象
	 * @param map 传递数据到结果页面
	 * @return String(会跳入指定页面)
	 */
	@Transactional
	@RequestMapping(value = "/Leave/{id}", method = RequestMethod.DELETE)
	public String cancel(Leave leave, ModelMap map) {
		Boolean bool = leaveService.cancelLeave(leave);
		if (bool) {
			map.put("succ", number);
			return "show";
		} else {
			map.put("error", 1);
			return "show";
		}
	}

	/**
	 * 查询所有
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:45:07
	 * 
	 * @param response 发送
	 * @param request 接收
	 * @return Json格式的String 数据
	 */
	@RequestMapping(value = "/Leave", method = RequestMethod.GET)
	public @ResponseBody String sel(HttpServletResponse response, HttpServletRequest request) {
		List<Leave> listUser = leaveService.query();
		String str = "";
		try {
			// false表示数组中的属性不需要转成json,如果是true代表只将数组中的属性转成json格式
			str = JsonUtils.beanListToJson(listUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
		
	}
	
	/**
	 * 查询指定学生的假期
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:45:53
	 * 
	 * @param response 发送数据
	 * @param request 接收数据
	 * @param studenCode 学生code
	 * @return Json格式数据
	 */
		@RequestMapping(value = "/Leave/studenCode", method = RequestMethod.GET)
		public @ResponseBody String query(HttpServletResponse response, 
				HttpServletRequest request, @PathVariable(value = "studenCode") String studenCode) {
			List<Leave> listUser = leaveService.query(studenCode);
			String str = "";
			try {
				// false表示数组中的属性不需要转成json,如果是true代表只将数组中的属性转成json格式
				str = JsonUtils.beanListToJson(listUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
			
		}
	}
