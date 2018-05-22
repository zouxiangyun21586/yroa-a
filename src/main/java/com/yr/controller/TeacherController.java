package com.yr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yr.service.TeacherService;

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
}
