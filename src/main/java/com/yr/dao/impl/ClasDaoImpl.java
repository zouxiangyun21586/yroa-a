package com.yr.dao.impl;

import java.util.Date;
import java.util.ArrayList;
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
	 */
	@Override
	public void add(Clas clas) {
		Clas cla = new Clas();
		cla.setName(clas.getName()); // 此届 批次名
		cla.setYear(String.valueOf(DateUtils.getCurrentYear())); // 当前年(当前届数)
		String strCode = code(); // 获取code数
		cla.setCode(strCode);
		cla.setCreateTime(new Date()); // 创建时间(获取当前时间)
		cla.setTeacherCode(clas.getTeacherCode()); // 设置这批届次老师的code(获取页面上填写的老师code)
		String strName = (String) entityManager
				.createNativeQuery("select teacher_name from yr_clas where teacher_code = ?1")
				.setParameter(1, clas.getTeacherCode()).getSingleResult();
		cla.setTeacherName(strName); // 设置这批届次老师的名字(获取页面上填写的老师code获取到老师名字)
		cla.setStartTime(clas.getStartTime());
		entityManager.persist(cla);
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
			code = "C1001";
		} else { // 如果数据库中有值 那么将最大code 数查出 加1 成为下一个code数
			String sql = "select max(`code`)  from yr_clas";
			String sqlCode = entityManager.createNativeQuery(sql).getSingleResult().toString();
			Integer integer = Integer.valueOf(sqlCode.substring(1)) + 1;
			code = "C" + integer;
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
		
		Clas c = entityManager.find(Clas.class, clas.getTeacherCode());
		
		Integer id = clas.getId();
		String teacherName = clas.getTeacherName();
		entityManager.remove(c);
		
		Clas cl = new Clas();
		cl.setId(id);
		cl.setTeacherName(teacherName);
		entityManager.merge(cl);
		
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
//		Query query = entityManager.createNativeQuery("select count(*) from Clas where Clas_code ="
//				+ clas.getCode());
//		BigInteger big = (BigInteger) query.getSingleResult();
//		int uid = big.intValue();
//		if (query != null && uid != 0) { // 如果届次表中有数据那么不能进行删除届次
//			return 1;
//		} else {
//			Query qu = entityManager.createNativeQuery("delete from Clas where id ="
//					+ clas.getId());
//			qu.executeUpdate();
//			return number;
//		}
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
	 *  @param code 获取页面是届次code 用来数据回显
	 * @return 返回某届次的对象
	 */
	@Override
	public Clas get(String code) {
		Query q = entityManager.createQuery("from Clas where code = :code").setParameter("code", code);
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
	 * @return Integer 判断是否毕业  1表示毕业 其余表示未毕业
	 */
	@Override
	public Integer graduation(String code) {
		String str = (String) entityManager.createNativeQuery("select isFinish from yr_clas where code = :code")
				.setParameter("code", code).getSingleResult();
		return Integer.valueOf(str);
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
	public Integer openClss(String code) {
		String str = (String) entityManager.createNativeQuery("select start_time from yr_clas where code = ?1")
				.setParameter(1, code).getSingleResult();
		return Integer.valueOf(str);
	}

	/**
	 * 查询指定值
	 * @author zxy
	 * 
	 * 2018年5月24日 上午10:59:49
	 * 
	 * @param year
	 * @return 查询某届下的所有批次
	 */
	@Override
	public List<Clas> getOnly(String year) {
		List<Clas> listClas = entityManager.createNativeQuery("select * from yr_clas where year = ?1")
				.setParameter(1, year).getResultList();
		return listClas;
	}

	@Override
	public List<Clas> query() {
		List<Clas> listClas = entityManager.createQuery("from Clas").getResultList();
		return listClas;
	}
	
}
