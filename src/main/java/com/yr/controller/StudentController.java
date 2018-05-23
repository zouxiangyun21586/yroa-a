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
	@RequestMapping(value = "/student")
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
	@RequestMapping(value = "/student", method = {RequestMethod.POST})
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
	 * @describe : 根据学术id删除学生信息
	 */
	@ResponseBody
	@RequestMapping(value = "/student", method = {RequestMethod.DELETE})
	public String deleteStudent(Integer id) {
		String result = studentService.deleteStudent(id);
		return result;
	}
	
	 
}
