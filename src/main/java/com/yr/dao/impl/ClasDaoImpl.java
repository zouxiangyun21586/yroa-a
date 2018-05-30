package com.yr.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.ClasDao;
import com.yr.entity.Clas;
import com.yr.util.DateUtils;
import com.yr.util.PageUtil;

/**
 * 届次 Dao 实现类
 * @author zxy
 *
 * 2018年5月22日 下午4:45:27
 *
 */
@Repository
public class ClasDaoImpl implements ClasDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	final Integer number = 2;

	/**
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:48:16
	 * 
	 * @param clas 届次对象
	 * @return String 判断是否成功
	 */
	@Override
	public String add(Clas clas) {
		try {
			Clas cla = new Clas();
			cla.setName(clas.getName()); // 此届 批次名
			cla.setYear(String.valueOf(DateUtils.getCurrentYear())); // 当前年(当前届数)
			String strCode = code(); // 获取code数
			cla.setCode(strCode);
			cla.setCreateTime(new Date()); // 创建时间(获取当前时间)
			cla.setTeacherCode(clas.getTeacherCode()); // 设置这批届次老师的code(获取页面上填写的老师code)
			String strName = (String) entityManager
					.createNativeQuery("select `name` from yr_teacher where code = ?1")
					.setParameter(1, clas.getTeacherCode()).getSingleResult();
			cla.setTeacherName(strName); // 设置这批届次老师的名字(获取页面上填写的老师code获取到老师名字)
			cla.setIsFinish(ifs(clas.getIsFinish()));
			cla.setStartTime(clas.getStartTime());
			entityManager.persist(cla);
			return "succ";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}

	/**
	 * 是否是毕业生
	 * 
	 * @author zxy
	 * @param aps 参数
	 * @return String
	 * 2018年5月25日下午8:37:46
	 */
	public String ifs(String aps) {
		String cs = "0";
		if (aps.equals("1")) {
			cs = aps;
		}
		return cs;
	}
	
	/**
	 * 获取届次编号
	 * @author zxy
	 * 
	 * 2018年5月24日 下午8:52:19
	 * 
	 * @return String
	 */
	public String code() {
		String code = "";
		String jpql = "select count(*) from yr_clas";
		String value = entityManager.createNativeQuery(jpql).getSingleResult().toString();
		if ("0".equals(value)) { // 如果数据中没有值 那么编号默认从 C1001 开始
			code = "1001";
		} else { // 如果数据库中有值 那么将最大code 数查出 加1 成为下一个code数
			String sql = "select max(`code`)  from yr_clas";
			String sqlCode = entityManager.createNativeQuery(sql).getSingleResult().toString();
			Integer integer = Integer.parseInt(sqlCode) + 1;
			code = String.valueOf(integer);
		}
		return code;
	}
	
	/**
	 * 修改 (获取老师Code 根据code修改老师名称)
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:47:58
	 * 
	 * @param clas 届次对象
	 */
	@Override
	public void update(Clas clas) {
		String t = entityManager.createNativeQuery(""
				+ "select `name` from yr_teacher where `code` = :tCode")
				.setParameter("tCode", clas.getTeacherCode()).getSingleResult().toString();
		Query q = entityManager.createQuery("update Clas set teacherName = :tName ,"
				+ "teacher_code = :tCode where code = :code")
				.setParameter("tName", t).setParameter("tCode", clas.getTeacherCode()).
				setParameter("code", clas.getCode());
		q.executeUpdate();
	}

	/**
	 * 删除
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:48:35
	 * 
	 * @param clas 届次对象
	 * @return 返回 Integer类型 届次不能删除
	 */
	@Override
	public Integer delete(Clas clas) {
		return 0;
	}

	/**
	 * 查询所有
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:48:49
	 * 
	 * @return 返回届次集合,以便显示
	 */
	@Override
	public PageUtil query(Integer page, Integer limit, String year) {
		PageUtil pageUtil = new PageUtil();
		try {
			int count = 0;
			String jpql = "From Clas order by startTime desc";
			if (null  != year && !"".equals(year)) {
				jpql = "from Clas where year like :year order by startTime desc";
			}
			List<Clas> studentList = new ArrayList<Clas>();
			year = pageUtil.decodeSpecialCharsWhenLikeUseSlash(year);
			if (null  != year && !"".equals(year)) {
				studentList = entityManager.createQuery(jpql)
						.setMaxResults(limit).setFirstResult((page - 1) * limit)
						.setParameter("year", "%" + year + "%").getResultList();
				count = Integer
				.parseInt(entityManager
				 .createNativeQuery("select count(*) from yr_clas where year like :year ")
				   .setParameter("year", "%" + year + "%").getSingleResult().toString());
			} else {
				studentList = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
						.setMaxResults(limit).getResultList();
				count = Integer
						.parseInt(entityManager
						   .createNativeQuery("select count(*) from yr_clas")
							  .getSingleResult().toString());
			}
			pageUtil = new PageUtil(limit, page, count);		
			pageUtil.setCount(count);
			pageUtil.setCode(0);
			pageUtil.setData(studentList);
			pageUtil.setMsg("OK");
		} catch (Exception e) {
			pageUtil.setCode(1);
			pageUtil.setMsg("---出错了!----");
			e.printStackTrace();
		}
		
		return pageUtil;
	}

	/**
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:48:57
	 * 
	 * @param id 获取页面是届次id 用来数据回显
	 * @return 返回某届次的对象
	 */
	@Override
	public Clas get(Integer id) {
		Query q = entityManager.createQuery("from Clas where id = :id").setParameter("id", id);
		Clas listUser = (Clas) q.getSingleResult();
		return listUser;
	}

	/**
	 * 毕业
	 * @author zxy
	 * 
	 * 2018年5月23日 上午9:36:50
	 * 
	 * @param code 届次code
	 */
	@Override
	public String graduation(String code) {
		try {
			Query q = entityManager.createQuery(""
					+ "update Clas set isFinish = :isFinish where code = :code")
					.setParameter("isFinish", "1")
					.setParameter("code", code);
			q.executeUpdate();
			return "succ";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 开课
	 * @author zxy
	 * 
	 * 2018年5月23日 上午10:25:22
	 * 
	 * @param code 届次
	 * @return 判断是否开课
	 */
	@Override
	public String openClss(String code) {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String curdate = simpleDateFormat.format(date);
		try {
			Query q = entityManager.createQuery(""
					+ "update Clas set startTime = :startTime where code = :code")
					.setParameter("startTime", curdate)
					.setParameter("code", code);
			q.executeUpdate();
			return "succ";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 查询指定值
	 * @author zxy
	 * 
	 * 2018年5月24日 上午10:59:49
	 * 
	 * @param year
	 * @return 查询某届下的批次
	 */
	@Override
	public String getOnly(String code) {
		String str = entityManager.createNativeQuery("select `code` from yr_clas where `code` = ?1")
				.setParameter(1, code).getSingleResult().toString();
		return str;
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
		Query q = entityManager.createQuery("from Clas where code = :code").setParameter("code", code);
		Clas listUser = (Clas) q.getSingleResult();
		return listUser;
	}

	/**
	 * 数据回显查询
	 * @author zxy
	 * 
	 * 2018年5月26日 下午7:14:15
	 * 
	 * @return
	 */
	@Override
	public List<Clas> query() {
		List<Clas> listClas = entityManager.createQuery("from Clas").getResultList();
		return listClas;
	}

	/**
	 * 详情
	 * @author zxy
	 * 
	 * 2018年5月28日 下午7:27:11
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @param code 某届批次code,用户分辨
	 * @return 分页工具类
	 */
	@Override
	public PageUtil details(Integer page, Integer limit, String name, String code) {
		PageUtil pageUtil = new PageUtil();
		try {
			int count = 0;
			String jpql = "From Student where class_code = :class_code order by inTime desc";
			if (null  != name && !"".equals(name)) {
				jpql = "from Student where name like :sName order by inTime desc";
			}
			List<Clas> studentList = new ArrayList<Clas>();
			name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
			if (null  != name && !"".equals(name) &&  null != code && !"".equals(code)) {
				studentList = entityManager.createQuery(jpql)
						.setMaxResults(limit).setFirstResult((page - 1) * limit)
						.setParameter("sName", "%" + name + "%").getResultList();
				count = Integer
				.parseInt(entityManager
				 .createNativeQuery("select count(*) from yr_student where "
				 	+ "name like :sName ,class_code = :class_code")
				   .setParameter("sName", "%" + name + "%").setParameter("class_code", code)
				   .getSingleResult().toString());
			} else {
				studentList = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
						.setMaxResults(limit).setParameter("class_code", code).getResultList();
				count = Integer
						.parseInt(entityManager
						   .createNativeQuery("select count(*) from yr_student where"
						   					+ " class_code = :class_code")
						   .setParameter("class_code", code)
						   .getSingleResult().toString());
			}
			pageUtil = new PageUtil(limit, page, count);		
			pageUtil.setCount(count);
			pageUtil.setCode(0);
			pageUtil.setData(studentList);
			pageUtil.setMsg("OK");
		} catch (Exception e) {
			pageUtil.setCode(1);
			pageUtil.setMsg("---出错了!----");
			e.printStackTrace();
		}
		
		return pageUtil;
	}
	
}
