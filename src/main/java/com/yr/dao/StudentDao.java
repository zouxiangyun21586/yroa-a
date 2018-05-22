package com.yr.dao;

import java.util.List;

import com.yr.entity.Student;

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
	 * @Date : 2018年5月22日下午5:16:00
	 * 
	 * @author : 唐子壕
	 * 
	 * @describe : 查询所有学生
	 *	
	 * @return : List<Student> 
	 *
	 */
	 List<Student> queryStudent();
	 
	 /**
	  * 
	  * @Date : 2018年5月22日下午5:43:10
	  * 
	  * @author : 唐子壕
	  *	
	  * @describe : 添加学生
	  *	
	  * @return : String 返回一个提示
	  *
	  * @param student 接收StudentService传过来的值
	  */
	 String addStudent(Student student);
}
