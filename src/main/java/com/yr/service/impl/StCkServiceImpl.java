package com.yr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.ClasDao;
import com.yr.dao.HoliDao;
import com.yr.dao.RoleDao;
import com.yr.dao.StCkDao;
import com.yr.dao.StudentDao;
import com.yr.entity.CheckTime;
import com.yr.entity.Clas;
import com.yr.entity.Holiday;
import com.yr.entity.Role;
import com.yr.entity.Student;
import com.yr.entity.StudentCheck;
import com.yr.service.StCkService;
import com.yr.util.DateUtils;
import com.yr.util.JsonUtils;

import net.sf.json.JSONObject;

/**
 * 学生考勤Service层
 * @author 林水桥
 * 2018年5月25日下午9:58:18
 */
@Transactional
@Service("stCkServiceImpl")
public class StCkServiceImpl implements StCkService {
	
	@Autowired
	private StCkDao stCkDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private ClasDao clasDao;
	@Autowired
	private HoliDao holiDao;
	@Autowired
	private RoleDao roleDao;
	private static Integer addReturn = 0;
	/**
	 * 签到   。。。。  未完成批量签到
	 * @author 林水桥
	 * @param code     学生代码
	 * @return String 返回签到状态 0为签到成功
	 * 2018年5月25日下午10:26:04
	 */
	public String duty(String code) {
		Map<String, Object> map = new HashMap<>();
		Student student = studentDao.querytoCode(code); //根据学生编码查询学生
		Clas clas = clasDao.getCode(student.getClassCode()); //根据届次编码查询届次
		String checkTimeCode = DateUtils.getAmPmNt(); //获得当前考勤时间编码
		CheckTime checkTime = stCkDao.getCheckTime(checkTimeCode); //根据考勤时间编码查询考勤时间数据
		Date nowDay = DateUtils.getCurrentDateA(); //获取当前日期Date类型
		String nowsDay = DateUtils.getCurrentDate(); //获取当前日期String类型
		String nowTime = DateUtils.getCurrentTime(); //获取当前时间
		Long lateTime = DateUtils.getDistanceTimes(nowsDay + " " + nowTime, 
				nowsDay + " " + checkTime.getStartTime()); //获取标准上课与实际到达时间差(分钟)
		Integer status = 1;
		StudentCheck studentCheck = new StudentCheck(); //学生考勤实体
		Holiday holiday = holiDao.getDuty(clas.getCode()); //获取对应届次发布假期数据
		StudentCheck studentCk = stCkDao.repeatSign(student.getCode(), checkTimeCode, nowDay); //重复签到
		if (null != holiday && nowDay.getTime() > holiday.getStartDate().getTime() 
		&& nowDay.getTime() < holiday.getEndDate().getTime() 
		&& DateUtils.getCurrentDateE(nowTime) > DateUtils.getCurrentDateE(holiday.getStartTime()) 
		&& DateUtils.getCurrentDateE(nowTime) < DateUtils.getCurrentDateE(holiday.getEndTime())) { 
			addReturn = 0;
		} else if (null == studentCk) {
			if (lateTime < 0) {
				status = 1;
				studentCheck.setLateTime(Math.abs(Integer.valueOf(String.valueOf(lateTime)))); //迟到时间
			} else {
				status = 0;
			}
			studentCheck.setClassCode(clas.getCode()); //届次编码
			studentCheck.setClassName(clas.getName()); //届次名称
			studentCheck.setStudentCode(student.getCode()); //学生编码
			studentCheck.setStudentName(student.getName()); //学生名称
			studentCheck.setCheckTimeCode(checkTime.getCode()); //考勤时间编码
			studentCheck.setCheckTimeDesc(checkTime.getTimeName()); //考勤时间名称
			studentCheck.setCheckTime(nowDay); //考勤日期
			studentCheck.setStartTime(checkTime.getStartTime()); //标准上课时间
			studentCheck.setRetyTime(nowTime); //实际到达时间
			studentCheck.setStatus(status); //考勤状态 0没迟到,1迟到
			studentCheck.setIsNote(1); //默认无请假条
			studentCheck.setCreateTime(DateUtils.getCurrentDateTimeA()); //创建时间
			addReturn = stCkDao.add(studentCheck);
		}
		if (0 == addReturn) {
			map.put("code", 1);
			map.put("msg", "签到失败");
		} else {
			map.put("code", 0);
			map.put("msg", "签到成功");
		}
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 添加考勤
	 * @author 林水桥
	 * @param stCk     学生考勤实体数据
	 * @return Integer 返回添加ID
	 * 2018年5月25日下午10:03:49
	 */
	public Integer add(StudentCheck stCk) {
		
		stCkDao.add(stCk);
		
		return null;
	}
	
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
		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		List<Role> roList = roleDao.queryR(userName);
		for (Role role : roList) {
			if ("学生".equals(role.getName())) {
				Student student = studentDao.getAccount(userName);
				name = student.getName();
				break;
			} else if ("家长".equals(role.getName())) {
				name = "不能使用";
				break;
			}
		}
		return stCkDao.getAttendance(page, limit, name, checkTC, status);
	}
	
	/**
	 * 查询考勤时间
	 * @author 林水桥
	 * @return String 返回考勤时间json数据
	 * 2018年5月28日下午9:30:09
	 */
	public String checkTimeCode() {
		List<CheckTime> checkTime = stCkDao.checkTimeCode();
		return JsonUtils.beanListToJson(checkTime);
	}
	
	/**
	 * 修改考勤数据
	 * @author 林水桥
	 * @param stCk    学生考勤修改数据
	 * @return Integer  返回修改状态 0为修改成功
	 * 2018年5月25日下午10:11:21
	 */
	public Integer update(StudentCheck stCk) {
		
		stCkDao.update(stCk);
		
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
		return stCkDao.get(id);
	}
	
	/**
	 * 当天考勤报告
	 * @author 林水桥
	 * @param page 分页当前页
	 * @param limit 每页多少条记录
	 * @param ckStatus 考勤时间状态 AM,PM,NT
	 * @return String 返回当天考勤数据 根据考勤日期倒序排序
	 * 2018年5月28日下午8:11:41
	 */
	public String report(int page, int limit, String ckStatus) {
		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		String code = null;
		List<Role> roList = roleDao.queryR(userName);
		for (Role role : roList) {
			if ("学生".equals(role.getName())) {
				Student student = studentDao.getAccount(userName);
				code = student.getCode();
				break;
			} else if ("家长".equals(role.getName())) {
				code = "不能使用";
				break;
			}
		}
		Date nowDay = DateUtils.getCurrentDateA();
		String list = stCkDao.report(page, limit, code, nowDay, ckStatus);
		return list;
	}

	@Override
	public String stckDic() {
		String str = stCkDao.stckDic();
		return str;
	}
}
