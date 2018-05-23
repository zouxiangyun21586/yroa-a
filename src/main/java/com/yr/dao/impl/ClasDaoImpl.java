package com.yr.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.ClasDao;
import com.yr.entity.Clas;
import com.yr.util.DateUtils;

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
	public void add(Clas clas) {
		
		Clas cla = new Clas();
		cla.setName(clas.getName()); // 此届 批次名
		cla.setYear(String.valueOf(DateUtils.getCurrentYear())); // 当前年(当前届数)
		String strCode = (String) entityManager.createNativeQuery("select max(code) from Clas")
				.getSingleResult();
		cla.setCode(strCode); // 不需要页面传值过来,在后台算出code最大值后+1 成为要存入的code值
		cla.setCreateTime(Date.valueOf(DateUtils.getCurrentTime())); // 创建时间(获取当前时间)
		cla.setTeacherCode(clas.getTeacherCode()); // 设置这批届次老师的code(获取页面上填写的老师名获取到老师code)
		cla.setTeacherName(clas.getTeacherName()); // 设置这批届次老师的code(获取页面上填写的老师名)
		cla.setFinishTime(Date.valueOf("毕业时间"));
		cla.setStartTime(Date.valueOf("开班时间"));
		cla.setIsFinish("判断是否毕业   1 表示已毕业");
		entityManager.persist(cla);
	}

	/**
	 * 修改 (获取老师Code 根据code修改老师名称)
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:47:58
	 * 
	 * @param clas 届次对象
	 */
	public void update(Clas clas) {
		
		Clas c = entityManager.find(Clas.class, clas.getTeacherCode());
		
		Integer id = clas.getId();
//		String name = clas.getName();
//		String year = clas.getYear();
//		String code = clas.getCode();
//		String teacherCode = clas.getTeacherCode();
		String teacherName = clas.getTeacherName();
//		Date startTime = clas.getStartTime();
//		Date createTime = clas.getCreateTime();
//		String isFinish = clas.getIsFinish();
//		Date finishTime = clas.getFinishTime();
		entityManager.remove(c);
		
		Clas cl = new Clas();
		cl.setId(id);
//		cl.setName(name);
//		cl.setYear(year);
//		cl.setCode(code);
//		cl.setTeacherCode(teacherCode);
		cl.setTeacherName(teacherName);
//		cl.setStartTime(startTime);
//		cl.setCreateTime(createTime);
//		cl.setIsFinish(isFinish);
//		cl.setFinishTime(finishTime);
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
	 * @return @return 返回届次集合,以便显示
	 */
	public List<Clas> query() {
		Query q = entityManager.createQuery("from Clas");
		List<Clas> listResource = q.getResultList();
		return listResource;
	}

	/**
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:48:57
	 * 
	 *  @param id 获取页面是届次id 用来数据回显
	 * @return 返回某届次的对象
	 */
	public Clas get(Integer id) {
		Query q = entityManager.createQuery("from Clas where id = " + id);
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
	public Integer graduation(String code) {
		String str = (String) entityManager.createNativeQuery("select isFinish from Clas where code = " 
				+ code).getSingleResult();
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
	public Integer openClss(String code) {
		String str = (String) entityManager.createNativeQuery("select start_time from Clas where code = "
				+ code).getSingleResult();
		return Integer.valueOf(str);
	}
	
}
