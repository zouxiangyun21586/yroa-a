package com.yr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Student;
import com.yr.service.StudentService;
import com.yr.util.JsonUtils;

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
	 * @Date : 2018年5月22日下午5:13:42
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String 返回Json格式的String数据
	 *
	 * @describe : 查询所有学生
	 */
	@ResponseBody
	@RequestMapping(value = "/student")
	public String queryStudent() {
		List<Student> studentList = studentService.queryStudent();
		String result = JsonUtils.listToJson(studentList);
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
		studentService.addStudent(student);
		return null;
	}
	 
}
