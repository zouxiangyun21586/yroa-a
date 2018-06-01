package com.yr.service;

import java.util.List;

import com.yr.entity.Report;
import com.yr.entity.StudentCheck;

/**
 * 学生考勤
 * @author 林水桥
 * 2018年5月25日下午9:59:12
 */
public interface StCkService {
	
	/**
	 * 签到
	 * @author 林水桥
	 * @param code     学生代码
	 * @return String 返回签到状态 0为签到成功
	 * 2018年5月25日下午10:26:04
	 */
	String duty(String code);
	
	/**
	 * 添加考勤
	 * @author zxy
	 * @param stCk 学生考勤实体数据
	 * @return String 是否成功
	 * 2018年5月25日下午10:03:49
	 */
	String  add(StudentCheck stCk);
	
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
	 * 查询考勤时间
	 * @author 林水桥
	 * @return String 返回考勤时间json数据
	 * 2018年5月28日下午9:30:09
	 */
	String checkTimeCode();
	
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
	 * 当天考勤报告
	 * @author 林水桥
	 * @param page 分页当前页
	 * @param limit 每页多少条记录
	 * @param ckStatus 考勤时间状态 AM,PM,NT
	 * @return String 返回当天考勤数据 根据考勤日期倒序排序
	 * 2018年5月28日下午8:11:41
	 */
	String report(int page, int limit, String ckStatus);
	
	/**
	 * 查询字典中考勤的所有状态
	 * @author zxy
	 * 
	 * 2018年5月31日 下午5:58:10
	 * 
	 * @return 判断是否执行成功
	 */
	String stckDic();
	
	/**
	 * 导出考勤列表数据
	 * @author 林水桥
	 * @return List<StudentCheck> 导出的所有数据
	 * 2018年6月1日下午5:05:31
	 */
	List<StudentCheck> getExcel();
	
	/**
	 * 导出当天考勤数据
	 * @author 林水桥
	 * @return List<Report> 导出的所有数据
	 * 2018年6月1日下午5:05:31
	 */
	List<Report> getReportExcel();
	
}
