package com.yr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	String jsp = "teacherFolder/teacherFolderUpd";
	
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
	@RequestMapping(value = "/teacher", method = RequestMethod.POST)
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
	 * @param code 用于找到这个方法
	 * @return 执行完这个方法后去到哪个页面
	 */
	@RequestMapping(value = "/teacher", method = RequestMethod.DELETE)
	public String del(Teacher teacher, ModelMap map, String code) {
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
	@RequestMapping(value = "/teacher", method = RequestMethod.PUT)
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
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月22日 下午4:04:50
	 * @param mod   返回页面数据
	 * @param id 需数据回显的老师id
	 * @return Json格式的String数据
	 */
	@RequestMapping(value = "/getTeacher", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String get(Integer id, ModelMap mod) {
//		Map<String, Object> map = new HashMap<>();
		Teacher teacherGet = teacherService.get(id);
//		map.put("teacher", teacherGet);
		mod.addAttribute("teacher", teacherGet);
		return jsp;
	}

	/**
	 * 查询所有数据回显使用
	 * @param id  老师ID
	 * @return String   老师数据,json格式
	 * 2018年5月26日上午11:51:01
	 */
//	@ModelAttribute
	@ResponseBody
	@RequestMapping(value = "/teacherHX", produces = "text/json;charset=UTF-8")
	public String huixian(Integer id) {
//		map.put("teacher", teacherService.query());
		Teacher teacherGet = teacherService.get(id);
		
		return JsonUtils.beanToJson(teacherGet);
	}
	
	/**
	 * 查询 (提供给假期使用)
	 * @author zxy
	 * 
	 * 2018年5月22日 下午4:07:04
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @return Json格式的String数据
	 */
	@RequestMapping(value = "/teacher", produces = "text/json;charset=UTF-8")
	public @ResponseBody String sel(Integer page, Integer limit, String name) {
		String str = teacherService.query(page, limit, name);
		return str;
		
	}
}
