package com.yr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 搜索条件
	 */
	@ResponseBody
	@RequestMapping(value = "/student", produces = "text/json;charset=UTF-8")
	public String queryStudent(Integer page, Integer limit, String name) {
		String result = studentService.queryStudent(page, limit, name);
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
	 * @param id 学生id
	 * 
	 * @param student 
	 */
	@ResponseBody
	@RequestMapping(value = "/student", method = {RequestMethod.PUT}, produces = "text/json;charset=UTF-8")
	public String updateStudent(Integer id, Student student) {
		String result = studentService.updateStudent(id, student);
		return result;
	}
	
	/**
	 * 
	 * @Date : 2018年5月24日下午7:12:54
	 * 
	 * @author : 唐子壕 
	 *	
	 * @return : String 
	 *
	 * @param id 学生code 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateDisplay", produces = "text/json;charset=UTF-8")
	public String updateDisplay(Integer id) {
		String result = studentService.updateDisplay(id);
		return result;
	}
	
	
	
	
	
	 
}
