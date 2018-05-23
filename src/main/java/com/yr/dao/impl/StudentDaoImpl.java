package com.yr.dao.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.StudentDao;
import com.yr.entity.Student;
import com.yr.util.PageUtil;



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
	 * @Date : 2018年5月22日下午7:19:40
	 * 
	 * @author : 唐子壕
	 *	
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 搜索条件
	 * @return : PageUtil 返回查询结果,是一个集合
	 *
	 * @see com.yr.dao.StudentDao#queryStudent(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public PageUtil queryStudent(Integer page, Integer limit, String name) {
		PageUtil pageUtil = new PageUtil(); 
		try {
			int count = 0;
			String jpql = "From Student where code !='root' order by in_time desc";
			if (null  != name && !"".equals(name)) {
				jpql = "from Student where name like :name and code !='root'order by in_time desc";
			}
			List<Student> studentList = new ArrayList<Student>();
			name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
			if (null  != name && !"".equals(name)) {
				studentList = entityManager.createQuery(jpql)
						.setMaxResults(limit).setFirstResult((page - 1) * limit)
						.setParameter("name", "" + name + "%").getResultList();
				count = Integer
				.parseInt(entityManager
				 .createNativeQuery("select count(*) from Student name like :name and code !='root'")
				   .setParameter("name", "" + name + "%").getSingleResult().toString());
			} else {
				studentList = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
						.setMaxResults(limit).getResultList();
				count = Integer
						.parseInt(entityManager
						   .createNativeQuery("select count(*) from Student code !='root'")
							  .getSingleResult().toString());
			}
			pageUtil = new PageUtil(limit, page, count);		
			pageUtil.setCount(count);
			pageUtil.setCode(0);
			pageUtil.setData(studentList);
			pageUtil.setMsg("OK");
		} catch (Exception e) {
			pageUtil.setCode(1);
			pageUtil.setMsg("---出错了!----");
			e.printStackTrace();
		}
		
		return pageUtil;
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
		String name = ""; //学生姓名
		Date birth = null; //出生年月
		String sex = ""; //性别
		String year = ""; //届次
		String tel = ""; //学生电话
		String addr = ""; //家庭地址
		String homeTel = ""; //家长电话
		Date inTime = null; //入学时间
		try {
			name = new String(student.getName().getBytes("ISO8859-1"), "utf-8");
			birth = student.getBirth();
			sex = new String(student.getSex().getBytes("ISO8859-1"), "utf-8");
			if ("男".equals(sex)) {
				sex = "1";
			} else if ("女".equals(sex)) {
				sex = "0";
			}
			year = new String(student.getYear().getBytes("ISO8859-1"), "utf-8");
			tel = new String(student.getTel().getBytes("ISO8859-1"), "utf-8");
			addr = new String(student.getAddr().getBytes("ISO8859-1"), "utf-8");
			homeTel = new String(student.getHomeTel().getBytes("ISO8859-1"), "utf-8");
			inTime = student.getInTime();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		student.setName(name);
		student.setBirth(birth);
		student.setSex(sex);
		student.setYear(year);
		student.setTel(tel);
		student.setAddr(addr);
		student.setHomeTel(homeTel);
		student.setInTime(inTime);
		entityManager.persist(student);
		return name;
	}
}
