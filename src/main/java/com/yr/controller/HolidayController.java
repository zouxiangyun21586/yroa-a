package com.yr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yr.util.JsonUtils;

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
	private static String addr1 = "attendance/holiday/holidayUpdate";
	/**
	 * 查询全部假期   带分页
	 * @param page       当前页
	 * @param limit		   每页多少条数据
	 * @param name       是否使用搜索框
	 * @return  String   返回json类型      
	 * String
	 * @作者 林水桥2018年5月24日上午11:53:44
	 */
	@ResponseBody
    @RequestMapping(value = "/getHoliday", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String getTest(int page, int limit, String name) {
		String a = holidayService.getHoliday(page, limit, name);
		return a;
	}
	
	/**
     * 查询届次集合
     * @return    届次json集合
     * String
     * 2018年3月1日下午10:09:29
     */
	@ResponseBody
    @RequestMapping(value = "/clasList", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    public String adds() {
		List<Clas> clas = claService.query();
		String a = JsonUtils.beanListToJson(clas);
        return a;
    }
	
	/**
	 * 添加假期
	 * @param holiday 假期数据
	 * @return 返回添加状态
	 * void
	 * @作者 林水桥2018年5月24日上午11:28:23
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String add(Holiday holiday) {
		return holidayService.add(holiday);
	}
	
	/**
	 * 根据ID删除假期
	 * @param id   假期ID
	 * @return	          返回删除状态    0为未删除
	 * String
	 * @作者 林水桥2018年5月25日上午10:17:21
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.DELETE, produces = "text/json;charset=UTF-8")
	public String delete(Integer id) {
		return holidayService.delete(id);
	}
	
	/**
	 * 进入假期修改页面
	 * @param id   假期ID
	 * @return	          假期对应实体数据及届次数据
	 * String
	 * @作者 林水桥2018年5月25日上午10:38:17
	 */
//	@ResponseBody
	@RequestMapping(value = "updates", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String updates(Integer id) {
		Map<String, Object> map = new HashMap<>();
		Holiday holiday = holidayService.get(id);
		map.put("holiday", holiday);
		return addr1;
	}
	
	/**
	 * 根据假期ID修改假期数据
	 * @param holiday  假期修改数据
	 * @return		          假期修改状态  0为修改成功
	 * String
	 * @作者 林水桥2018年5月25日上午10:34:32
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.PUT, produces = "text/json;charset=UTF-8")
	public String update(Holiday holiday) {
		return holidayService.update(holiday);
	}
	
	/**
	 * 过滤json嵌套异常字段
	 * @param object  对象
	 * @param gl     忽略字段
	 * @return
	 * String
	 * 2018年4月9日下午10:06:38
	 */
	public static String sendArray(Object object, String[] gl) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(gl); // 只要将所需忽略字段加到数组中即可
		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonBeanProcessor(java.sql.Date.class, new DateJsonValueProcessor());
		JSONArray json = JSONArray.fromObject(object, jsonConfig);
		return json.toString();
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
    }
}
