package com.yr.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.TeacherDao;
import com.yr.entity.Teacher;
import com.yr.util.HanyuPinyinHelper;
import com.yr.util.PageUtil;

/**
 * 老师的 Dao 实现类(jpa)
 * 
 * @author zxy
 *
 */
@Repository
public class TeacherDaoImpl implements TeacherDao {

	@PersistenceContext
	private EntityManager entityManager;
	final Integer number = 2;

	/**
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:55:59
	 * 
	 * @param teacher 老师对象
	 * @return String 判断是否成功
	 * @throws Exception 
	 */
	public String add(Teacher teacher) {
		
		try {
			Teacher tch = new Teacher();
			tch.setName(teacher.getName());
			String code = code();
			tch.setCode(code); // 需加1
			tch.setSex(teacher.getSex());
			tch.setBirth(teacher.getBirth());
			tch.setTel(teacher.getTel());
			tch.setAddr(teacher.getAddr());
			tch.setInTime(teacher.getInTime());
			tch.setLevel(levelMothed(teacher.getLevel()));
			tch.setInfo(teacher.getInfo());
			tch.setIsLeave(isLeave(teacher.getIsLeave()));
			tch.setCreateTime(new Date());
			HanyuPinyinHelper hanyuPinyinHelper = new HanyuPinyinHelper();
	        String account = hanyuPinyinHelper.toHanyuPinyin(teacher.getName());
			tch.setTeacherAccount(account);
			entityManager.persist(tch); // 老师code  age需要算
			return "succ";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	/**
	 * 获取字段表 keyv  查出 val值     根据 val值在角色表中得到角色对应的 code
	 * 
	 * 第一步: 去yr_dic（字典表） 根据字段keyv  查出 字段 val：（学生）
	 * 第二步: 根据查出的字段val（角色） 去yr_role表根据name查出 对应的code
	 * @param keyv 键
	 * @return String 角色code
	 * 2018年5月26日上午8:40:05
	 */
	public String roleCode(String keyv) {
		String teaVal = (String) entityManager
				.createNativeQuery("select val from yr_dic where keyv = :keyv")
				.setParameter("keyv", keyv).getSingleResult();
		String teaCode = (String) entityManager
				.createNativeQuery("select `code` from yr_role where `name` = :val")
				.setParameter("val", teaVal).getSingleResult();
		return teaCode;
	}
	
	/**
	 * 教学等级
	 * @author zxy
	 * 
	 * 2018年5月25日 下午5:44:37
	 * 
	 * @param level 等级参数
	 * @return 教师等级
	 */
	public static String levelMothed(String level) {
		String lev = "";
		if (level.equals("UI")) {
			lev = level;
		} else if (level.equals("DBA")) {
			lev = level;
		} else if (level.equals("DEVELE")) {
			lev = level;
		} else if (level.equals("DEVMID")) {
			lev = level;
		} else if (level.equals("DEVHIG")) {
			lev = level;
		}
		return lev;
	}
	
	/**
	 * 老师状态
	 * @param leave 状态 1 离职   0或者"" 在职  2 试用 
	 * @return String 状态
	 * 2018年5月25日下午8:58:33
	 */
	public static String isLeave(String leave) {
		String lea = "";
		if (leave.equals("1")) {
			lea = leave;
		} else if (leave.equals("2")) {
			lea = leave;
		} else if (leave.equals("0") || leave.equals("")) {
			lea = leave;
		} 
		return lea;
	}
	
	/**
	 * 获取届次编号
	 * @author zxy
	 * 
	 * 2018年5月24日 下午8:54:03
	 * 
	 * @return String
	 */
	public String code() {
		String code = "";
		String jpql = "select count(*) from yr_teacher";
		String value = entityManager.createNativeQuery(jpql).getSingleResult().toString();
		if ("0".equals(value)) { // 如果数据中没有值 那么编号默认从 1001 开始
			code = "1001";
		} else { // 如果数据库中有值 那么将最大code 数查出 加1 成为下一个code数
			String sql = "select max(`code`)  from yr_teacher";
			String sqlCode = entityManager.createNativeQuery(sql).getSingleResult().toString();
			Integer integer = Integer.parseInt(sqlCode) + 1;
			code = String.valueOf(integer);
		}
		return code;
	}
	
	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:56:02
	 * 
	 * @param teacher 老师对象
	 */
	public void update(Teacher teacher) {
		Teacher teacher1 = entityManager.find(Teacher.class, teacher.getId());
		teacher1.setTel(teacher.getTel());
		teacher1.setLevel(teacher.getLevel());
		teacher1.setIsLeave(teacher.getIsLeave());
		entityManager.merge(teacher1);
		
	}

	/**
	 * 删除
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:56:18
	 * 
	 * @param teacher 老师对象
	 * @return 返回 Integer类型 判断是否删除	1 表示此老师有届次(Clas届次表)不能删除  0 表示可以删除
	 */
	public Integer delete(Teacher teacher) {
		Query query = entityManager
				.createNativeQuery("select count(*) from yr_clas c where c.teacher_code = :code")
				.setParameter("code", teacher.getCode());
		BigInteger big = (BigInteger) query.getSingleResult();
		int uid = big.intValue();
		if (query != null && uid != 0) { // 如果届次表中有数据那么不能进行删除老师
			return 1;
		} else {
			Query qu = entityManager.createNativeQuery("delete from yr_teacher where code = ?1")
					.setParameter(1, teacher.getCode());
			qu.executeUpdate();
			return number;
		}
	}

	/**
	 * 分页查询
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:57:16
	 * 
	 * @return 返回老师集合
	 */
	@Override
	public PageUtil query(Integer page, Integer limit, String name) {
		PageUtil pageUtil = new PageUtil();
		try {
			int count = 0;
			String jpql = "From Teacher order by in_time desc";
			if (null  != name && !"".equals(name)) {
				jpql = "from Teacher where name like :name order by in_time desc";
			}
			List<Teacher> studentList = new ArrayList<Teacher>();
			name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
			if (null  != name && !"".equals(name)) {
				studentList = entityManager.createQuery(jpql)
						.setMaxResults(limit).setFirstResult((page - 1) * limit)
						.setParameter("name", "%" + name + "%").getResultList();
				count = Integer
				.parseInt(entityManager
				 .createNativeQuery("select count(*) from yr_teacher where name like :name ")
				   .setParameter("name", "%" + name + "%").getSingleResult().toString());
			} else {
				studentList = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
						.setMaxResults(limit).getResultList();
				count = Integer
						.parseInt(entityManager
						   .createNativeQuery("select count(*) from yr_teacher")
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
	 * 查询等级(数据回显使用)
	 * @return list<Teacher>
	 * 2018年5月26日上午11:53:44
	 */
	public List<Teacher> query() {
		List<Teacher> listTeacher = entityManager.createQuery("From Dic where type = :type")
				.setParameter("type", "level").getResultList();
//		List<Teacher> listTeacher = entityManager.createNativeQuery(
//				"select * from yr_teacher where `code` in "
//				+ "(select max(`code`) from yr_teacher group by `level`)")
//				.getResultList();
		return listTeacher;
	}
	
	/**
	 * 老师状态
	 * @author zxy
	 * 
	 * 2018年5月26日 下午10:22:45
	 * 
	 * @return list<Teacher>
	 */
	public List<Teacher> queryIs() {
		List<Teacher> listTeacher = entityManager.createQuery("From Dic where type = :type")
				.setParameter("type", "isLeave").getResultList();
		return listTeacher;
	}

	/**
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:57:29
	 * 
	 * @param id 获取页面是老师id 用来数据回显
	 * @return 返回某老师的对象
	 */
	public Teacher get(Integer id) {
		Teacher teacher =  (Teacher) entityManager.createQuery("From Teacher where id = :id")
				.setParameter("id", id).getSingleResult();
		return teacher;
	}

	@Override
	public List<Teacher> queryTeacher() {
		List<Teacher> listTeacher = entityManager.createQuery("From Teacher").getResultList();
		return listTeacher;
	}

}
