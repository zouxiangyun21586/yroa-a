package com.yr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.ClasDao;
import com.yr.entity.Clas;
import com.yr.service.ClasService;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

import net.sf.json.JSONObject;

/**
 * 届次 Service实现类
 * @author zxy
 *
 * 2018年5月22日 下午4:51:56
 *
 */
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
	@Transactional
	@Override
	public String add(Clas clas) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String str = clasDao.add(clas);
			if (str.equals("succ")) {
				map.put("code", 0);
				map.put("msg", "添加成功");
			} else if (str.equals("error")) {
				map.put("code", 1);
				map.put("msg", "添加失败");
			}
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "添加失败");
		}
		String result = JSONObject.fromObject(map).toString();
		return result;
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
	@Transactional
	@Override
	public String update(Clas clas) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			clasDao.update(clas);
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
	 * @param clas 届次对象
	 * @return 返回boolean 判断是否成功
	 */
	@Transactional
	@Override
	public String delete(Clas clas) {
		return "error";
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
	@Transactional
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
	 * @param id 某届次的id
	 * @return 届次对象,用于数据回显
	 */
	@Transactional
	@Override
	public Clas get(Integer id) {
		Clas listClas = clasDao.get(id);
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
	@Transactional
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
	@Transactional
	@Override
	public Integer openClss(String code) {
		Integer oc = clasDao.openClss(code);
		if (oc == 1) {
			return 1;
		}
		return 0;
	}

	/**
	 * 提供方法给添加假期模块
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:48:57
	 * 
	 * @param code 根据届次code查询届次数据
	 * @return 返回某届次的对象
	 */
	@Override
	public Clas getCode(String code) {
		return clasDao.getCode(code);
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
	@Transactional
	@Override
	public String getOnly(String year) {
		String cla = clasDao.getOnly(year);
		return cla;
	}

	/**
	 * 查询所有
	 * @author zxy
	 * 
	 * 2018年5月24日 下午5:34:01
	 * 
	 * @return
	 */
	@Transactional
	@Override
	public List<Clas> query() {
		List<Clas> listClas = clasDao.query();
		return listClas;
	}
}
