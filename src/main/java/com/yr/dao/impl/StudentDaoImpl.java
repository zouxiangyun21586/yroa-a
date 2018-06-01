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
import com.yr.util.CheckParamUtil;
import com.yr.util.CheckTelephoneUtil;
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
		PageUtil pageUtil = new PageUtil(); //分页实体
		try { 
			//modules代表页面传来的是已毕业,未毕业
			name = new String(name.getBytes("ISO8859-1"), "utf-8"); //页面传来要搜索的值
			int count = 0;
			String jpql = "From Student order by inTime desc"; // 查出所有学生根据入学时间进行升序
			if (null  != name && !"".equals(name) && null != modules && !"".equals(modules)) { //如果name不等于null与空和modules不等于null和空，必须都满足条件
				//模糊查询学生表里name字段等于页面传来的name 和 isFinish等于页面传来的是否已毕业 的数据
				jpql = "from Student where name like :name and isFinish=:isFinish order by inTime desc"; 
			} else if (null  != name && !"".equals(name)) { //如果name不等于null和空,必须都满足条件
				//模糊查询学生表里name字段等于页面传来的name的数据
				jpql = "from Student where name like :name order by inTime desc";
			} else if (null != modules && !"".equals(modules)) { //如果modules不等于null和空,必须都满足条件
				//模糊查询学生表里isFinish等于页面传来的是否已毕业的数据
				jpql = "from Student where isFinish=:isFinish order by inTime desc";
			}
			List<Student> studentList = new ArrayList<Student>(); //用来存放查询出的值
			name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name); //防止sql注入
			if (null  != name && !"".equals(name) &&  null != modules && !"".equals(modules)) { ////如果name不等于null与空和modules不等于null和空，必须都满足条件
			   studentList = entityManager.createQuery(jpql).setMaxResults(limit).setFirstResult((page - 1) * limit)
					   		.setParameter("name", "%" + name + "%").setParameter("isFinish", modules).getResultList(); //进行分页查询
			   	//查询学生表里name字段等于页面传来的name 和 isFinish等于页面传来的是否已毕业 的总数
			   count = Integer.parseInt(entityManager.createNativeQuery("select count(*) from yr_student where name like :name and is_finish=:isFinish")
		    		 				.setParameter("name", "%" + name + "%").setParameter("isFinish", modules).getSingleResult().toString());
			} else if (null  != name && !"".equals(name)) {
				//进行分页查询
				studentList = entityManager.createQuery(jpql).setMaxResults(limit).setFirstResult((page - 1) * limit).setParameter("name", "%" + name + "%").getResultList();
				//查询学生表里name字段等于页面传来的name的总数
				count = Integer.parseInt(entityManager.createNativeQuery("select count(*) from yr_student where"
				 + " name like :name").setParameter("name", "%" + name + "%").getSingleResult().toString());
			} else if (null != modules && !"".equals(modules)) {
				//进行分页查询
			    studentList = entityManager.createQuery(jpql).setMaxResults(limit).setFirstResult((page - 1) * limit).setParameter("isFinish", modules).getResultList();
				//查询学生表里isFinish等于页面传来的是否毕业的总数
			    count = Integer.parseInt(entityManager.createNativeQuery("select count(*) from yr_student where is_finish=:isFinish").setParameter("isFinish", modules)
										.getSingleResult().toString());
			} else {
				//进行分页查询
				studentList = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit).setMaxResults(limit).getResultList();
				//查询所有学生的总数
				count = Integer.parseInt(entityManager.createNativeQuery("select count(*) from yr_student").getSingleResult().toString());
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
		return pageUtil; //返回数据
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
					Boolean bool = CheckTelephoneUtil.isMobile(student.getTel());
					if (bool.equals(false)) {
						return "telformattingError";
					}
					Boolean boo = CheckTelephoneUtil.isMobile(student.getHomeTel());
					if (boo.equals(false)) {
						return "homeTelformattingError";
					}
					String code = code(); //学生编号
					String clasCode = new String(student.getYear()
							.getBytes("ISO8859-1"), "utf-8"); //届次 ,获取到的是届次表的code
					Clas clas = queryClas(clasCode); //根据届次code查出届次
					String year = clas.getYear(); //届次
					String classCode = clas.getCode(); //所属批次Code 
					Date createTime = new Date(); //添加这条信息的时间
					//将学生的姓名转成该名字的拼音作为他的账号
					HanyuPinyinHelper hanyuPinyinHelper = new HanyuPinyinHelper();
			        String account = hanyuPinyinHelper.toHanyuPinyin(student.getName());
			        student.setAccount(account);
					student.setCode(code); //学生编码
					student.setYear(year); //届次
					student.setClassCode(classCode); //届次code
					student.setCreateTime(createTime); //创建时间
					if ("0".equals(student.getIsFinish())) { //代表该学生未毕业
						CheckParamUtil<Student> checkParamUtil = new CheckParamUtil<>();
						result = checkParamUtil.checkParam(student); //判断未毕业学生参数是否为空
					} else { //代表该学生已毕业
						CheckParamUtil<Student> checkParamUtil = new CheckParamUtil<>();
						result = checkParamUtil.checkParam1(student); //判断已毕业学生参数是否为空
					}
					if ("checkSuccess".equals(result)) { //代表校验参数是否为空通过
						entityManager.persist(student); //执行添加
						result = "addSuccess";
					} else { //代表校验参数是否为空不通过
						return result;
					}	
				} else { //代表该学学已经添加过了
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
	 * @return String
	 * 
	 * @describe : 根据id删除学生信息
	 * 
	 * @see com.yr.dao.StudentDao#deleteStudent(java.lang.Integer)
	 */
	public String deleteStudent(Integer id) {
		Student student = entityManager.find(Student.class, id); //根据id查出该学生的所有信息
		String account = student.getAccount(); //获取该学生的账号
		entityManager.remove(student); //执行删除
		return account; //将账号返回
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
	public String updateStudent(Student student) {
		String result = "";
		try {
				Boolean bool = CheckTelephoneUtil.isMobile(student.getTel()); //判断电话号码的格式是否正确
				if (bool.equals(false)) { //代表不正确
					return "telformattingError";
				}
				Boolean boo = CheckTelephoneUtil.isMobile(student.getHomeTel()); //判断家长电话的格式是否正确
				if (boo.equals(false)) { //代表不正确
					return "homeTelformattingError";
				}
				Student student1 = entityManager.find(Student.class, student.getId()); //根据id查出该学生的所有信息
				student1.setAddr(student.getAddr()); //修改地址
				student1.setBirth(student.getBirth()); //修改生日
				student1.setSex(student.getSex()); //修改性别
				student1.setClassCode(student.getClassCode()); //修改届次code
				Clas clas = queryClas(student.getClassCode()); //根据届次code查出数据
				student1.setYear(clas.getYear()); //将届次表里的届次字段作为学生表的届次修改
				student1.setTel(student.getTel()); //修改电话
				student1.setIsFinish(student.getIsFinish());  //修改是否毕业
				student1.setIsItDisplayed(student.getIsItDisplayed()); //是否展示该学生
				student1.setHomeTel(student.getHomeTel()); //修改家长电话
				student1.setInTime(student.getInTime()); //修改入学时间
				//这里因为有已毕业或未毕业的学生
				if (null != student.getFinishTime() || !"".equals(student.getFinishTime())) { //判断毕业时间不能为空或null
					student1.setFinishTime(student.getFinishTime()); //修改毕业时间
				}
				if (null != student.getOfferTime() || !"".equals(student.getOfferTime())) { //判断入职时间不能为空或null
					student1.setOfferTime(student.getOfferTime()); //修改入职时间
				}
				if (null != student.getOfferIncome() || !"".equals(student.getOfferIncome())) { //判断工资不能为空或null
					student1.setOfferIncome(student.getOfferIncome()); //修改工资
				}
				if ("0".equals(student.getIsFinish())) { //代表未毕业
					CheckParamUtil<Student> checkParamUtil1 = new CheckParamUtil<>();
					result = checkParamUtil1.checkParam(student); //判断参数是否为空
				} else {
					CheckParamUtil<Student> checkParamUtil1 = new CheckParamUtil<>();
					result = checkParamUtil1.checkParam1(student); //判断参数是否为空
				}
				if ("checkSuccess".equals(result)) { //代表检验参数是否为空通过
					entityManager.merge(student1); //执行修改
					result = "updateSuccess";
				} else { //代表检验参数是否为空 不通过
					return result;
				}
		} catch (Exception e) {
			result = "updateFial";
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
		String jpql = "select count(*) from yr_student where name =:name"; //查询要插入的学生是否已存在
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
		String jpql = "select count(*) from yr_student"; //查询学生的总数
		String value = entityManager.createNativeQuery(jpql).getSingleResult().toString();
		if ("0".equals(value)) { //如果总数为0 编号就是1001
			code = "1001";
		} else { //如果总数不为0
			String sql = "select max(`code`)  from yr_student"; //查出code字段的最大值
			String sqlCode = entityManager.createNativeQuery(sql).getSingleResult().toString();
			Integer integer = Integer.valueOf(sqlCode) + 1; //查出code字段的最大值加1
			code = integer.toString(); //integer转 String
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
	 * @describe 根据code查询届次表的数据
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
		String jpql = "select val from Dic where keyv=:keyv"; //根据keyv字段查出字典表的val字段
		String val = (String) entityManager.createQuery(jpql).setParameter("keyv", "stu").getSingleResult();
		String jpql1 = "select code from Role where name=:name"; //根据查出的字段val（角色） 去yr_role表根据name查出 对应的code
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
		String jpql = "from Student where isFinish =:isFinish"; //查询出所有未毕业学生
		List<Student> student = entityManager.createQuery(jpql).setParameter("isFinish", "0").getResultList();
		return student;
	}
	
	/**
	 * 
	 * @Date : 2018年5月26日上午11:49:13
	 * 
	 * @author : 唐子壕
	 *	
	 * @param code 
	 *	
	 * @return Student
	 * 
	 * @describe 根据code查询学生对象
	 * 
	 * @see com.yr.dao.StudentDao#querytoCode()
	 */
	public Student querytoCode(String code) {
		String jpql = "from Student where code=:code"; //根据code查询学生表的数据
		Student student = (Student) entityManager.createQuery(jpql).setParameter("code", code).getSingleResult();
		return student;
	}
	
	/**
	 * 
	 * @Date : 2018年5月31日下午3:28:15
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id 
	 * 
	 * @return Student 
	 * 
	 * @describe : 就业信息回显
	 *  
	 * @see com.yr.dao.StudentDao#employment(java.lang.Integer)
	 */
	public Student employment(Integer id) {
		Student student = entityManager.find(Student.class, id);
		String year = session(student.getClassCode()); //获取到该学生的届次和批次（已拼接）
		student.setYear(year); //将拼接的届次和批次赋值给学生表里的year字段显示到页面
		return student;
	}
	
	/**
	 * 
	 * @Date : 2018年5月31日下午4:49:47
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : String 
	 * 
	 * @param code 
	 * 
	 * @describe : 根据code查询出该学生的届次和批次
	 */
	public String session(String code) {
		String jpql = "from Clas where code=:code"; //根据届次code查出该学生的届次信息
		Clas clas = (Clas) entityManager.createQuery(jpql).setParameter("code", code).getSingleResult();
		String year = clas.getYear(); //获取到届次
		String name = clas.getName(); //获取到批次
		String result = year + "-" + name; //将届次和批次拼接在一起
		return result;
	}
	
	/**
	 * 根据账号获取学生数据
	 * @author 林水桥
	 * @param userName 学生账号
	 * @return Student 返回学生数据
	 * 2018年5月31日下午10:15:29
	 */
	public Student getAccount(String userName) {
		Student student = (Student) entityManager.createQuery("from Student where account=:userName")
				.setParameter("userName", userName).getSingleResult();
		return student;
	}
	
}
