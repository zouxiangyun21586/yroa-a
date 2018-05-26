package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.StudentDao;
import com.yr.entity.Clas;
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
			name = new String(name.getBytes("ISO8859-1"), "utf-8");
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
						.setParameter("name", "%" + name + "%").getResultList();
				count = Integer
				.parseInt(entityManager
				 .createNativeQuery("select count(*) from yr_student where name like :name ")
				   .setParameter("name", "%" + name + "%").getSingleResult().toString());
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
		try {
			String name = student.getName(); //学生姓名
			String result1 = queyrIsName(name);
			if ("0".equals(result1)) {
				String code = code(); //学生编号
				String clasCode = new String(student.getYear()
						.getBytes("ISO8859-1"), "utf-8"); //届次 ,获取到的是届次表的code
				Clas clas = queryClas(clasCode); //根据届次code查出届次
				String year = clas.getYear(); //届次
				String classCode = clas.getCode(); //所属批次Code 
				Date createTime = new Date(); //添加这条信息的时间
				String isFinish = "0"; //是否毕业  1代表已毕业,0代表未毕业,添加时默认是未毕业
				student.setName(name);
				student.setCode(code);
				student.setYear(year);
				student.setClassCode(classCode);
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
	 * @describe : 根据id删除学生信息
	 * 
	 * @see com.yr.dao.StudentDao#deleteStudent(java.lang.Integer)
	 */
	public void deleteStudent(Integer id) {
		Student student = entityManager.find(Student.class, id);
		entityManager.remove(student);
	}
	
	
	/**
	 * 
	 * @Date : 2018年5月24日下午8:25:25
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id 
	 * 
	 * @describe : 根据id修改学生信息
	 * 
	 * @see com.yr.dao.StudentDao#updateStudent(java.lang.Integer)
	 */
	@Override
	public void updateStudent(Integer id, Student student) {
		Student student1 = entityManager.find(Student.class, id);
		student1.setName(student.getName());
		entityManager.merge(student);
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
	
	
	/**
	 * 
	 * @Date : 2018年5月25日上午8:20:56
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id
	 * 
	 * @describe : 修改数据回显
	 * 
	 * @see com.yr.dao.StudentDao#updateDisplay(java.lang.Integer)
	 */
	@Override
	public Student updateDisplay(Integer id) {
		Student student = entityManager.find(Student.class, id);
		return student;
	}

	/**
	 * 
	 * @Date : 2018年5月24日下午10:16:00
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : List<String> 
	 * 
	 * @describe : 查询出已有届次
	 *
	 */
	@Override
	public List<Clas> queryCls() {
		 List<Clas> yearList = entityManager.createQuery("from Clas").getResultList();
		return yearList;
	}
	
	/**
	 * 
	 * @Date : 2018年5月25日下午9:25:42
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : Clas
	 *
	 * @param code 
	 * 
	 * @describe 根据
	 */
	public Clas queryClas(String code) {
		String jpql = "from Clas where code=:code";
		Clas clas = (Clas) entityManager.createQuery(jpql).setParameter("code", code).getSingleResult();
		return clas;
	}

	@Override
	public String queryRoleCod() {
		//首先第一步：去yr_dic（字典表） 根据字段keyv  查出 字段 val：（学生）
		//第二步 ：根据查出的字段val（角色） 去yr_role表根据name查出 对应的code
		String jpql = "select val from Dic where keyv=:keyv";
		String val = (String) entityManager.createQuery(jpql).setParameter("keyv", "stu").getSingleResult();
		String jpql1 = "select code from Role where name=:name";
		String code = (String) entityManager.createQuery(jpql1).setParameter("name", val).getSingleResult();
		return code;
	}
}
