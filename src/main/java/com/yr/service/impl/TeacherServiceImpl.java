package com.yr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.TeacherDao;
import com.yr.entity.Account;
import com.yr.entity.Teacher;
import com.yr.service.AccountService;
import com.yr.service.TeacherService;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @author zxy
 *
 * 2018年5月22日 上午8:40:18
 *
 */
@Transactional
@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired
	private AccountService accountService;
	
	final Integer number = 2;

	/**
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:58:15
	 * 
	 * @param teacher 老师对象
	 * @return 返回boolean 判断是否成功
	 */
	@Transactional
	public String add(Teacher teacher) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String str = teacherDao.add(teacher);
			if (str.equals("succ")) {
				//需提供Account对象内容:
				//自动生成的账号 ,电话, isAdmin 值是'否',明文的密码 ,.第二个参数是角色的code
				Account ac = new Account();
				ac.setUserName(teacher.getTeacherAccount());
				ac.setTel(teacher.getTel());
				ac.setIsAdmin("否");
				ac.setPassWord("123456");
				String teaCode = teacherDao.roleCode("tea");
				accountService.addId(ac, teaCode);
				map.put("code", 0);
				map.put("msg", "添加成功");
			} else if (str.equals("error")) {
				map.put("code", 1);
				map.put("msg", "添加失败");
			}
			String result = JSONObject.fromObject(map).toString();
			return result;
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "添加失败");
		}
		return null;
	}

	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:58:56
	 * 
	 * @param teacher 老师对象
	 * @return 返回String 判断是否成功
	 */
	@Transactional
	public String update(Teacher teacher) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			teacherDao.update(teacher);
			map.put("code", 0);
			map.put("msg", "修改成功");
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "修改失败");
			map.put("error", e);
		}
		String result = JSONObject.fromObject(map).toString();
		return result;
	}

	/**
	 * 删除
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:01
	 * 
	 * @param teacher 老师对象
	 * @return 返回boolean 判断是否成功
	 */
	@Transactional
	public String delete(Teacher teacher) {
		try {
			Integer code = teacherDao.delete(teacher);
			if (code == number) { // 如果返回的是 2 那么代表可以删除
				return "succ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	/**
	 * 分页查询(页面显值)
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:07
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param year 分页条件
	 *
	 * @return : PageUtil 返回查询的结果,是一个集合
	 */
	public String query(Integer page, Integer limit, String year) {
		PageUtil pu = teacherDao.query(page, limit, year);
		String result = JsonUtils.beanToJson(pu);
		return result;
	}

	/**
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:21
	 * 
	 * @param id 某老师的id
	 * @return 老师对象,用于数据回显
	 */
	public Teacher get(Integer id) {
		Teacher listTeacher = teacherDao.get(id);
		return listTeacher;
	}

	/**
	 * 数据回显老师等级
	 * @author zxy
	 * 
	 * 2018年5月26日 下午5:30:17
	 * 
	 * @return List<Teacher>
	 */
	@Override
	public List<Teacher> query() {
		List<Teacher> listTeacher = teacherDao.query();
		return listTeacher;
	}
	
	/**
	 * 数据回显老师状态
	 * @author zxy
	 * 
	 * 2018年5月26日 下午10:19:01
	 * 
	 * @return List<Teacher>
	 */
	@Override
	public List<Teacher> queryIs() {
		List<Teacher> listTeacher = teacherDao.queryIs();
		return listTeacher;
	}

	@Override
	public String queryTeacher() {
		List<Teacher> listTeacher = teacherDao.queryTeacher();
		String strTch = JsonUtils.beanListToJson(listTeacher);
		return strTch;
	}

}
