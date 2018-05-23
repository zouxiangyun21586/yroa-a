package com.yr.dao;

import java.util.List;

import com.yr.entity.Leave;

/**
 * 请假 Dao接口
 * 
 * @author zxy
 *
 *         2018年5月23日 上午10:52:05
 *
 */
public interface LeaveDao {
	
	/**
	 * 添加假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:06:13
	 * 
	 * @param leave 假条信息
	 */
	void add(Leave leave);

	/**
	 * 查询所有假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:06:38
	 * 
	 * @return 假条信息
	 */
	List<Leave> query();

	/**
	 * 查询某学生的所有假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:11:44
	 * 
	 * @param studentCode 学生code
	 * @return 某学生的所有假条信息
	 */
	List<Leave> query(String studentCode);
	
	/**
	 * 取消请假
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:17:00
	 * 
	 * @param leave 请假对象
	 */
	void cancelLeave(Leave leave);	
}
