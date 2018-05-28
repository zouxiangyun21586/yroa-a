package com.yr.dao;

import java.util.List;

import com.yr.entity.Clas;
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
	
	/**学生查询
	 * 
	 * @Date : 2018年5月22日下午7:18:23
	 * 
	 * @author : 唐子壕
	 *	
	 * @describe 分页查询
	 *
	 * @return : PageUtil 返回查询的结果,是一个集合
	 *
	 * @param page 第几页
	 * 
	 * @param limit 每页多少条
	 * 
	 * 
	 * @param name 搜索条件
	 */
	PageUtil queryStudent(Integer page, Integer limit, String name);
	 
	 /**添加学生信息
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
	 
	 /**删除学生信息
	  * 
	  * @Date : 2018年5月23日下午3:08:02
	  * 
	  * @author : 唐子壕
	  *	
	  * @param id 学生id
	  * 
	  * @return String
	  */
	 String deleteStudent(Integer id);
	 
	 /**修改数据回显
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
	  * @param student 
	  */
	 void updateStudent(Student student);
	 
	 /**
	  * 
	  * @Date : 2018年5月24日下午10:28:22
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : List<String> 
	  *
	  */
	 List<Clas> queryCls();
	 
	 
	 /**查询角色code
	  * 
	  * @Date : 2018年5月25日下午9:51:03
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : String 
	  *
	  * @describe 从角色表中查询出学生的code
	  */
	 String queryRoleCod();
	 
	 
	 
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
	  * @param code 
	  *	
	  * @return : Student
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
	  * @param student 
	  * 
	  * @describe 修改已就业学生信息
	  */
	 void employmentEditors(Student student);
	 
}
