package com.yr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.LeaveDao;
import com.yr.entity.Leave;
import com.yr.service.LeaveService;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

import net.sf.json.JSONObject;

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
	 * 添加假条
	 * @author zxy
	 * 
	 * 2018年5月30日 下午9:50:55
	 * 
	 * @param leave
	 * @return
	 */
	@Override
	public String add(Leave leave) {
		Map<String, Object> map = new HashMap<String, Object>();
		String str = leaveDao.add(leave);
		if (str.equals("succ")) {
			map.put("code", 0);
			map.put("msg", "添加成功");
		} else if (str.equals("error")) {
			map.put("code", 1);
			map.put("msg", "添加失败");
		}
		String result = JSONObject.fromObject(map).toString();
		return result;
	}

	/**
	 * 回显查询
	 * @author zxy
	 * 
	 * 2018年5月30日 下午9:50:50
	 * 
	 * @param code
	 * @return
	 */
	@Override
	public String query(String code) {
		List<Leave> listLeave = leaveDao.query(code);
		String strLeave = JsonUtils.beanListToJson(listLeave);
		return strLeave;
	}

	/**
	 * 分页查询所有
	 * @author zxy
	 * 
	 * 2018年5月30日 下午9:50:22
	 * 
	 * @param page
	 * @param limit
	 * @param name
	 * @return
	 */
	@Override
	public String query(Integer page, Integer limit, String name) {
		PageUtil pu = leaveDao.query(page, limit, name);
		String result = JsonUtils.beanToJson(pu);
		return result;
	}

	/**
	 * 取消请假
	 * @author zxy
	 * 
	 * 2018年5月30日 下午9:50:40
	 * 
	 * @param leave
	 * @return
	 */
	@Override
	public String cancelLeave(Leave leave) {
		Map<String, Object> map = new HashMap<String, Object>();
		String str = leaveDao.cancelLeave(leave);
		if (str.equals("succ")) {
			map.put("code", 0);
			map.put("msg", "取消成功");
		} else if (str.equals("error")) {
			map.put("code", 1);
			map.put("msg", "取消失败");
		}
		String result = JSONObject.fromObject(map).toString();
		return result;
	}
}
