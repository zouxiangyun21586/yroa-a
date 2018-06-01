package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.StCkDao;
import com.yr.entity.CheckTime;
import com.yr.entity.Dic;
import com.yr.entity.StudentCheck;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

/**
 * 学生考勤Dao层
 * @author 林水桥
 * 2018年5月25日下午9:44:35
 */
@Repository("stCkDaoImpl")
public class StCkDaoImpl implements StCkDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 查询所有考勤数据     带分页
	 * @author 林水桥
	 * @param page             当前页
	 * @param limit            每页多少条数据
	 * @param name             学生姓名模糊查询
	 * @param checkTC          考勤时间编码 AM PM NT
	 * @param status           考勤状态 0没迟到 1迟到 2旷课 3请假 4早退
	 * @return String          返回学生考勤数据json格式
	 * 2018年5月28日下午10:18:33
	 */
	public String getAttendance(int page, int limit, String name, String checkTC, Integer status) {
		PageUtil pageUtil = new PageUtil();
        try {
            int count = 0;
            String jpql = getJpql(name, checkTC, status);
            List<StudentCheck> list = new ArrayList<StudentCheck>();
            List<StudentCheck> list1 = new ArrayList<StudentCheck>();
            name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
            if (null != name && !"".equals(name) && null == checkTC && "".equals(checkTC) && null == status) {
                list = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).setParameter("studentName", "%" + name + "%").getResultList();
                count = Integer.parseInt(entityManager.createNativeQuery(
                		"SELECT COUNT(*) FROM yr_student_check where student_name like :studentName")
                        .setParameter("studentName", "%" + name + "%").getSingleResult().toString());
            } else if (null != name && !"".equals(name) && null != checkTC && !"".equals(checkTC) && null == status) {
            	list = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit).setMaxResults(limit)
            	.setParameter("studentName", "%" + name + "%").setParameter("checkTC", checkTC).getResultList();
            	count = Integer.parseInt(entityManager.createNativeQuery("SELECT COUNT(*) FROM yr_student_check where "
+ "student_name like :studentName and check_time_code=:checkTC").setParameter("studentName", "%" + name + "%")
            	.setParameter("checkTC", checkTC).getSingleResult().toString());
            } else if (null != name && !"".equals(name) && null != checkTC && !"".equals(checkTC) && null != status) {
            	list = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit).setMaxResults(limit)
.setParameter("studentName", "%" + name + "%").setParameter("checkTC", checkTC).setParameter("status", status)
            	.getResultList();
                count = Integer.parseInt(entityManager.createNativeQuery("SELECT COUNT(*) FROM yr_student_check where "
+ "student_name like :studentName and check_time_code=:checkTC and status=:status").setParameter(
"studentName", "%" + name + "%").setParameter("checkTC", checkTC)
                		.setParameter("status", status).getSingleResult().toString());
            } else {
                list = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).getResultList();
                count = Integer.parseInt(entityManager
                       .createNativeQuery("SELECT COUNT(*) FROM yr_student_check").getSingleResult().toString());
            }
            for (StudentCheck holiday : list) {
				list1.add(holiday);
			}
            pageUtil = new PageUtil(limit, page, count);
            pageUtil.setCount(count);
            pageUtil.setData(list1);
            pageUtil.setCode(0);
            pageUtil.setMsg("OK");
        } catch (Exception e) {
            pageUtil.setCode(1);
            pageUtil.setMsg("没有数据");
            e.printStackTrace();
        }
        return JsonUtils.beanToJson(pageUtil);
	}
	
	/**
	 * 添加考勤
	 * @author 林水桥
	 * @param stCk     学生考勤实体数据
	 * @return Integer 返回添加ID
	 * 2018年5月25日下午10:03:49
	 */
	public Integer add(StudentCheck stCk) {
		entityManager.persist(stCk);
		return stCk.getId();
	}
	
	/**
	 * 修改考勤数据
	 * @author 林水桥
	 * @param stCk    学生考勤修改数据
	 * @return Integer  返回修改状态 0为修改 
	 * 2018年5月25日下午10:11:21
	 */
	public Integer update(StudentCheck stCk) {
		
		return null;
	}
	
	/**
	 * 学生考勤数据回显
	 * @author 林水桥
	 * @param id     学生考勤ID
	 * @return  StudentCheck   学生考勤数据
	 * 2018年5月25日下午10:19:01
	 */
	public StudentCheck get(Integer id) {
		StudentCheck studentCk = new StudentCheck();
		try {
			studentCk = (StudentCheck) entityManager.createQuery("from StudentCheck where id = :id")
					.getSingleResult();
		} catch (Exception e) {
			studentCk = null;
		}
		return studentCk;
	}
	
	/**
	 * 判断重复签到
	 * @author 林水桥
	 * @param stuCode  		学生编码
	 * @param checkTimeCode 考勤时间编码
	 * @param checkDate     考勤日期
	 * @return StudentCheck 返回考勤对象，null为无重复  
	 * 2018年5月26日下午8:45:53
	 */
	public StudentCheck repeatSign(String stuCode, String checkTimeCode, Date checkDate) {
		StudentCheck stuCk = new StudentCheck();
		try {
			stuCk = (StudentCheck) entityManager.createQuery("from StudentCheck where "
					+ "studentCode = :studentCode and checkTimeCode = :checkTimeCode and "
					+ "checkTime = :checkDate")
					.setParameter("studentCode", stuCode)
					.setParameter("checkTimeCode", checkTimeCode)
					.setParameter("checkDate", checkDate)
					.getSingleResult();
		} catch (Exception e) {
			stuCk = null;
		}
		return stuCk;
	}
	
	/**
	 * 查询考勤时间
	 * @author 林水桥
	 * @return List<CheckTime> 返回考勤时间List
	 * 2018年5月28日下午9:30:09
	 */
	public List<CheckTime> checkTimeCode() {
		List<CheckTime> checkTime = entityManager.createQuery("from CheckTime").getResultList();
		return checkTime;
	}
	
	/**
	 * 根据考勤时间code，获取考勤时间对象
	 * @author 林水桥
	 * @param code     考勤时间code
	 * @return CheckTime 考勤时间数据
	 * 2018年5月26日下午9:50:44
	 */
	public CheckTime getCheckTime(String code) {
		CheckTime checkTime = (CheckTime) entityManager.createQuery("from CheckTime where code = :code")
						      .setParameter("code", code) 
							  .getSingleResult();
		return checkTime;
	}
	
	/**
	 * 将查询分页查询语句单独拿出
	 * @author 林水桥
	 * @param name      学生姓名 
	 * @param checkTC   考勤时间代码
	 * @param status    考勤状态
	 * @return String   查询语句
	 * 2018年5月28日下午11:25:36
	 */
	public String getJpql(String name, String checkTC, Integer status) {
		String jpql = "FROM StudentCheck ORDER BY checkTime desc";
        if (null != name && !"".equals(name) && null == checkTC && "".equals(checkTC) && null == status) {
            jpql = "FROM StudentCheck where studentName like :studentName ORDER BY checkTime desc";
        } else if (null != name && !"".equals(name) && null != checkTC && !"".equals(checkTC) && null == status) {
            jpql = "FROM StudentCheck where studentName like :studentName and checkTimeCode=:checkTC "
            		+ "ORDER BY checkTime desc";
        } else if (null != name && !"".equals(name) && null != checkTC && !"".equals(checkTC) && null != status) {
            jpql = "FROM StudentCheck where studentName like :studentName and checkTimeCode=:checkTC "
            		+ "and status=:status ORDER BY checkTime desc";
        }
        return jpql;
	}
	
	/**
	 * 当天考勤报告
	 * @author 林水桥
	 * @return String 返回当天考勤数据
	 * 2018年5月28日下午8:11:41
	 */
	public String report() {
		
		return null;
	}

	@Override
	public String stckDic() {
		List<Dic> listDic = entityManager.createQuery("From Dic where type = :type")
				.setParameter("type", "status").getResultList();
		String strJson = JsonUtils.listToJson(listDic);
		return strJson;
	}
	
}
