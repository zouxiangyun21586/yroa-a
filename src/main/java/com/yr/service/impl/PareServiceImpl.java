package com.yr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.AccountDao;
import com.yr.dao.PareDao;
import com.yr.dao.StudentDao;
import com.yr.entity.Account;
import com.yr.entity.Parents;
import com.yr.service.PareService;
import com.yr.util.CheckTelephoneUtil;
import com.yr.util.DateUtils;
import com.yr.util.HanyuPinyinHelper;

import net.sf.json.JSONObject;

/**
 * 家长Service层
 * @作者 林水桥
 * 2018年6月4日下午2:50:47
 */
@Transactional
@Service("pareServiceImpl")
public class PareServiceImpl implements PareService {
	
	@Autowired
	private PareDao pareDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private StudentDao studentDao;
	private static final Integer T8 = 8;
	
	/**
	 * 家长分页查询
	 * @author 林水桥
	 * @param page   当前页
	 * @param limit  每页多少条数据
	 * @param name   家长姓名 模糊查询
	 * @return String 返回json数据
	 * 2018年6月4日下午2:44:57
	 */
	public String getParents(int page, int limit, String name) {
		return pareDao.getParents(page, limit, name);
	}
	
	/**
	 * 添加家长
	 * @author 林水桥
	 * @param parents    添加的家长数据
	 * @param stuName    学生姓名
	 * @return Integer   返回家长ID
	 * 2018年6月4日上午10:20:53
	 */
	public Integer add(Parents parents, String stuName) {
		parents.setAccount(new HanyuPinyinHelper().toHanyuPinyin(parents.getName()));
		parents.setCode(pareDao.getCode());
		Date nowTime = DateUtils.getCurrentDateTimeA();
		parents.setCreateTime(nowTime);
		parents.setUpdateTime(nowTime);
		String roleCode = pareDao.roleCode();
		String tel = parents.getTel();
		Integer test = null;
		Integer count = studentDao.queryCountName(stuName);
		if (0 != count) {
			test = pareDao.add(parents);
		}
		if (null != test) {
			Account account = new Account();
			account.setIsAdmin("否");
			account.setUserName(parents.getAccount());
			account.setPassWord(tel.substring(tel.length() - T8, tel.length()));
			account.setTel(tel);
			accountDao.addId(account, roleCode);
			studentDao.updateParents(stuName, parents.getCode(), parents.getTel());
		}
		return test;
	}
	
	/**
	 * 修改家长
	 * @author 林水桥
	 * @param parents   要修改的家长数据
	 * @return String  0为修改失败
	 * 2018年6月4日上午10:26:21
	 */
	public String update(Parents parents) {
		Map<String, Object> map = new HashMap<String, Object>();
		Parents pare = pareDao.getSingle(parents.getId());
		parents.setUpdateTime(DateUtils.getCurrentDateTimeA());
		Boolean boo = CheckTelephoneUtil.isMobile(parents.getTel()); //校验家长电话号码
		Integer update = 0;
		if (null != pare) {
			update = pareDao.update(parents);
			studentDao.updatePareTel(pare.getCode(), parents.getTel());
		}
		if (0 == update || boo.equals(false)) {
			map.put("code", 1);
			map.put("msg", "修改失败");
		} else {
			map.put("code", 0);
			map.put("msg", "修改成功");
		}
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 数据回显
	 * @author  林水桥
	 * @param id      家长ID
	 * @return Parents 家长数据
	 * 2018年6月5日下午4:40:22
	 */
	public Parents getSingle(Integer id) {
		return pareDao.getSingle(id);
	}
	
}
