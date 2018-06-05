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
	 * @param page 第几页
	 * 
	 * @param limit 每页多少条
	 * 
	 * @param modules 搜索条件
	 * 
	 * @param name 搜索条件
	 * 
	 * @return : PageUtil 返回查询的结果,是一个集合
	 * 
	 * @describe 分页查询
	 */
	PageUtil queryStudent(Integer page, Integer limit, String name, String modules);
	 
	 /**添加学生信息
	  * 
	  * @Date : 2018年5月22日下午5:43:10
	  * 
	  * @author : 唐子壕
	  *	
	  * @param student 接收StudentService传过来的值
	  * 
	  * @return String  
	  * 
	  * @describe : 添加学生
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
	  * @param id 
	  * 
	  * @return : String 
	  */
	 Student updateDisplay(Integer id);
	 
	 
	 /**
	  * 
	  * @Date : 2018年5月24日下午8:24:39
	  * 
	  * @author : 唐子壕
	  *	
	  * @param student 
	  * 
	  * @return String  
	  */
	 String updateStudent(Student student);
	 
	 /**
	  * 
	  * @Date : 2018年5月24日下午10:28:22
	  * 
	  * @author : 唐子壕
	  *	
	  * @return : List<String> 
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
	  * @Date : 2018年5月31日下午3:17:57
	  * 
	  * @author : 唐子壕
	  *	
	  * @param id 
	  * 
	  * @return : String
	  * 
	  * @describe 查询就业学生信息
	  */
	 Student employment(Integer id);
	 
	 /**
	 * 根据账号获取学生数据
	 * @author 林水桥
	 * @param value 学生账号或学生电话
	 * @return Student 返回学生数据
	 * 2018年5月31日下午10:15:29
	 */
	Student getAccount(String value);
	
	/**
	 * 根据学生姓名查询学生数量
	 * @author 林水桥
	 * @param name   学生姓名
	 * @return Integer  学生数量
	 * 2018年6月4日下午8:54:33
	 */
	Integer queryCountName(String name);
	
	/**
	 * 家长添加，修改学生家长code
	 * @author 林水桥
	 * @param name          学生姓名
	 * @param parentsCode   家长code
	 * @param tel           家长电话
	 * @return Integer      0为修改失败
	 * 2018年6月4日下午10:11:01
	 */
	Integer updateParents(String name, String parentsCode, String tel);
	
	/**
	 * 家长修改电话
	 * @author  林水桥
	 * @param code   家长code
	 * @param tel    家长电话
	 * @return Integer 0为修改失败
	 * 2018年6月5日下午5:13:27
	 */
	Integer updatePareTel(String code, String tel);
}
