package com.yr.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Student;
import com.yr.service.StudentService;

/**
 * 
 * @Date:2018年5月22日下午3:33:02	
 *
 * @author: 唐子壕
 * 
 * 学生管理controller层
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * 
	 * @Date : 2018年5月22日下午7:10:05
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String 返回json格式
	 * 
	 * @describe : 分页查询
	 *
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 搜索条件,模糊查询
	 * @param modules 搜索条件,根据是否毕业查询
	 */
	@ResponseBody
	@RequestMapping(value = "/student", produces = "text/json;charset=UTF-8")
	public String queryStudent(Integer page, Integer limit, String name, String modules) {
		String result = studentService.queryStudent(page, limit, name, modules);
		return result;
	}
	
	/**
	 * 
	 * @Date : 2018年5月22日下午5:32:55
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String 返回Json格式的String数据
	 *
	 * @describe : 添加学生
	 *
	 * @param student 用来接收页面传过来的值
	 */
	@ResponseBody
	@RequestMapping(value = "/student", method = {RequestMethod.POST}, produces = "text/json;charset=UTF-8")
	public String addStudent(Student student) {
		String result = studentService.addStudent(student);
		return result;
	}
	
	/**
	 * 
	 * @Date : 2018年5月23日下午3:05:04
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String 返回Json格式的String数据
	 *
	 * @param id 学生id
	 * 
	 * @describe : 根据学生id删除学生信息
	 */
	@ResponseBody
	@RequestMapping(value = "/student", method = {RequestMethod.DELETE}, produces = "text/json;charset=UTF-8")
	public String deleteStudent(Integer id) {
		String result = studentService.deleteStudent(id);
		return result;
	}
	
	/**
	 * 
	 * @Date : 2018年5月24日下午5:42:55
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String
	 *
	 * @describe : 根据学生id修改学生信息
	 *
	 * @param student 
	 */
	@ResponseBody
	@RequestMapping(value = "/student", method = {RequestMethod.PUT}, produces = "text/json;charset=UTF-8")
	public String updateStudent(@ModelAttribute("student")Student student) {
		String result = studentService.updateStudent(student);
		return result;
	}
	
	/**
	 * 
	 * @Date : 2018年5月25日上午8:57:31
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String
	 *
	 * @param id 
	 * 
	 * @param map 
	 * 
	 * @describe     用map传入一个对象到页面用于修改数据回显 ,未就业修改回显
	 */
	@RequestMapping(value = "/updateWjyDisplay", produces = "text/json;charset=UTF-8")
	public String updateWjyDisplay(Integer id, Map<String, Object> map) {
		HashMap<String, String> sex = new HashMap<String, String>();
		sex.put("0", "女");
		sex.put("1", "男");
		map.put("sex", sex);
		HashMap<String, String> isFinish = new HashMap<String, String>();
		isFinish.put("0", "未毕业");
		isFinish.put("1", "已毕业");
		map.put("isFinish", isFinish);
		HashMap<String, String> isItDisplayed = new HashMap<String, String>();
		isItDisplayed.put("1", "是");
		isItDisplayed.put("0", "否");
		map.put("isItDisplayed", isItDisplayed);
		Student student = studentService.updateDisplay(id);
		map.put("student", student);
		return "student/studentUpdate";
	}
	
	
	/**\
	 *
	 * @Date : 2018年5月31日下午3:15:48
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String 
	 *
	 * @param id 
	 */
	@ResponseBody
	@RequestMapping(value = "/employment", produces = "text/json;charset=UTF-8")
	public String employment(Integer id) {
		String result = studentService.employment(id);
		return result;
		
	}
	
	/**
	 * 
	 * @Date : 2018年5月25日上午8:57:31
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String
	 *
	 * @param id 
	 * 
	 * @param map 
	 * 
	 * @describe     用map传入一个对象到页面用于修改数据回显,已就业修改回显
	 */
	@RequestMapping(value = "/updateYjyDisplay", produces = "text/json;charset=UTF-8")
	public String updateYjyDisplay(Integer id, Map<String, Object> map) {
		HashMap<String, String> sex = new HashMap<String, String>();
		sex.put("0", "女");
		sex.put("1", "男");
		map.put("sex", sex);
		HashMap<String, String> isItDisplayed = new HashMap<String, String>();
		isItDisplayed.put("1", "是");
		isItDisplayed.put("0", "否");
		map.put("isItDisplayed", isItDisplayed);
		Student student = studentService.updateDisplay(id);
		map.put("student", student);
		return "student/jy";
	}
	
	/**
	 * 
	 * @Date : 2018年5月25日上午8:57:31
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String
	 *
	 * @param id 
	 * 
	 * @param map 
	 * 
	 * @describe     用map传入一个对象到页面用于修改数据回显  
	 */
	@RequestMapping(value = "/jyDisplay", produces = "text/json;charset=UTF-8")
	public String jyDisplay(Integer id, Map<String, Object> map) {
		Student student = studentService.updateDisplay(id);
		map.put("jy", student);
		return "student/jy";
	}
	
	
	/**
	 * @Date : 2018年5月25日上午8:57:31
	 * 
	 * @author : 唐子壕
	 * 
	 * @describe : 查询届次表到修改页面或添加页面进行修改回显和下拉选择
	 * 
	 * @return : String 
	 */
	@ResponseBody
	@RequestMapping(value = "/queryYear", produces = "text/json;charset=UTF-8")
	public String queryYear() {
		String result = studentService.queryCls();
		return result;
	}
	
	/**
	 * 
	 * @Date : 2018年5月28日上午8:14:45
	 * 
	 * @author : 唐子壕
	 *	
	 * @param student 
	 *
	 * @return : String
	 * 
	 * @describe 修改已就业学生信息
	 *
	 */
	@ResponseBody
	@RequestMapping(value = "/employmentEditors", produces = "text/json;charset=UTF-8")
	public String employmentEditors(@ModelAttribute("student")Student student) {
		String result = studentService.employmentEditors(student);
		return result;
	}
	
	
	/**
	 * 
	 * @Date : 2018年5月26日上午9:53:41
	 * 
	 * @author : 唐子壕
	 *	
	 * @describe 动态修改 不修改的字段不做变动
	 * 
	 * @param map 用来存放数据
	 * 
	 * @param student 用来得到id
	 */
	@ModelAttribute
    public void getStudent(Student student, Map<String, Object> map) {
		if (student.getId() != null) {
			map.put("student", studentService.updateDisplay(student.getId()));
		}
    }
	
	
}
