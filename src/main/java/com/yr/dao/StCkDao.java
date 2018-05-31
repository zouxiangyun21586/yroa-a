package com.yr.dao;

import java.util.Date;
import java.util.List;

import com.yr.entity.CheckTime;
import com.yr.entity.StudentCheck;

/**
 * 学生考勤
 * @author 林水桥
 * 2018年5月25日下午9:43:14
 */
public interface StCkDao {
	
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
	String getAttendance(int page, int limit, String name, String checkTC, Integer status);
	
	/**
	 * 添加考勤
	 * @author 林水桥
	 * @param stCk     学生考勤实体数据
	 * @return Integer 返回添加ID
	 * 2018年5月25日下午10:03:49
	 */
	Integer add(StudentCheck stCk);
	
	/**
	 * 修改考勤数据
	 * @author 林水桥
	 * @param stCk    学生考勤修改数据
	 * @return Integer  返回修改状态 0为修改成功
	 * 2018年5月25日下午10:11:21
	 */
	Integer update(StudentCheck stCk);
	
	/**
	 * 学生考勤数据回显
	 * @author 林水桥
	 * @param id     学生考勤ID
	 * @return  StudentCheck   学生考勤数据
	 * 2018年5月25日下午10:19:01
	 */
	StudentCheck get(Integer id);
	
	/**
	 * 判断重复签到
	 * @author 林水桥
	 * @param stuCode  		学生编码
	 * @param checkTimeCode 考勤时间编码
	 * @param checkDate     考勤日期
	 * @return StudentCheck 返回考勤对象，null为无重复  
	 * 2018年5月26日下午8:45:53
	 */
	StudentCheck repeatSign(String stuCode, String checkTimeCode, Date checkDate);
	
	/**
	 * 查询考勤时间
	 * @author 林水桥
	 * @return List<CheckTime> 返回考勤时间List
	 * 2018年5月28日下午9:30:09
	 */
	List<CheckTime> checkTimeCode();
	
	/**
	 * 根据考勤时间code，获取考勤时间对象
	 * @author 林水桥
	 * @param code     考勤时间code
	 * @return CheckTime 考勤时间数据
	 * 2018年5月26日下午9:50:44
	 */
	CheckTime getCheckTime(String code);
	
	/**
	 * 当天考勤报告
	 * @author 林水桥
	 * @param page 分页当前页
	 * @param limit 每页多少条记录
	 * @param code    学生code
	 * @param checkTime 考勤时间数据
	 * @return String 返回当天考勤数据 根据考勤日期倒序排序
	 * 2018年5月28日下午8:11:41
	 */
	String report(int page, int limit, String code, Date checkTime);
	
	/**
	 * 查询字典
	 * @author zxy
	 * 
	 * 2018年5月31日 下午5:54:11
	 * 
	 * @param type 字典类型
	 * @return String
	 */
	String stckDic(String type);
	
}
