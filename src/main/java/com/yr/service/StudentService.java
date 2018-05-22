package com.yr.service;

import java.util.List;

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
	 * @Date : 2018年5月22日下午4:51:12
	 * 
	 * @author : 唐子壕
	 * 
	 * @describe : 查询所有学生
	 *	
	 * @return : List<Student> 返回类型
	 */
	 List<Student> queryStudent();
	 
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
}
