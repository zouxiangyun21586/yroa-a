package com.yr.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
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
	
//	@PersistenceContext
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
		cla.setCode("使用本地sql查询max()值 + 1 进行添加");
		cla.setCreateTime(Date.valueOf(DateUtils.getCurrentTime())); // 创建时间(获取当前时间)
		cla.setTeacherCode("1001"); // 设置这批届次老师的code(获取页面上填写的老师名获取到老师code)
		cla.setTeacherName("钟林杰"); // 设置这批届次老师的code(获取页面上填写的老师名)
		cla.setFinishTime(Date.valueOf("毕业时间"));
		cla.setStartTime(Date.valueOf("开班时间"));
		cla.setIsFinish("是否毕业 1 表示已毕业");
		entityManager.persist(clas);
	}

	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:47:58
	 * 
	 * @param clas 届次对象
	 */
	public void update(Clas clas) {
		
		Clas c = entityManager.find(Clas.class, clas.getId());
		
		Integer id = clas.getId();
//		String name = clas.getName();
//		String year = clas.getYear();
//		String code = clas.getCode();
		String teacherCode = clas.getTeacherCode();
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
		cl.setTeacherCode(teacherCode);
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
	 * @return 返回 Integer类型 判断是否删除	1 表示此届次有届次(Clas届次表)不能删除  0 表示可以删除
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
	 * 查询
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:48:49
	 * 
	 * @return @return 返回届次集合
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
	
}
