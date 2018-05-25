package com.yr.dao;

import java.util.List;

import com.yr.entity.Student;
import com.yr.util.PageUtil;

/**
 * 
 * @Date:2018年5月22日下午3:37:34	
 *
 * @author: 唐子壕
 * 
 * 学生管理dao层接口类
 */
public interface StudentDao {
	/**
	 * 
	 * @Date : 2018年5月22日下午7:18:23
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : PageUtil 返回查询的结果,是一个集合
	 *
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 搜索条件
	 */
	PageUtil queryStudent(Integer page, Integer limit, String name);
	 
	 /**
	  * 
	  * @Date : 2018年5月22日下午5:43:10
	  * 
	  * @author : 唐子壕
	  *	
	  * @describe : 添加学生
	  * 
	  * @return String  
	  *	
	  * @param student 接收StudentService传过来的值
	  */
	 String addStudent(Student student);
	 
	 /**
	  * 
	  * @Date : 2018年5月23日下午3:08:02
	  * 
	  * @author : 唐子壕
	  *	
	  * @param id 学生id
	  */
	 void deleteStudent(Integer id);
	 
	 /**
	  * 
	  * @Date : 2018年5月24日下午7:23:50
	  * 
	  * @author : 唐子壕 
	  *	
	  * @return : String 
	  * 
	  * @param id 
	  */
	 Student updateDisplay(Integer id);
	 
	 
	 /**
	  * 
	  * @Date : 2018年5月24日下午8:24:39
	  * 
	  * @author : 唐子壕
	  *	
	  * @param id 
	  * 
	  * @param student 
	  */
	 void updateStudent(Integer id, Student student);
	 
	 /**
	  * 
	  * @Date : 2018年5月24日下午10:28:22
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : List<String> 
	  *
	  */
	 List<Student> queryCls();
}
