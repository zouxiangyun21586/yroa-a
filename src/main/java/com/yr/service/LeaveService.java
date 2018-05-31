package com.yr.service;

import com.yr.entity.Leave;

/**
 * 请假 Service接口
 * 
 * @author zxy
 *
 *         2018年5月23日 上午10:54:26
 *
 */
public interface LeaveService {
	/**
	 * 添加假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:06:13
	 * 
	 * @param leave 假条信息
	 * @return String 判断是否执行成功
	 */
	String add(Leave leave);
	
	/**
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月30日 下午9:42:38
	 * 
	 * @param code 学生code
	 * @return String 判断是否执行成功
	 */
	String query(String code);

	/**
	 * 查询所有假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:06:38
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @return String 判断是否执行成功
	 */
	String query(Integer page, Integer limit, String name);

	/**
	 * 取消请假
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:17:00
	 * 
	 * @param leave 请假对象
	 * @return String 判断是否执行成功
	 */
	String cancelLeave(Leave leave);
}
