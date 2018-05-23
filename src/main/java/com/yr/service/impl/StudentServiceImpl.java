package com.yr.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.StudentDao;
import com.yr.entity.Student;
import com.yr.service.StudentService;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @Date:2018年5月22日下午3:36:12	
 *
 * @author: 唐子壕
 * 
 * 学生管理service层实现类
 */
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
	/**
	 * 
	 * @Date : 2018年5月22日下午7:15:23
	 * 
	 * @author : 唐子壕
	 *	
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 搜索条件
	 *
	 * @return : PageUtil 返回查询的结果,是一个集合
	 * 
	 * @see com.yr.service.StudentService#queryStudent(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public String queryStudent(Integer page, Integer limit, String name) {
		PageUtil pageUtil = studentDao.queryStudent(page, limit, name);
		String result = JsonUtils.beanToJson(pageUtil);
		return result;
	}
	
	/**
	 * 
	 * @Date : 2018年5月22日下午5:40:11
	 * 
	 * @author : 唐子壕
	 * 
	 * @describe : 实现com.yr.service.StudentService接口,重写方法
	 *	
	 * @param student 方法重写，带参数
	 *
	 * @return : String 返回一个提示
	 *  
	 * @see com.yr.service.StudentService#addStudent(com.yr.entity.Student)
	 */
	public String addStudent(Student student) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			studentDao.addStudent(student);
			map.put("code", 0);
			map.put("msg", "添加成功");
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "添加失败");
			map.put("error", e);
		}
		String result = JSONObject.fromObject(map).toString();
		return result;
	}

	/**
	 * 
	 * @Date : 2018年5月23日下午3:08:34
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id 学生id
	 * 
	 * @return : String
	 * 
	 * @see com.yr.service.StudentService#deleteStudent(java.lang.Integer)
	 */
	public String deleteStudent(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			studentDao.deleteStudent(id);
			map.put("code", 0);
			map.put("msg", "删除成功");
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "删除失败");
			map.put("error", e);
		}
		String result = JSONObject.fromObject(map).toString();
		return result;
	}
	
	
}
