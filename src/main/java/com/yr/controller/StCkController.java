package com.yr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Student;
import com.yr.service.StCkService;
import com.yr.service.StudentService;
import com.yr.util.JsonUtils;

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
	@Autowired
	private StudentService studService;
	
	/**
	 * 查询所有签到人员数据
	 * @author 林水桥
	 * @return String 放回学生集合  json
	 * 2018年5月26日上午9:15:48
	 */
	@ResponseBody
	@RequestMapping(value = "/atteList", produces = "text/json;charset=UTF-8")
	public String atteList() {
		
		List<Student> student = studService.queryNoGre();
		
		return JsonUtils.beanListToJson(student);
	}
	
	/**
	 * 学生签到
	 * @author 林水桥
	 * 
	 * @return String 学生签到状态 0为成功
	 * 2018年5月26日下午2:54:25
	 */
	@ResponseBody
	@RequestMapping(value = "/signIn", produces = "text/json;charset=UTF-8")
	public String signIn() {
		
		return null;
	}
	
	
	
}
