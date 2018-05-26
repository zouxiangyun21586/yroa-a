package com.yr.service.impl;

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
	public Boolean add(Teacher teacher) {
		try {
			//需提供Account对象内容:
			//自动生成的账号 ,电话, isAdmin 值是'否',明文的密码 ,.第二个参数是角色的code
			Account ac = new Account();
			ac.setUsername(teacher.getTeacherAccount());
			ac.setTel(teacher.getTel());
			ac.setIsAdmin("否");
			ac.setPassword("123456");
			String teaCode = teacherDao.roleCode("tea");
			accountService.addId(ac, teaCode);
			teacherDao.add(teacher);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:58:56
	 * 
	 * @param teacher 老师对象
	 * @return 返回boolean 判断是否成功
	 */
	public Boolean update(Teacher teacher) {
		try {
			teacherDao.update(teacher);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
	public Boolean delete(Teacher teacher) {
		try {
			Integer code = teacherDao.delete(teacher);
			if (code == number) { // 如果返回的是 2 那么代表可以删除
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
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
	 * @param code 某老师的code
	 * @return 老师对象,用于数据回显
	 */
	public Teacher get(String code) {
		Teacher listTeacher = teacherDao.get(code);
		return listTeacher;
	}

}
