package com.yr.dao;

import java.util.List;

import com.yr.entity.Leave;
import com.yr.util.PageUtil;

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
	 * @return String
	 */
	String add(Leave leave);
	
	/**
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月30日 下午9:42:38
	 * 
	 * @param code 学生code
	 * @return List<Leave>所有值
	 */
	List<Leave> query(String code);

	/**
	 * 查询所有假条
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:06:38
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @return PageUtil 返回查询的所有假条信息
	 */
	PageUtil query(Integer page, Integer limit, String name);

	/**
	 * 取消请假
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:17:00
	 * 
	 * @param leave 请假对象
	 * @return 判断是否执行成功
	 */
	String cancelLeave(Leave leave);	
}
