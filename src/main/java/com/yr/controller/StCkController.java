package com.yr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yr.service.StCkService;

/**
 * 学生考勤Controller层
 * @author 林水桥
 * 2018年5月25日下午10:00:07
 */
@Controller("stCkController")
public class StCkController {
	
	@Autowired
	private StCkService stCkService;
	
	
	
	
}
