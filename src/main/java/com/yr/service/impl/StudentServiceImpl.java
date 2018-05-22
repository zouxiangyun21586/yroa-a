package com.yr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.StudentDao;
import com.yr.entity.Student;
import com.yr.service.StudentService;

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
	 * @Date : 2018年5月22日下午5:09:23
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : 返回所有学生的集合
	 * 
	 *  @describe : 实现com.yr.service.StudentService接口,重写方法
	 * 
	 * @see com.yr.service.StudentService#queryStudent()
	 */
	public List<Student> queryStudent() {
		List<Student> student = studentDao.queryStudent();
		return student;
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
		String result = studentDao.addStudent(student);
		return result;
	}
	
	
}
