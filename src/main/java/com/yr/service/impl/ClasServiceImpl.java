package com.yr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.ClasDao;
import com.yr.entity.Clas;
import com.yr.service.ClasService;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

/**
 * 届次 Service实现类
 * @author zxy
 *
 * 2018年5月22日 下午4:51:56
 *
 */
@Transactional
@Service
public class ClasServiceImpl implements ClasService {
	
	@Autowired
	private ClasDao clasDao;
	final Integer number = 2;

	/**
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:58:15
	 * 
	 * @param clas 届次对象
	 * @return 返回boolean 判断是否成功
	 */
	public Boolean add(Clas clas) {
		try {
			clasDao.add(clas);
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
	 * @param clas 届次对象
	 * @return 返回boolean 判断是否成功
	 */
	@Override
	public Boolean update(Clas clas) {
		try {
			clasDao.update(clas);
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
	 * @param clas 届次对象
	 * @return 返回boolean 判断是否成功
	 */
	@Override
	public Boolean delete(Clas clas) {
//		try {
//			Integer code = clasDao.delete(clas);
//			if (code == number) { // 如果返回的是 2 那么代表可以删除
//				return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		return false;
	}

	/**
	 * 分页查询
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:07
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 *
	 * @return : PageUtil 返回查询的结果,是一个集合
	 */
	@Override
	public String query(Integer page, Integer limit, String name) {
		PageUtil pu = clasDao.query(page, limit, name);
		String result = JsonUtils.beanToJson(pu);
		return result;
	}

	/**
	 * 回显
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:21
	 * 
	 * @param code 某届次的code
	 * @return 届次对象,用于数据回显
	 */
	@Override
	public Clas get(String code) {
		Clas listClas = clasDao.get(code);
		return listClas;
	}

	/**
	 * 毕业
	 * @author zxy
	 * 
	 * 2018年5月23日 上午9:47:29
	 * 
	 * @param code 届次
	 * @return 判断
	 */
	@Override
	public Integer graduation(String code) {
		Integer gd = clasDao.graduation(code);
		if (gd == 1) {
			return 1;
		}
		return 0;
	}

	/**
	 * 开课
	 * @author zxy
	 * 
	 * 2018年5月23日 上午10:29:20
	 * 
	 * @param code 届次
	 * @return Integer 有值表示已开课,如果没有值代表还未开
	 */
	@Override
	public Integer openClss(String code) {
		Integer oc = clasDao.openClss(code);
		if (oc == 1) {
			return 1;
		}
		return 0;
	}

	/**
	 * 查询指定值
	 * @author zxy
	 * 
	 * 2018年5月24日 下午5:33:52
	 * 
	 * @param year
	 * @return
	 */
	@Override
	public List<Clas> getOnly(String year) {
		List<Clas> listClas = clasDao.getOnly(year);
		return listClas;
	}

	/**
	 * 查询所有
	 * @author zxy
	 * 
	 * 2018年5月24日 下午5:34:01
	 * 
	 * @return
	 */
	@Override
	public List<Clas> query() {
		List<Clas> listClas = clasDao.query();
		return null;
	}
}
