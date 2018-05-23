package com.yr.service;

import java.util.List;

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
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:31:04
	 * 
	 * @param leave 请假对象
	 * @return Boolean 以便于判断
	 */
	Boolean add(Leave leave);

	/**
	 * 查询
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:31:08
	 * 
	 * @return Leave的List集合 用于页面显示
	 */
	List<Leave> query();

	/**
	 * 查询指定学生的假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:31:11
	 * 
	 * @param studentCode 学生code
	 * @return 所有值
	 */
	List<Leave> query(String studentCode);

	/**
	 * 取消请假
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:31:13
	 * 
	 * @param leave 请假对象
	 * @return Boolean 便于判断
	 */
	Boolean cancelLeave(Leave leave);
}
