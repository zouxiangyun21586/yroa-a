package com.yr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yr.service.StCkService;

/**
 * 学生考勤Controller层
 * @author 林水桥
 * 2018年5月25日下午10:00:07
 */
@Controller("stCkController")
@RequestMapping("attendance")
public class StCkController {
	
	@Autowired
	private StCkService stCkService;
	
	/**
	 * 查询所有签到人员数据
	 * @author 林水桥
	 * @return String 放回学生集合  json
	 * 2018年5月26日上午9:15:48
	 */
	@RequestMapping(value = "/atteList", produces = "text/json;charset=UTF-8")
	public String atteList() {
		
		return null;
	}
	
	
}
