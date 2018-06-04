package com.yr.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.StCkDao;
import com.yr.entity.CheckTime;
import com.yr.entity.Dic;
import com.yr.entity.Report;
import com.yr.entity.StudentCheck;
import com.yr.util.DateUtils;
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
	private static final Integer T2 = 2;
	private static final Integer T3 = 3;
	private static final Integer T4 = 4;
	private static final Integer T60 = 60;
	private static final Integer T1000 = 1000;
	
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
            if (null != name && !"".equals(name) && null == checkTC && null == status) {
                list = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).setParameter("studentName", "%" + name + "%").getResultList();
                count = Integer.parseInt(entityManager.createNativeQuery(
                		"SELECT COUNT(*) FROM yr_student_check where student_name like :studentName")
                        .setParameter("studentName", "%" + name + "%").getSingleResult().toString());
            } else if (null != name && !"".equals(name) && null != checkTC && null == status) {
            	list = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit).setMaxResults(limit)
            	.setParameter("studentName", "%" + name + "%").setParameter("checkTC", checkTC).getResultList();
            	count = Integer.parseInt(entityManager.createNativeQuery("SELECT COUNT(*) FROM yr_student_check where "
+ "student_name like :studentName and check_time_code=:checkTC").setParameter("studentName", "%" + name + "%")
            	.setParameter("checkTC", checkTC).getSingleResult().toString());
            } else if (null != name && !"".equals(name) && null != checkTC && null != status) {
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
	 * 签到
	 * @author 林水桥
	 * @param stCk  考勤数据
	 * @return Integer 返回签到数据ID
	 * 2018年6月4日上午8:25:17
	 */
	public Integer addPunch(StudentCheck stCk) {
		try {
			entityManager.persist(stCk);
		} catch (Exception e) {
			stCk.setId(0);
			e.printStackTrace();
		}
		return stCk.getId();
	}
	
	/**
	 * 添加考勤
	 * 问题:
	 * 图片添加有bug
	 * @author zxy
	 * @param stCk     学生考勤实体数据
	 * @return Integer 返回添加ID
	 * 2018年5月25日下午10:03:49
	 * @throws  
	 */
	public Integer add(StudentCheck stCk) {
		try {
			StudentCheck sc = new StudentCheck();
			String clCode = entityManager.createNativeQuery(
					"select class_code from yr_student where name = :sName")
					.setParameter("sName", stCk.getStudentName()).getSingleResult().toString();
			String sCode = entityManager.createNativeQuery(""
					+ "select code from yr_student where name = :sName")
					.setParameter("sName", stCk.getStudentName()).getSingleResult().toString();
			sc.setStudentCode(sCode);
			sc.setClassCode(clCode);
			if ("上午".equals(stCk.getCheckTimeDesc())) {
				sc.setCheckTimeCode("AM");
			} else if ("下午".equals(stCk.getCheckTimeDesc())) {
				sc.setCheckTimeCode("PM");
			} else if ("晚上".equals(stCk.getCheckTimeDesc())) {
				sc.setCheckTimeCode("NT");
			}
			sc.setStudentName(stCk.getStudentName());
			sc.setClassName(stCk.getClassName());
			sc.setCheckTimeDesc(stCk.getCheckTimeDesc());
			sc.setCheckTime(new Date());
			String shiji = ""; // 标准上课时间
			if ("上午".equals(stCk.getCheckTimeDesc())) {
				sc.setStartTime("8:00");
				shiji = sc.getStartTime();
			} else if ("下午".equals(stCk.getCheckTimeDesc())) {
				sc.setStartTime("14:00");
				shiji = sc.getStartTime();
			} else if ("晚上".equals(stCk.getCheckTimeDesc())) {
				sc.setStartTime("19:00");
				shiji = sc.getStartTime();
			}
			Date day = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
			String daoda = String.valueOf(sd.format(day));
			
			long from = sd.parse(shiji).getTime();  
			long to = sd.parse(daoda).getTime();  
			int hours = (int) ((to - from) / (T1000 * T60 * T60));
			sc.setLateTime(hours);
			
			if (hours == 0) {
				sc.setStatus(stCk.getStatus());
			} else {
				sc.setStatus(1);
			}
			sc.setIsNote(stCk.getIsNote());
			sc.setCreateTime(new Date());
			entityManager.persist(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
					.setParameter("id", id).getSingleResult();
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
        if (null != name && !"".equals(name) && null == checkTC && null == status) {
            jpql = "FROM StudentCheck where studentName like :studentName ORDER BY checkTime desc";
        } else if (null != name && !"".equals(name) && null != checkTC && null == status) {
            jpql = "FROM StudentCheck where studentName like :studentName and checkTimeCode=:checkTC "
            		+ "ORDER BY checkTime desc";
        } else if (null != name && !"".equals(name) && null != checkTC && null != status) {
            jpql = "FROM StudentCheck where studentName like :studentName and checkTimeCode=:checkTC "
            		+ "and status=:status ORDER BY checkTime desc";
        }
        return jpql;
	}
	
	/**
	 * 当天考勤报告
	 * @author 林水桥
	 * @param page 分页当前页
	 * @param limit 每页多少条记录
	 * @param code    学生code
	 * @param checkTime 当天日期
	 * @param ckStatus 考勤时间状态AM,PM,NT
	 * @return String 返回当天考勤数据 根据考勤日期倒序排序
	 * 2018年5月28日下午8:11:41
	 */
	public String report(int page, int limit, String code, Date checkTime, String ckStatus) {
		PageUtil pageUtil = new PageUtil();
		try {
			int count = 0;
			String jpql = "";
			List<StudentCheck> list = new ArrayList<StudentCheck>();
			if (null == code) {
jpql = "from StudentCheck where checkTime=:checkTime and checkTimeCode=:ckStatus order by checkTime desc";
list = entityManager.createQuery(jpql).setParameter("checkTime", checkTime).setParameter("ckStatus", ckStatus)
					.setFirstResult((page - 1) * limit).setMaxResults(limit).getResultList();
count = Integer.valueOf(entityManager.createNativeQuery("select count(*) from yr_student_check "
+ "where check_date=:checkTime and check_time_code=:ckStatus").setParameter("checkTime", checkTime)
		.setParameter("ckStatus", ckStatus).getSingleResult().toString());
			} else {
			jpql = "from StudentCheck where studentCode=:studentCode and checkTime=:checkTime "
					+ "and checkTimeCode=:ckStatus order by checkTime desc";
list = entityManager.createQuery(jpql).setParameter("studentCode", code).setParameter("ckStatus", ckStatus)
.setParameter("checkTime", checkTime).setFirstResult((page - 1) * limit).setMaxResults(limit).getResultList();
count = Integer.valueOf(entityManager.createNativeQuery("select count(*) from yr_student_check "
+ "where check_date=:checkTime and student_code=:stuCode and check_time_code=:ckStatus")
.setParameter("checkTime", checkTime).setParameter("ckStatus", ckStatus)
.setParameter("stuCode", code).getSingleResult().toString());
			}
			List<Report> reportList = new ArrayList<Report>();
			reportList = getReport(list, reportList);
			pageUtil = new PageUtil(limit, page, count);
	        pageUtil.setCount(count);
	        pageUtil.setData(reportList);
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
	 * 当天考勤需要节俭代码
	 * @author 林水桥
	 * @param list        考勤数据
	 * @param reportList  当天报告
	 * @return List<Report> 返回当天报告
	 * List<Report>
	 * 2018年5月31日下午3:27:00
	 */
	public List<Report> getReport(List<StudentCheck> list, List<Report> reportList) {
		String description = "";
		for (StudentCheck studentCheck : list) {
			description = DateUtils.getCurrentDateT(studentCheck.getCheckTime());
			description += studentCheck.getStudentName();
			description += studentCheck.getCheckTimeDesc();
			Report report = new Report();
			if (0 == studentCheck.getStatus()) {
				report.setStatus("0");
				report.setStatusDesc("正常考勤");
			} else if (1 == studentCheck.getStatus()) {
				report.setStatus("1");
				report.setStatusDesc("迟到" + studentCheck.getLateTime() + "分钟");
			} else if (T2 == studentCheck.getStatus()) {
				report.setStatus("2");
				report.setStatusDesc("旷课");
			} else if (T3 == studentCheck.getStatus()) {
				report.setStatus("3");
				report.setStatusDesc("请假");
			} else if (T4 == studentCheck.getStatus()) {
				report.setStatus("4");
				report.setStatusDesc("早退");
			}
			if ("AM".equals(studentCheck.getCheckTimeCode())) {
				report.setCheckStatus("AM");
				report.setName(description);
			} else if ("PM".equals(studentCheck.getCheckTimeCode())) {
				report.setCheckStatus("PM");
				report.setName(description);
			} else {
				report.setCheckStatus("NT");
				report.setName(description);
			}
			reportList.add(report);
		}
		return reportList;
	}

	@Override
	public String stckDic() {
		List<Dic> listDic = entityManager.createQuery("From Dic where type = :type")
				.setParameter("type", "status").getResultList();
		String strJson = JsonUtils.listToJson(listDic);
		return strJson;
	}
	
	/**
	 * 导出考勤列表数据
	 * @author 林水桥
	 * @param code  学生或家长登录的数据
	 * @return List<StudentCheck> 导出的所有数据
	 * 2018年6月1日下午5:05:31
	 */
	public List<StudentCheck> getExcel(String code) {
		String jpql = "from StudentCheck order by checkTime desc";
		List<StudentCheck> stCk = new ArrayList<StudentCheck>();
		if (null != code) {
			jpql = "from StudentCheck where studentCode=:code order by checkTime desc";
			stCk = entityManager.createQuery(jpql).setParameter("code", code).getResultList();
		} else {
			stCk = entityManager.createQuery(jpql).getResultList();
		}
		return stCk;
	}
	
	/**
	 * 导出当天考勤数据
	 * @author 林水桥
	 * @param code  学生或家长登录的数据
	 * @param checkTime 当天日期
	 * @return List<Report> 导出的所有数据
	 * 2018年6月1日下午5:05:31
	 */
	public List<Report> getReportExcel(String code, Date checkTime) {
		String jpql = "from StudentCheck where checkTime=:time order by checkTime desc";
		List<StudentCheck> stCk = new ArrayList<StudentCheck>();
		if (null != code) {
			jpql = "from StudentCheck where studentCode=:code and checkTime=:time order by checkTime desc";
			stCk = entityManager.createQuery(jpql).setParameter("code", code)
					.setParameter("time", checkTime).getResultList();
		} else {
			stCk = entityManager.createQuery(jpql).setParameter("time", checkTime).getResultList();
		}
		List<Report> report = new ArrayList<Report>();
		report = getReport(stCk, report);
		return report;
	}
	
}
