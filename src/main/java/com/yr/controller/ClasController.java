package com.yr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Clas;
import com.yr.entity.Student;
import com.yr.service.ClasService;
import com.yr.service.StudentService;
import com.yr.service.TeacherService;
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
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	final Integer number = 2;
	
	String jsp = "curriculum/curriculumUpd";
	
	String strStu = "curriculum/studentAdd";
	
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
		String str = clasService.add(clas);
		return str;
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
		return "";
	}

	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:58:07
	 * 
	 * @param clas 老师对象
	 * @return 执行完这个方法后去到哪个页面
	 */
	@ResponseBody
	@RequestMapping(value = "/clas", method = RequestMethod.PUT)
	public String upd(@ModelAttribute("clas")Clas clas) {
		String str = clasService.update(clas);
		return str;
	}

	/**
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:58:00
	 * 
	 * @param id 需数据回显的老师id
	 * @param map 传递控制方法或者传递数据到结果页面
	 * @return Json格式的String数据
	 */
	@RequestMapping(value = "/getClasOnly", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String get(ModelMap map, Integer id) {
		Clas clas = clasService.get(id);
		map.put("clas", clas);
		return jsp;
	}

	/**
	 * 查询所有数据回显使用
	 * @return String   老师数据,json格式
	 * 2018年5月26日上午11:51:01
	 */
	@ResponseBody
	@RequestMapping(value = "/clasHX", produces = "text/json;charset=UTF-8")
	public String huixian() {
		List<Clas> listTeacher = clasService.query();
		String str = JsonUtils.beanListToJson(listTeacher);
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
	 * 查询所有
	 * @author zxy
	 * 
	 * 2018年5月24日 下午5:36:28
	 * 
	 * @return JsonString数据
	 */
	@RequestMapping(value = "/clasQuery", produces = "text/json;charset=UTF-8")
	public @ResponseBody String query() {
		List<Clas> listClas = clasService.query();
		String str = JsonUtils.beanToJson(listClas);
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
	 * 查询某个值
	 * @author zxy
	 * 
	 * 2018年5月24日 上午11:03:30
	 * 
	 * @param map 发送
	 * @param code 届次
	 * @return JsonString
	 */
	@RequestMapping(value = "/clasGet", method = RequestMethod.GET)
	public String query(ModelMap map, String code) {
		String str = clasService.getOnly(code);
		map.put("clasStu", str);
		return strStu;
	}
	
	/**
	 * 查询所有 (用于届次添加下拉框显示)
	 * @author zxy
	 * 
	 * 2018年5月27日 上午10:44:03
	 * 
	 * @return 是否成功
	 */
	@RequestMapping(value = "/clasTeacher", produces = "text/json;charset=UTF-8")
	public @ResponseBody String queryTc() {
		String str = teacherService.queryTeacher();
		return str;
	}
	
	/**
	 * 添加学生
	 * @author zxy
	 * 
	 * 2018年5月28日 上午10:49:04
	 * 
	 * @param student 学生信息
	 * @param code 本届次code
	 * @return String Josn
	 */
	@ResponseBody
	@RequestMapping(value = "/student", method = {RequestMethod.POST}, produces = "text/json;charset=UTF-8")
	public String addStudent(Student student, String code) {
		String result = studentService.addStudent(student);
		return result;
	}
}
