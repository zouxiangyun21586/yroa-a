package com.yr.service;

import com.yr.entity.Student;

/**
 * 
 * @Date:2018年5月22日下午3:34:41	
 *
 * @author: 唐子壕
 * 
 * 学生管理service层接口类
 */
public interface StudentService {
	/**
	 * 
	 * @Date : 2018年5月22日下午7:13:48
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : PageUtil
	 *
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 搜索条件
	 */
	 String queryStudent(Integer page, Integer limit, String name);
	 
	 /**
	  * 
	  * @Date : 2018年5月22日下午5:36:46
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : String 返回一个提示
	  *
	  * @describe : 添加学生
	  *
	  * @param student 接收StudentControlle传过来的值
	  */
	 String addStudent(Student student);
	 
	 /**
	  * 
	  * @Date : 2018年5月23日下午3:08:02
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : String
	  *
	  * @param id 学生id
	  */
	 String deleteStudent(Integer id);
}
