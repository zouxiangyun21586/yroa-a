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
import com.yr.util.HanyuPinyinHelper;
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
	
	
	
/**查询学生信息
 * 
 * @Date : 2018年5月22日下午7:19:40
 * 
 * @author : 唐子壕
 *
 * @describe   分页查询,模糊查询,查询结果根据入学时间进行升序查询	
 *
 * @param page 第几页
 * @param limit 每页多少条
 * @param name 搜索条件
 * @param modules 搜索条件
 * 
 * @return : PageUtil 返回查询结果,是一个集合
 *
 * @see com.yr.dao.StudentDao#queryStudent(java.lang.Integer, java.lang.Integer, java.lang.String)
 */
public PageUtil queryStudent(Integer page, Integer limit, String name, String modules) {
	PageUtil pageUtil = new PageUtil(); 
	try { 
		name = new String(name.getBytes("ISO8859-1"), "utf-8");
		int count = 0;
		String jpql = "From Student order by inTime desc";
		if (null  != name && !"".equals(name) && null != modules && !"".equals(modules)) {
			jpql = "from Student where name like :name and isFinish=:isFinish order by inTime desc";
		} else if (null  != name && !"".equals(name)) { 
			jpql = "from Student where name like :name order by inTime desc";
		} else if (null != modules && !"".equals(modules)) {
			jpql = "from Student where isFinish=:isFinish order by inTime desc";
		}
		List<Student> studentList = new ArrayList<Student>();
		name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
		if (null  != name && !"".equals(name) &&  null != modules && !"".equals(modules)) {
		   studentList = entityManager.createQuery(jpql).setMaxResults(limit).setFirstResult((page - 1) * limit)
		 .setParameter("name", "%" + name + "%").setParameter("isFinish", modules).getResultList();
	     count = Integer.parseInt(entityManager.createNativeQuery("select count(*) from yr_student where"
		+ "name like :name and is_finish=:isFinish").setParameter("name", "%" + name + "%")
			     .setParameter("isFinish", modules).getSingleResult().toString());
		} else if (null  != name && !"".equals(name)) {
			studentList = entityManager.createQuery(jpql).setMaxResults(limit)
			.setFirstResult((page - 1) * limit).setParameter("name", "%" + name + "%").getResultList();
			count = Integer.parseInt(entityManager.createNativeQuery("select count(*) from yr_student where"
			 + " name like :name").setParameter("name", "%" + name + "%").getSingleResult().toString());
		} else if (null != modules && !"".equals(modules)) {
		  studentList = entityManager.createQuery(jpql).setMaxResults(limit)
			.setFirstResult((page - 1) * limit).setParameter("isFinish", modules).getResultList();
			count = Integer.parseInt(entityManager.createNativeQuery("select count(*) from "
					+ "yr_student where is_finish=:isFinish")
					.setParameter("isFinish", modules).getSingleResult().toString());
		} else {
			studentList = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
					.setMaxResults(limit).getResultList();
			count = Integer.parseInt(entityManager.createNativeQuery("select "
					+ "count(*) from yr_student").getSingleResult().toString());
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
	
/**添加学生信息
 * 
 * @Date : 2018年5月22日下午5:44:32
 * 
 * @author : 唐子壕
 *	
 * @describe : 实现com.yr.dao.StudentDao接口,重写方法,进行操作数据库
 * 			   这里届次和所属批次code是根据届次code获取到的,添加到学生表里面
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
		String result1 = queyrIsName(student.getName()); //判断学生姓名是否已存在
		if ("0".equals(result1)) { //判断添加的学是是否也存在 这里表示不存在
			String code = code(); //学生编号
			String clasCode = new String(student.getYear()
					.getBytes("ISO8859-1"), "utf-8"); //届次 ,获取到的是届次表的code
			Clas clas = queryClas(clasCode); //根据届次code查出届次
			String year = clas.getYear(); //届次
			String classCode = clas.getCode(); //所属批次Code 
			Date createTime = new Date(); //添加这条信息的时间
			HanyuPinyinHelper hanyuPinyinHelper = new HanyuPinyinHelper();
	        String account = hanyuPinyinHelper.toHanyuPinyin(student.getName());
	        student.setAccount(account);
			student.setCode(code);
			student.setYear(year);
			student.setClassCode(classCode);
			student.setCreateTime(createTime);
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



/**删除学生信息
 * 
 * @Date : 2018年5月23日下午3:20:42
 * 
 * @author : 唐子壕
 *	
 * @param id 学生id
 * 
 * @describe : 根据id删除学生信息
 * 
 * @return String
 * 
 * @see com.yr.dao.StudentDao#deleteStudent(java.lang.Integer)
 */
public String deleteStudent(Integer id) {
	Student student = entityManager.find(Student.class, id);
	String account = student.getAccount();
	entityManager.remove(student);
	return account;
}

/**修改学生信息
 * 
 * @Date : 2018年5月24日下午8:25:25
 * 
 * @author : 唐子壕
 *	
 * @describe : 根据id修改学生信息
 * 
 * @see com.yr.dao.StudentDao#updateStudent(java.lang.Integer)
 */
@Override
public void updateStudent(Student student) {
	Student student1 = entityManager.find(Student.class, student.getId());
	student1.setAddr(student.getAddr());
	student1.setBirth(student.getBirth());
	student1.setSex(student.getSex());
	student1.setClassCode(student.getClassCode());
	Clas clas = queryClas(student.getClassCode());
	student1.setYear(clas.getYear());
	student1.setTel(student.getTel());
	student1.setIsFinish(student.getIsFinish());
	student1.setIsItDisplayed(student.getIsItDisplayed()); //是否展示该学生
	student1.setHomeTel(student.getHomeTel());
	student1.setInTime(student.getInTime());
	entityManager.merge(student1);
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
		code = "1001";
	} else {
		String sql = "select max(`code`)  from yr_student";
		String sqlCode = entityManager.createNativeQuery(sql).getSingleResult().toString();
		Integer integer = Integer.valueOf(sqlCode) + 1;
		code = integer.toString();
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
/**
 * 
 * @Date : 2018年5月26日上午10:43:36
 * 
 * @author : 唐子壕
 *	
 * @describe  首先第一步：去yr_dic（字典表） 根据字段keyv  查出 字段 val：（学生）
 *            第二步 ：根据查出的字段val（角色） 去yr_role表根据name查出 对应的code
 * 
 * @see com.yr.dao.StudentDao#queryRoleCod()
 */
@Override
public String queryRoleCod() {
	String jpql = "select val from Dic where keyv=:keyv";
	String val = (String) entityManager.createQuery(jpql).setParameter("keyv", "stu").getSingleResult();
	String jpql1 = "select code from Role where name=:name";
	String code = (String) entityManager.createQuery(jpql1).setParameter("name", val).getSingleResult();
	return code;
}

/**
 * 
 * @Date : 2018年5月26日上午11:49:09
 * 
 * @author : 唐子壕
 *	
 * @return List<Student>
 * 
 * @describe 查询所有未毕业学生
 * 
 * @see com.yr.dao.StudentDao#queryNoGre()
 */
public List<Student> queryNoGre() {
	String jpql = "from Student where isFinish =:isFinish";
	List<Student> student = entityManager.createQuery(jpql).setParameter("isFinish", "0").getResultList();
	return student;
}

/**
 * 
 * @Date : 2018年5月26日上午11:49:13
 * 
 * @author : 唐子壕
 *	
 * @describe 根据code查询学生对象
 *  
 * @param code 
 *	
 * @return Student
 * 
 * @see com.yr.dao.StudentDao#querytoCode()
 */
public Student querytoCode(String code) {
	String jpql = "from Student where code=:code";
	Student student = (Student) entityManager.createQuery(jpql)
			.setParameter("code", code).getSingleResult();
	return student;
}

/**
 * 
 * @Date : 2018年5月28日上午8:21:43
 * 
 * @author : 唐子壕
 *	
 * @param student 
 * 
 * @see com.yr.dao.StudentDao#employmentEditors(com.yr.entity.Student)
 */
public void employmentEditors(Student student) {
	Student stu = entityManager.find(Student.class, student.getId());
	stu.setFinishTime(student.getFinishTime()); //修改毕业时间
	stu.setOfferTime(student.getOfferTime()); //修改就业时间
	stu.setOfferIncome(student.getOfferIncome());
	entityManager.merge(stu);
}
}
