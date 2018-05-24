package com.yr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Clas;
import com.yr.service.ClasService;
import com.yr.util.JsonUtils;

/**
 * 届次 controller
 * @author zxy
 *
 * 2018年5月22日 下午4:44:24
 *
 */
@Controller
public class ClasController {
	
	@Autowired
	private ClasService clasService;
	final Integer number = 2;
	
	/**
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:58:24
	 * 
	 * @param clas 老师对象
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return 返回到哪个界面
	 */
	@RequestMapping(value = "/clas", method = RequestMethod.POST)
	public String add(Clas clas, ModelMap map) {
		Boolean boo = clasService.add(clas);
		if (boo) {
			map.put("succ", number);
			return "show";
		} else {
			map.put("error", 1);
			return "show";
		}
	}

	/**
	 * 删除
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:58:15
	 * 
	 * @param clas 老师对象
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return 执行完这个方法后去到哪个页面
	 */
	@RequestMapping(value = "/clas/{id}", method = RequestMethod.DELETE)
	public String del(Clas clas, ModelMap map) {
//		Boolean bool = clasService.delete(clas);
//		if (bool) {
//			map.put("succ", number);
//			return "show";
//		} else {
//			map.put("error", 1);
//			return "show";
//		}
		return "";
	}

	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:58:07
	 * 
	 * @param clas 老师对象
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return 执行完这个方法后去到哪个页面
	 */
	@RequestMapping(value = "/clas", method = RequestMethod.PUT)
	public String upd(Clas clas, ModelMap map) {
		Boolean bool = clasService.update(clas);
		if (bool) {
			map.put("succ", number);
			return "show";
		} else {
			map.put("error", 1);
			return "show";
		}
	}

	/**
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:58:00
	 * 
	 * @param code 需数据回显的老师code
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return Json格式的String数据
	 */
	@RequestMapping(value = "/getClas", method = RequestMethod.GET)
	public @ResponseBody String get(String code, ModelMap map) {
		Clas listUser = clasService.get(code);
		String str = JsonUtils.beanToJson(listUser);
		return str;
	}

	/**
	 * 查询
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:57:52
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param year 分页条件
	 * @return Json格式的String数据
	 */
	@RequestMapping(value = "/clas", produces = "text/json;charset=UTF-8")
	public @ResponseBody String sel(Integer page, Integer limit, String year) {
		String str = clasService.query(page, limit, year);
		return str;
		
	}
	
	/**
	 * 毕业或者开课
	 * @author zxy
	 * 
	 * 2018年5月23日 上午9:52:53
	 * 
	 * @param code 届次
	 * @param map 传送值
	 * @return JsonString
	 */
	@RequestMapping(value = "/graduation", method = RequestMethod.GET)
	public @ResponseBody String graduation(String code, ModelMap map) {
		Integer gd = clasService.graduation(code);
		Integer oc = clasService.openClss(code);
		if (gd == 1) {
			return "已毕业";
		} else if (oc == 1) {
			return "已开课";
		}
		return "灰色按钮";
	}
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月24日 上午11:03:30
	 * 
	 * @param response 发送
	 * @param request 接收
	 * @param year 届次
	 * @return JsonString
	 */
	@RequestMapping(value = "/clas/year", method = RequestMethod.GET)
	public @ResponseBody String query(HttpServletResponse response, HttpServletRequest request,
			@PathVariable(value = "year") String year) {
		List<Clas> listUser = clasService.getOnly(year);
		String str = "";
		try {
			// false表示数组中的属性不需要转成json,如果是true代表只将数组中的属性转成json格式
			str = JsonUtils.beanListToJson(listUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
		
	}
}
