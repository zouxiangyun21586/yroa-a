package com.yr.dao.impl;

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
			String jpql = "From Student order by inTime desc";
			if (null  != name && !"".equals(name)) {
				jpql = "from Student where name like :name order by inTime desc";
			}
			List<Student> studentList = new ArrayList<Student>();
			name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
			if (null  != name && !"".equals(name)) {
				studentList = entityManager.createQuery(jpql)
						.setMaxResults(limit).setFirstResult((page - 1) * limit)
						.setParameter("name", "" + name + "%").getResultList();
				count = Integer
				.parseInt(entityManager
				 .createNativeQuery("select count(*) from yr_student name like :name ")
				   .setParameter("name", "" + name + "%").getSingleResult().toString());
			} else {
				studentList = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
						.setMaxResults(limit).getResultList();
				count = Integer
						.parseInt(entityManager
						   .createNativeQuery("select count(*) from yr_student")
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
	 * @return String 
	 * 
	 * @see com.yr.dao.StudentDao#addStudent(com.yr.entity.Student)
	 */
	public String addStudent(Student student) {
		String result = "";
		String name = ""; //学生姓名
		String code = ""; //学生编号
		String birth = null; //出生年月
		String sex = ""; //性别  0代表女,1代表男
		String year = ""; //届次
		String tel = ""; //学生电话
		String addr = ""; //家庭地址
		String homeTel = ""; //家长电话
		String inTime = null; //入学时间
		Date createTime = null; //创建时间
		String isFinish = ""; //是否毕业  1代表已毕业,0代表未毕业,添加时默认是未毕业
		try {
			name = new String(student.getName().getBytes("ISO8859-1"), "utf-8");
			String result1 = queyrIsName(name);
			if ("0".equals(result1)) {
				code = code();
				birth = student.getBirth();
				sex = new String(student.getSex().getBytes("ISO8859-1"), "utf-8");
				year = new String(student.getYear().getBytes("ISO8859-1"), "utf-8");
				tel = new String(student.getTel().getBytes("ISO8859-1"), "utf-8");
				addr = new String(student.getAddr().getBytes("ISO8859-1"), "utf-8");
				homeTel = new String(student.getHomeTel().getBytes("ISO8859-1"), "utf-8");
				inTime = student.getInTime();
				createTime = new Date();
				isFinish = "0";
				student.setName(name);
				student.setBirth(birth);
				student.setCode(code);
				student.setSex(sex);
				student.setYear(year);
				student.setTel(tel);
				student.setAddr(addr);
				student.setHomeTel(homeTel);
				student.setInTime(inTime);
				student.setCreateTime(createTime);
				student.setIsFinish(isFinish);
				entityManager.persist(student);
				result = "addSuccess";
			} else {
				result = "alreadyExisted";
			}
		} catch (Exception e) {
			result = "addFail";
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Date : 2018年5月23日下午7:12:24
	 * 
	 * @author : 唐子壕
	 *	
	 * @param name 学生姓名
	 * 
	 * @return String 
	 * 
	 * @describe 查询要插入的学生是否已存在
	 */
	private String queyrIsName(String name) {
		String jpql = "select count(*) from yr_student where name =:name";
		String result = entityManager.createNativeQuery(jpql)
				.setParameter("name", name).getSingleResult().toString();
		return result;
	}

	/**
	 * 
	 * @Date : 2018年5月23日下午3:20:42
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id 学生id
	 * 
	 * @see com.yr.dao.StudentDao#deleteStudent(java.lang.Integer)
	 */
	public void deleteStudent(Integer id) {
		Student student = entityManager.find(Student.class, id);
		entityManager.remove(student);
	}
	
	/**
	 * 
	 * @Date : 2018年5月23日下午8:36:22
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String
	 *
	 * @describe 获取添加学生的编号 	
	 */
	public String code() {
		String code = "";
		String jpql = "select count(*) from yr_student";
		String value = entityManager.createNativeQuery(jpql).getSingleResult().toString();
		if ("0".equals(value)) {
			code = "S1001";
		} else {
			String sql = "select max(`code`)  from yr_student";
			String sqlCode = entityManager.createNativeQuery(sql).getSingleResult().toString();
			Integer integer = Integer.valueOf(sqlCode.substring(1)) + 1;
			code = "S" + integer;
		}
		return code;
	}
	
}
