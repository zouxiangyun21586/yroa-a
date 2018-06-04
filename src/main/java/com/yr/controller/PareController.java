package com.yr.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Parents;
import com.yr.service.PareService;

import net.sf.json.JSONObject;

/**
 * 家长Controller层
 * @作者 林水桥
 * 2018年6月4日下午3:05:18
 */
@Controller("pareController")
@RequestMapping("parents")
public class PareController {
	@Autowired
	private PareService pareService;
	private static final Integer T11 = 11;
	
	/**
	 * 家长分页查询
	 * @author 林水桥
	 * @param page   当前页
	 * @param limit  每页多少条数据
	 * @param name   家长姓名 模糊查询
	 * @return String 返回json数据
	 * 2018年6月4日下午3:15:05
	 */
	@ResponseBody
	@RequestMapping(value = "/getParents", produces = "text/json;charset=UTF-8")
	public String getParents(int page, int limit, String name) {
		String json = "";
		try {
			name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			json = pareService.getParents(page, limit, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 家长添加
	 * @author 林水桥
	 * @param parents   家长数据
	 * @param stuName   学生姓名
	 * @return String   
	 * 2018年6月4日下午4:41:49
	 */
	@RequestMapping(value = "/add", produces = "text/json;charset=UTF-8")
	public String add(Parents parents, String stuName) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (parents.getTel().length() == T11) {
			Integer a = pareService.add(parents, stuName);
			if (null == a) {
				map.put("code", 1);
				map.put("msg", "添加失败");
			} else {
				map.put("code", 0);
				map.put("msg", "添加成功");
			}
		}
		return JSONObject.fromObject(map).toString();
	}
	
	
	
}
