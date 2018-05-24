package com.yr.controller;

import java.util.List;
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

import com.yr.entity.Clas;
import com.yr.entity.Holiday;
import com.yr.service.ClasService;
import com.yr.service.HolidayService;
import com.yr.util.DateJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

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
	@Autowired
	private ClasService claService;
	
	/**
	 * 查询全部假期   带分页
	 * @param page       当前页
	 * @param limit		   每页多少条数据
	 * @param name       是否使用搜索框
	 * @param response    请求
	 * @return  String   返回json类型      
	 * String
	 * @作者 林水桥2018年5月24日上午11:53:44
	 */
	@ResponseBody
    @RequestMapping(value = "/getHoliday", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String getTest(int page, int limit, String name, HttpServletResponse response) {
		
		String a = holidayService.getHoliday(page, limit, name);
		
		return a;
		
	}
	
	/**
     * 进入假期添加页面
     * @return     届次json集合
     * String
     * 2018年3月1日下午10:09:29
     */
	@ResponseBody
    @RequestMapping(value = "/adds", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String adds() {
//        map.put("clas", claService.query());
//        map.put("holiday", new Holiday());
		List<Clas> clas = claService.query();
//		String a = JsonUtils.beanListToJson(clas, null, false);
		String a = sendArray(clas);
        return a;
    }
	/**
     * 过滤json嵌套异常字段
     * @param object  对象
     * @return
     * String
     * 2018年4月9日下午10:06:38
     */
    public static String sendArray(Object object) {
    	JsonConfig jsonConfig = new JsonConfig();

    	jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    	
    	jsonConfig.registerJsonBeanProcessor(java.sql.Date.class, new DateJsonValueProcessor());

    	JSONArray json = JSONArray.fromObject(object, jsonConfig);

    	return json.toString();
    }
    
	
	/**
	 * 添加假期
	 * @param holiday 假期数据
	 * @param request 
	 * @param response
	 * void
	 * @作者 林水桥2018年5月24日上午11:28:23
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add(Holiday holiday, HttpServletRequest request, HttpServletResponse response) {
		
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
