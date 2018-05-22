package com.yr.dao.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.TeacherDao;
import com.yr.entity.Teacher;

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
	 */
	public void add(Teacher teacher) {
		entityManager.persist(teacher);
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
		
		Teacher t = entityManager.find(Teacher.class, teacher.getId());
		
		Integer id = teacher.getId();
		String name = teacher.getName();
		String sex = teacher.getSex();
		Integer age = teacher.getAge();
		String tel = teacher.getTel();
		String addr = teacher.getAddr();
		Date inTime = teacher.getInTime();
		String level = teacher.getLevel();
		String isleaver = teacher.getIsLeave();
		Date leavetime  = teacher.getLeaveTime();
		String info = teacher.getInfo();
		entityManager.remove(t);
		
		Teacher tc = new Teacher();
		tc.setId(id);
		tc.setName(name);
		tc.setSex(sex);
		tc.setAge(age);
		tc.setTel(tel);
		tc.setAddr(addr);
		tc.setInTime(inTime);
		tc.setLevel(level);
		tc.setIsLeave(isleaver);
		tc.setLeaveTime(leavetime);
		tc.setInfo(info);
		entityManager.merge(tc);
		
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
		Query query = entityManager.createNativeQuery("select count(*) from Clas where teacher_code ="
				+ teacher.getCode());
		BigInteger big = (BigInteger) query.getSingleResult();
		int uid = big.intValue();
		if (query != null && uid != 0) { // 如果届次表中有数据那么不能进行删除老师
			return 1;
		} else {
			Query qu = entityManager.createNativeQuery("delete from teacher where id ="
					+ teacher.getId());
			qu.executeUpdate();
			return number;
		}
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:57:16
	 * 
	 * @return 返回老师集合
	 */
	public List<Teacher> query() {
		Query q = entityManager.createQuery("from Teacher");
		List<Teacher> listResource = q.getResultList();
		return listResource;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:57:29
	 * 
	 * @param id 获取页面是老师id 用来数据回显
	 * @return 返回某老师的对象
	 */
	public Teacher get(Integer id) {
		Query q = entityManager.createQuery("from Teacher where id = " + id);
		Teacher listUser = (Teacher) q.getSingleResult();
		return listUser;
	}

}
