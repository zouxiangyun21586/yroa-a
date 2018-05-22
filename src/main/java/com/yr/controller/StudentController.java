package com.yr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
