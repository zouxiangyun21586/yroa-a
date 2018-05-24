package com.yr.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Holiday;
import com.yr.service.HolidayService;

/**
 * 假期Controller层
 * @作者 林水桥
 * 2018年5月24日上午9:00:07
 */
@Controller("holidayController")
@RequestMapping("holiday")
public class HolidayController {

	@Autowired
	private HolidayService holidayService;
	
	/**
	 * 查询全部假期   带分页
	 * @param page       当前页
	 * @param limit		   每页多少条数据
	 * @param name       是否使用搜索框
	 * @param request    请求
	 * @return  String   返回json类型      
	 * String
	 * @作者 林水桥2018年5月24日上午11:53:44
	 */
	@ResponseBody
    @RequestMapping(value = "/getHoliday", method = RequestMethod.GET)
	public String getTest(int page, int limit, String name, HttpServletRequest request) {
		
		String a = holidayService.getHoliday(page, limit, name);
		
		return a;
		
	}
	
	/**
	 * 添加假期
	 * @param holiday 假期数据
	 * @param request 
	 * @param resp
	 * void
	 * @作者 林水桥2018年5月24日上午11:28:23
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add(Holiday holiday, HttpServletRequest request, HttpServletResponse resp) {
		
	}
	
	/**
	 * 动态修改 不修改的字段不做变动
	 * @param id   假期ID
	 * @param map  
	 * void
	 * @作者 林水桥2018年5月24日上午11:29:10
	 */
	@ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false)Integer id, Map<String, Object> map) {
        if (id != null) {
            map.put("holiday", holidayService.get(id));
        }
//        Map<String, Object> genders = new HashMap<String, Object>();
//        genders.put("1", "男");
//        genders.put("0", "女");
//        map.put("genders", genders);
    }
	
}
