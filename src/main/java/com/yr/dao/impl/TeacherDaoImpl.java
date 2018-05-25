package com.yr.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.TeacherDao;
import com.yr.entity.Clas;
import com.yr.entity.Teacher;
import com.yr.util.AgeUtils;
import com.yr.util.PageUtil;

/**
 * 老师的 Dao 实现类(jpa)
 * 
 * @author zxy
 *
 */
@Repository
public class TeacherDaoImpl implements TeacherDao {

	@PersistenceContext
	private EntityManager entityManager;
	final Integer number = 2;

	/**
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:55:59
	 * 
	 * @param teacher 老师对象
	 * @throws Exception 
	 */
	public void add(Teacher teacher) {
		
		try {
		Teacher tch = new Teacher();
		tch.setName(teacher.getName());
		String strCode = code();
		tch.setCode(strCode); // 需加1
		tch.setSex(teacher.getSex());
		tch.setBirth(teacher.getBirth());
		tch.setAge(AgeUtils.birthTime(teacher.getBirth()));
		tch.setTel(teacher.getTel());
		tch.setAddr(teacher.getAddr());
		tch.setInTime(teacher.getInTime());
		tch.setLevel(levelMothed(teacher.getLevel()));
		tch.setInfo(teacher.getInfo());
		tch.setIsLeave("");
		tch.setCreateTime(new Date());
		tch.setTeacherAccount(teacher.getTeacherAccount());
		entityManager.persist(tch); // 老师code  age需要算
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 教学等级
	 * @author zxy
	 * 
	 * 2018年5月25日 下午5:44:37
	 * 
	 * @param level 等级参数
	 * @return 教师等级
	 */
	public static String levelMothed(String level) {
		String lev = "";
		if (level.equals("UI")) {
			lev = level;
		} else if (level.equals("DBA")) {
			lev = level;
		} else if (level.equals("DEVELE")) {
			lev = level;
		} else if (level.equals("DEVMID")) {
			lev = level;
		} else if (level.equals("DEVHIG")) {
			lev = level;
		}
		return lev;
	}
	
	/**
	 * 获取届次编号
	 * @author zxy
	 * 
	 * 2018年5月24日 下午8:54:03
	 * 
	 * @return String
	 */
	public String code() {
		String code = "";
		String jpql = "select count(*) from yr_teacher";
		String value = entityManager.createNativeQuery(jpql).getSingleResult().toString();
		if ("0".equals(value)) { // 如果数据中没有值 那么编号默认从 C1001 开始
			code = "T1001";
		} else { // 如果数据库中有值 那么将最大code 数查出 加1 成为下一个code数
			String sql = "select max(`code`)  from yr_teacher";
			String sqlCode = entityManager.createNativeQuery(sql).getSingleResult().toString();
			Integer integer = Integer.valueOf(sqlCode.substring(1)) + 1;
			code = "T" + integer;
		}
		return code;
	}
	
	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:56:02
	 * 
	 * @param teacher 老师对象
	 */
	public void update(Teacher teacher) {
		
		Teacher t = entityManager.find(Teacher.class, teacher.getId());
		
		Integer id = teacher.getId();
		String name = teacher.getName();
		String sex = teacher.getSex();
		Integer age = teacher.getAge();
		String tel = teacher.getTel();
		String addr = teacher.getAddr();
		String birth = teacher.getBirth();
		String inTime = teacher.getInTime();
		String level = teacher.getLevel();
		String isleaver = teacher.getIsLeave();
		Date leavetime  = teacher.getLeaveTime();
		String info = teacher.getInfo();
		Date createTime = teacher.getCreateTime();
		entityManager.remove(t);
		
		Teacher tc = new Teacher();
		tc.setId(id);
		tc.setName(name);
		tc.setSex(sex);
		tc.setAge(age);
		tc.setTel(tel);
		tc.setAddr(addr);
		tc.setInTime(inTime);
		tc.setLevel(level);
		tc.setIsLeave(isleaver);
		tc.setLeaveTime(leavetime);
		tc.setInfo(info);
		tc.setBirth(birth);
		tc.setCreateTime(createTime);
		entityManager.merge(tc);
		
	}

	/**
	 * 删除
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:56:18
	 * 
	 * @param teacher 老师对象
	 * @return 返回 Integer类型 判断是否删除	1 表示此老师有届次(Clas届次表)不能删除  0 表示可以删除
	 */
	public Integer delete(Teacher teacher) {
		Query query = entityManager.createNativeQuery("select count(*) from yr_clas where teacher_code = :code")
				.setParameter("code", teacher.getCode());
		BigInteger big = (BigInteger) query.getSingleResult();
		int uid = big.intValue();
		if (query != null && uid != 0) { // 如果届次表中有数据那么不能进行删除老师
			return 1;
		} else {
			Query qu = entityManager.createNativeQuery("delete from yr_teacher where code = ?1")
					.setParameter(1, teacher.getCode());
			qu.executeUpdate();
			return number;
		}
	}

	/**
	 * 分页查询
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:57:16
	 * 
	 * @return 返回老师集合
	 */
	@Override
	public PageUtil query(Integer page, Integer limit, String name) {
		PageUtil pageUtil = new PageUtil();
		try {
			int count = 0;
			String jpql = "From Teacher order by in_time desc";
			if (null  != name && !"".equals(name)) {
				jpql = "from Teacher where year like :year order by in_time desc";
			}
			List<Clas> studentList = new ArrayList<Clas>();
			name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
			if (null  != name && !"".equals(name)) {
				studentList = entityManager.createQuery(jpql)
						.setMaxResults(limit).setFirstResult((page - 1) * limit)
						.setParameter("name", "%" + name + "%").getResultList();
				count = Integer
				.parseInt(entityManager
				 .createNativeQuery("select count(*) from yr_teacher where name like :name ")
				   .setParameter("name", "%" + name + "%").getSingleResult().toString());
			} else {
				studentList = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
						.setMaxResults(limit).getResultList();
				count = Integer
						.parseInt(entityManager
						   .createNativeQuery("select count(*) from yr_teacher")
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
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:57:29
	 * 
	 * @param code 获取页面是老师code 用来数据回显
	 * @return 返回某老师的对象
	 */
	public Teacher get(String code) {
		Query q = entityManager.createQuery("from Teacher where code = :code").setParameter("code", code);
		Teacher listUser = (Teacher) q.getSingleResult();
		return listUser;
	}

}
