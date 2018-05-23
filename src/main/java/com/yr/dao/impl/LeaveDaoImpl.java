package com.yr.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.LeaveDao;
import com.yr.entity.Leave;

/**
 * 请假 Dao实现类
 * @author zxy
 *
 * 2018年5月23日 上午10:53:43
 *
 */
@Repository
public class LeaveDaoImpl implements LeaveDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 添加假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:12:40
	 * 
	 * @param leave 假条信息
	 */
	public void add(Leave leave) {
		entityManager.persist(leave);
	}

	/**
	 * 查询所有假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:12:54
	 * 
	 * @return 所有假条信息
	 */
	public List<Leave> query() {
		List<Leave> listResource = entityManager.createQuery("from Leave").getResultList();
		return listResource;
	}

	/**
	 * 查询某学生的假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:13:12
	 * 
	 * @param studentCode 学生Code
	 * @return 某学生的所有请假信息
	 */
	public List<Leave> query(String studentCode) {
		List<Leave> listLeave = entityManager.createQuery("From Leave where student_code = ?1")
			.setParameter(1, studentCode).getResultList();
		return listLeave;
	}

	/**
	 * 取消请假
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:17:31
	 * 
	 * @param leave 请假对象
	 */
	public void cancelLeave(Leave leave) {
		entityManager.createQuery("delete Leave where student_code = :student_code")
			.setParameter("student_code", leave.getStudentCode());
	}

}
