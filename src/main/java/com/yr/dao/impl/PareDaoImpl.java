package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.PareDao;
import com.yr.entity.Parents;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

/**
 * 家长Dao层
 * @作者 林水桥
 * 2018年6月4日上午10:18:21
 */
@Repository("pareDaoImpl")
public class PareDaoImpl implements PareDao {
	@PersistenceContext
	private EntityManager entityManager;
	
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
		PageUtil pageUtil = new PageUtil();
		try {
			int count = 0;
			String jpql = "";
			List<Parents> list = new ArrayList<Parents>();
			if (null == name) {
				jpql = "from Parents order by updateTime desc";
				list = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
						.setMaxResults(limit).getResultList();
				count = Integer.valueOf(entityManager.createNativeQuery("select count(*) "
						+ "from yr_parents").getSingleResult().toString());
			} else {
				jpql = "from Parents where name like :name order by updateTime desc";
				list = entityManager.createQuery(jpql).setParameter("name", "%" + name + "%")
					   .setFirstResult((page - 1) * limit).setMaxResults(limit).getResultList();
				count = Integer.valueOf(entityManager.createNativeQuery("select count(*) from "
						+ "yr_parents where name like :name")
						.setParameter("name", "%" + name + "%").getSingleResult().toString());
			}
			pageUtil = new PageUtil(limit, page, count);
	        pageUtil.setCount(count);
	        pageUtil.setData(list);
	        pageUtil.setCode(0);
	        pageUtil.setMsg("OK");
		} catch (Exception e) {
			pageUtil.setCode(1);
	        pageUtil.setMsg("没有数据");
	        e.printStackTrace();
		}
		return JsonUtils.beanToJson(pageUtil);
	}
	
	/**
	 * 添加家长
	 * @author 林水桥
	 * @param parents    添加的家长数据
	 * @return Integer   返回家长ID
	 * 2018年6月4日上午10:20:53
	 */
	public Integer add(Parents parents) {
		try {
			entityManager.persist(parents);
		} catch (Exception e) {
			parents.setId(null);
			e.printStackTrace();
		}
		return parents.getId();
	}
	
	/**
	 * 修改家长
	 * @author 林水桥
	 * @param parents   要修改的家长数据
	 * @return Integer  0为修改失败
	 * 2018年6月4日上午10:26:21
	 */
	public Integer update(Parents parents) {
		
		return null;
	}
	
	/**
	 * 获取家长code
	 * @author 林水桥
	 * @return String 家长code
	 * 2018年6月4日下午7:37:12
	 */
	public String getCode() {
		String code = "";
		String jpql = "select count(*) from yr_parents"; //查询学生的总数
		String value = entityManager.createNativeQuery(jpql).getSingleResult().toString();
		if ("0".equals(value)) { //如果总数为0 编号就是1001
			code = "1001";
		} else { //如果总数不为0
			String sql = "select max(`code`)  from yr_parents"; //查出code字段的最大值
			String sqlCode = entityManager.createNativeQuery(sql).getSingleResult().toString();
			Integer integer = Integer.valueOf(sqlCode) + 1; //查出code字段的最大值加1
			code = integer.toString(); //integer转 String
		}
		return code;
	}
	
	/**
	 * 到字典表查出key对应得value，根据角色名到角色表查出角色code
	 * @author 林水桥
	 * @return String 角色code
	 * 2018年6月4日下午7:51:58
	 */
	public String roleCode() {
		String jpql = "select val from Dic where keyv=:keyv"; //根据keyv字段查出字典表的val字段
		String val = (String) entityManager.createQuery(jpql).setParameter("keyv", "per").getSingleResult();
		String jpql1 = "select code from Role where name=:name"; //根据查出的字段val（角色） 去yr_role表根据name查出 对应的code
		String code = (String) entityManager.createQuery(jpql1).setParameter("name", val).getSingleResult();
		return code;
	}
	
}
