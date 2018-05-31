package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.LeaveDao;
import com.yr.entity.Leave;
import com.yr.entity.Teacher;
import com.yr.util.PageUtil;

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
	 * @return String
	 */
	public String cancelLeave(Leave leave) {
		try {
			entityManager.createQuery("delete Leave where student_code = :student_code")
			.setParameter("student_code", leave.getStudentCode());
			return "succ";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 添加假条
	 * @author zxy
	 * 
	 * 2018年5月30日 下午9:38:39
	 * 
	 * @param leave
	 * @return
	 */
	@Override
	public String add(Leave leave) {
		try {
			
			return "succ";
		} catch (Exception e) {
			return "error";
		}
	}

	/**
	 * 分页查询
	 * @author zxy
	 * 
	 * 2018年5月30日 下午9:40:37
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @return 分页工具类
	 */
	@Override
	public PageUtil query(Integer page, Integer limit, String name) {
		PageUtil pageUtil = new PageUtil();
		try {
			int count = 0;
			String jpql = "From Leave order by leave_date desc";
			if (null  != name && !"".equals(name)) {
				jpql = "from Leave where name like :name order by leave_date desc";
			}
			List<Teacher> studentList = new ArrayList<Teacher>();
			name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
			if (null  != name && !"".equals(name)) {
				studentList = entityManager.createQuery(jpql)
						.setMaxResults(limit).setFirstResult((page - 1) * limit)
						.setParameter("name", "%" + name + "%").getResultList();
				count = Integer
				.parseInt(entityManager
				 .createNativeQuery("select count(*) from yr_leave where name like :name ")
				   .setParameter("name", "%" + name + "%").getSingleResult().toString());
			} else {
				studentList = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
						.setMaxResults(limit).getResultList();
				count = Integer
						.parseInt(entityManager
						   .createNativeQuery("select count(*) from yr_leave")
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

}
