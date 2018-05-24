package com.yr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.StudentDao;
import com.yr.entity.Account;
import com.yr.entity.Student;
import com.yr.service.AccountService;
import com.yr.service.StudentService;
import com.yr.util.HanyuPinyinHelper;
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
	
	@Autowired
	private AccountService accountService;
	
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
	@Transactional
	public String addStudent(Student student) {
		final Integer number = 2;
		Map<String, Object> map = new HashMap<String, Object>();
		String resutl1 = studentDao.addStudent(student);
		if ("addSuccess".equals(resutl1)) {
			Account account = addAccount(student);
//			accountService.addId(account, student.getCode());
			map.put("code", 0);
			map.put("msg", "添加成功");
		} else if ("addFail".equals(resutl1)) {
			map.put("code", 1);
			map.put("msg", "添加失败");
		} else if ("alreadyExisted".equals(resutl1)) {
			map.put("code", number);
			map.put("msg", "该学生已经添加过了");
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
	@Transactional
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
	/**
	 * 
	 * @Date : 2018年5月23日下午8:47:35
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : Account
	 *
	 * @param student 学生实体
	 * 
	 * @describe 添加学生时添加一个默认的账号
	 * 
	 */
	public Account addAccount(Student student) {
		Account account = new Account();
		HanyuPinyinHelper hanyuPinyinHelper = new HanyuPinyinHelper();
        String username = hanyuPinyinHelper.toHanyuPinyin(student.getName());
        String password = "25d55ad283aa400af464c76d713c07ad";
		String isAdmin = "false";
		String type = "S";
		String status = "0";
		Date createTime = new Date();
		Date updateTime = new Date();
		account.setUsername(username);
		account.setIsAdmin(isAdmin);
		account.setPassword(password);
		account.setType(type);
		account.setStatus(status);
		account.setCreateTime(createTime);
		account.setUpdateTime(updateTime);
		return account;
	}
	
	/**
	 * 
	 * @Date : 2018年5月24日下午8:22:51
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id
	 * 
	 * @see com.yr.service.StudentService#updateDisplay(java.lang.Integer)
	 */
	@Override
	public String updateDisplay(Integer id) {
		Student student = studentDao.updateDisplay(id);
		String result = JsonUtils.beanToJson(student);
		return result;
	}
	
	/**
	 * 
	 * @Date : 2018年5月24日下午8:23:07
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id 
	 * 
	 * @see com.yr.service.StudentService#updateStudent(java.lang.Integer)
	 */
	@Override
	public String updateStudent(Integer id, Student student) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			studentDao.updateStudent(id, student);
			map.put("code", 0);
			map.put("msg", "修改成功");
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "修改失败");
			map.put("error", e);
		}
		String result = JSONObject.fromObject(map).toString();
		return result;
	}
}
