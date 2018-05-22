package com.yr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Teacher;
import com.yr.service.TeacherService;
import com.yr.util.JsonUtils;

/**
 * 老师controller层
 * 
 * @author zxy
 *
 * 2018年5月22日 上午8:51:35
 *
 */
@Controller
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	final Integer number = 2;
	
	/**
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月22日 下午3:56:42
	 * 
	 * @param teacher 老师对象
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return 返回到哪个界面
	 */
	@Transactional
	@RequestMapping(value = "/Teacher", method = RequestMethod.POST)
	public String add(Teacher teacher, ModelMap map) {
		Boolean boo = teacherService.add(teacher);
		if (boo) {
			map.put("succ", number);
			return "show";
		} else {
			map.put("error", 1);
			return "show";
		}
	}

	/**
	 * 删除
	 * @author zxy
	 * 
	 * 2018年5月22日 下午4:00:27
	 * 
	 * @param teacher 老师对象
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return 执行完这个方法后去到哪个页面
	 */
	@Transactional
	@RequestMapping(value = "/Teacher/{id}", method = RequestMethod.DELETE)
	public String del(Teacher teacher, ModelMap map) {
		Boolean bool = teacherService.delete(teacher);
		if (bool) {
			map.put("succ", number);
			return "show";
		} else {
			map.put("error", 1);
			return "show";
		}
	}

	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 下午4:01:30
	 * 
	 * @param teacher 老师对象
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return 执行完这个方法后去到哪个页面
	 */
	@Transactional
	@RequestMapping(value = "/Teacher", method = RequestMethod.PUT)
	public String upd(Teacher teacher, ModelMap map) {
		Boolean bool = teacherService.update(teacher);
		if (bool) {
			map.put("succ", number);
			return "show";
		} else {
			map.put("error", 1);
			return "show";
		}
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午4:04:50
	 * 
	 * @param id 需数据回显的老师id
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return Json格式的String数据
	 */
	@RequestMapping(value = "/getTeacher", method = RequestMethod.GET)
	public @ResponseBody String get(Integer id, ModelMap map) {
		Teacher listUser = teacherService.get(id);
		String str = JsonUtils.beanToJson(listUser);
		return str;
	}

	/**
	 * 查询
	 * @author zxy
	 * 
	 * 2018年5月22日 下午4:07:04
	 * 
	 * @param response 发送数据
	 * @param request 接收数据
	 * @return Json格式的String数据
	 */
	@RequestMapping(value = "/Teacher", method = RequestMethod.GET)
	public @ResponseBody String sel(HttpServletResponse response, HttpServletRequest request) {
		List<Teacher> listUser = teacherService.query();
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
