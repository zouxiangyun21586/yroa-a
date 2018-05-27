package com.yr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Teacher;
import com.yr.service.TeacherService;
import com.yr.util.JsonUtils;

import net.sf.json.JSONObject;

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
	@ResponseBody
	@RequestMapping(value = "/teacher", method = RequestMethod.POST)
	public String add(Teacher teacher, ModelMap map) {
		String str = teacherService.add(teacher);
		return str;
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
	@ResponseBody
	@RequestMapping(value = "/teacher", method = RequestMethod.DELETE)
	public String del(Teacher teacher, ModelMap map, String code) {
		String str = teacherService.delete(teacher);
		if (str.equals("succ")) {
			map.put("code", 0);
			map.put("msg", "添加成功");
		} else if (str.equals("error")) {
			map.put("code", 1);
			map.put("msg", "添加失败");
		}
		String result = JSONObject.fromObject(map).toString();
		return result;
	}

	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 下午4:01:30
	 * 
	 * @param teacher 老师对象
	 * @return 执行完这个方法后去到哪个页面
	 */
	@ResponseBody
	@RequestMapping(value = "/teacher", method = RequestMethod.PUT)
	public String upd(@ModelAttribute("teacher")Teacher teacher) {
		String str = teacherService.update(teacher);
		return str;
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
	@RequestMapping(value = "/getTeacher", produces = "text/json;charset=UTF-8")
	public String get(Integer id, ModelMap mod) {
		Teacher teacherGet = teacherService.get(id);
		mod.addAttribute("teacher", teacherGet);
		return jsp;
	}

	/**
	 * 查询等级
	 * @return String   老师数据,json格式
	 * 2018年5月26日上午11:51:01
	 */
	@ResponseBody
	@RequestMapping(value = "/teacherLev", produces = "text/json;charset=UTF-8")
	public String huixian() {
		List<Teacher> listTeacher = teacherService.query();
		String str = JsonUtils.beanListToJson(listTeacher);
		return str;
	}
	
	/**
	 * 查询等级
	 * @return String   老师数据,json格式
	 * 2018年5月26日上午11:51:01
	 */
	@ResponseBody
	@RequestMapping(value = "/teacherIs", produces = "text/json;charset=UTF-8")
	public String huixianIs() {
		List<Teacher> listTeacher = teacherService.queryIs();
		String str = JsonUtils.beanListToJson(listTeacher);
		return str;
	}
	
	/**
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
