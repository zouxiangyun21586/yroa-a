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
	 * @Date : 2018年5月22日下午7:13:48
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : PageUtil
	 *
	 * @describe 分页查询
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
	  * @describe 根据学生id删除学生
	  *
	  * @param id 学生id
	  */
	 String deleteStudent(Integer id);
	 
	 /**
	  * 
	  * @Date : 2018年5月24日下午7:19:55
	  * 
	  * @author : 唐子壕 
	  *	
	  * @return : String 
	  *
	  * @param id 根据这个id查出一个对象，用来数据回显
	  * 
	  * @describe 修改数据回显
	  */
	 Student updateDisplay(Integer id);
	 
	 /**
	  * 
	  * @Date : 2018年5月24日下午8:32:49
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : String 
	  *
	  * @param student 
	  * 
	  * @describe 执行修改学生信息
	  */
	 String updateStudent(Student student);
	 
	 /**
	  * 
	  * @Date : 2018年5月25日上午9:06:32
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : String
	  * 
	  * @describe 查询届次用于回显到页面进行选择届次
	  *
	  */
	 String queryCls();
	 
	 /**
	  * 
	  * @Date : 2018年5月26日上午11:43:19
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : List<Student>
	  *
	  * @describe 供学生考勤模块调用
	  */
	 List<Student> queryNoGre();
	 
	 /**
	  * 
	  * @Date : 2018年5月26日上午11:46:32
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : Student
	  * 
	  * @param code 
	  * 
	  * @describe 供学生考勤模块调用
	  *
	  */
	 Student querytoCode(String code);
	 
	 /**
	  * 
	  * @Date : 2018年5月28日上午8:18:17
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : String 
	  *
	  * @param student 
	  * 
	  * @describe 修改已就业学生信息
	  */
	 String employmentEditors(Student student);
	 
	 
	 
}

