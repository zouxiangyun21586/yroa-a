package com.yr.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.StudentDao;
import com.yr.entity.Student;



/**
 * 
 * @Date:2018年5月22日下午3:38:21	
 *
 * @author: 唐子壕
 * 
 * 学生管理dao层实现类
 */
@Repository
public class StudentDaoImpl implements StudentDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 
	 * @Date : 2018年5月22日下午5:18:03
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : List<Student>
	 * 
	 * @describe : 实现com.yr.dao.StudentDao接口,重写方法,进行操作数据库
	 * 
	 * @see com.yr.dao.StudentDao#queryStudent()
	 */
	public List<Student> queryStudent() {
		String sql = "select * from Student";
		List<Student> studentList = entityManager.createQuery(sql).getResultList();
		for (Student student : studentList) {
			student.toString();
		}
		return studentList;
	}
	
	/**
	 * 
	 * @Date : 2018年5月22日下午5:44:32
	 * 
	 * @author : 唐子壕
	 *	
	 * @describe : 实现com.yr.dao.StudentDao接口,重写方法,进行操作数据库
	 *
	 * @param student 方法重写，带参数
	 * 
	 * @return : String 返回一个提示
	 * 
	 * @see com.yr.dao.StudentDao#addStudent(com.yr.entity.Student)
	 */
	public String addStudent(Student student) {
		
		return null;
	}
	
	

}
