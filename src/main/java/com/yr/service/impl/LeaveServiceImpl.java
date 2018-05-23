package com.yr.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.LeaveDao;
import com.yr.entity.Leave;
import com.yr.service.LeaveService;

/**
 * 请假Service
 * @author zxy
 *
 * 2018年5月23日 上午10:55:09
 *
 */
@Transactional
@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveDao leaveDao;
	
	/**
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:33:32
	 * 
	 * @param leave 
	 * @return true: 添加成功    false: 添加失败事务回滚
	 */
	public Boolean add(Leave leave) {
		try {
			leaveDao.add(leave);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 查询
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:33:36
	 * 
	 * @return 返回查询出来的集合以便于页面显示
	 */
	public List<Leave> query() {
		try {
			List<Leave> listLeave = leaveDao.query();
			return listLeave;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:33:44
	 * 
	 * @param studentCode 学生的code
	 * @return 返回指定的某个学生的所有请假信息
	 */
	public List<Leave> query(String studentCode) {
		try {
			List<Leave> listLeave = leaveDao.query(studentCode);
			return listLeave;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月23日 上午11:33:47
	 * 
	 * @param leave 请假对象
	 * @return true: 取消成功	 false: 取消失败(事务回滚)
	 */
	public Boolean cancelLeave(Leave leave) {
		try {
			leaveDao.cancelLeave(leave);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
