package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.HoliDao;
import com.yr.entity.Holiday;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

/**
 * 假期DAO
 * @作者 林水桥
 * 2018年5月23日上午8:42:00
 */
@Repository("holiDaoImpl")
public class HoliDaoImpl implements HoliDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 添加假期
	 * @作者 林水桥
	 * @param holiday 假期实体类
	 * @return Integer 添加ID
	 * 2018年5月23日下午7:57:59
	 */
	public Integer add(Holiday holiday) {
		entityManager.persist(holiday);
		return holiday.getId();
	}
	
	/**
	 * 根据ID删除假期
	 * @param id   假期ID
	 * @return     0为未删除
	 * Integer
	 * @作者 林水桥2018年5月25日上午10:02:37
	 */
	public Integer delete(Integer id) {
		
		Integer delete = entityManager.createQuery("delete from Holiday where id = :id")
				.setParameter("id", id).executeUpdate();
		
		return delete;
	}
	
	/**
	 * 分页查询假期
	 * @param page    当前页
	 * @param limit   每页多少条数据
	 * @param classCode	      是否使用模糊查询
	 * @return	String 返回json类型	  
	 * String
	 * @作者 林水桥2018年5月24日上午10:37:23
	 */
	public String getHoliday(int page, int limit, String classCode) {
        PageUtil pageUtil = new PageUtil();
        try {
            int count = 0;
            String jpql = "FROM Holiday ORDER BY updateTime desc";
            if (null != classCode && !"".equals(classCode)) {
                jpql = "FROM Holiday where classCode like :classCode ORDER BY updateTime desc";
            }
            List<Holiday> list = new ArrayList<Holiday>();
            List<Holiday> list1 = new ArrayList<Holiday>();
            classCode = pageUtil.decodeSpecialCharsWhenLikeUseSlash(classCode);
            if (null != classCode && !"".equals(classCode)) {
                list = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).setParameter("classCode", "%" + classCode + "%").getResultList();
                count = Integer.parseInt(entityManager.createNativeQuery(
                		"SELECT COUNT(*) FROM yr_holiday where class_code like :classCode")
                                        .setParameter("classCode", "%" + classCode + "%").getSingleResult().toString());
            } else {
                list = entityManager.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).getResultList();
                count = Integer.parseInt(entityManager
                       .createNativeQuery("SELECT COUNT(*) FROM yr_holiday").getSingleResult().toString());
            }
            for (Holiday holiday : list) {
				if (1 == holiday.getStatus()) {
					holiday.setStatusName("已发布");
				} else {
					holiday.setStatusName("未发布");
				}
				list1.add(holiday);
			}
            pageUtil = new PageUtil(limit, page, count);
            pageUtil.setCount(count);
            pageUtil.setData(list1);
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
	 * 修改假期
	 * @作者 林水桥
	 * @param holiday 假期实体类
	 * @return Integer 返回1为修改成功，0为修改失败
	 * 2018年5月24日上午9:16:37
	 */
	public Integer update(Holiday holiday) {
		Query query = entityManager.createQuery("update Holiday ho set ho.name = :name"
						+ ", ho.startDate = :startDate, ho.endDate = :endDate"
						+ ", ho.startTime = :startTime"
						+ ", ho.endTime = :endTime, ho.info = :info"
						+ ", ho.updateTime = :updateTime, ho.classCode = :classCode"
						+ ", ho.className = :className where ho.id = :id")
						.setParameter("name", holiday.getName())
						.setParameter("startDate", holiday.getStartDate())
						.setParameter("endDate", holiday.getEndDate())
						.setParameter("startTime", holiday.getStartTime())
						.setParameter("endTime", holiday.getEndTime())
						.setParameter("info", holiday.getInfo())
						.setParameter("updateTime", holiday.getUpdateTime())
						.setParameter("classCode", holiday.getClassCode())
						.setParameter("className", holiday.getClassName())
						.setParameter("id", holiday.getId());
		
		return query.executeUpdate();
	}
	
	/**
	 * 发布假期
	 * @author 林水桥
	 * @param holiday    假期数据
	 * @return Integer 返回发布状态 0为发布成功
	 * 2018年5月28日上午11:22:15
	 */
	public Integer release(Holiday holiday) {
		Query query = entityManager.createQuery("update Holiday set status=:status,"
					  + "releaseTime=:releaseTime,updateTime=:updateTime where id=:id")
					  .setParameter("status", holiday.getStatus())
					  .setParameter("releaseTime", holiday.getReleaseTime())
					  .setParameter("updateTime", holiday.getUpdateTime())
					  .setParameter("id", holiday.getId());
		return query.executeUpdate();
	}
	
	/**
	 * 数据回显
	 * @作者 林水桥
	 * @param id 根据假期ID回显数据
	 * @return Holiday 返回假期对象数据  null为无此数据
	 * 2018年5月24日上午10:03:28
	 */
	public Holiday get(Integer id) {
		Holiday holiday = new Holiday();
		try {
			holiday = (Holiday) entityManager.createQuery("from Holiday ho where ho.id = :id")
					.setParameter("id", id).getSingleResult();
			if (1 == holiday.getStatus()) {
				holiday.setStatusName("已发布");
			} else {
				holiday.setStatusName("未发布");
			}
		} catch (Exception e) {
			holiday = null;
		}
		return holiday;
	}
	
	/**
	 * 根据届次编码，假期状态，发布时间 查询假期
	 * @author 林水桥
	 * @param clasCode 届次编码
	 * @return Holiday 假期数据
	 * 2018年5月27日上午10:27:34
	 */
	public Holiday getDuty(String clasCode) {
		Holiday holiday = new Holiday();
		try {
			holiday = (Holiday) entityManager.createQuery("from Holiday where status=:status and "
					+ "classCode=:classCode order by releaseTime desc")
					.setParameter("status", 1)
					.setParameter("classCode", clasCode)
					.setFirstResult(0)
					.setMaxResults(1)
					.getSingleResult();
		} catch (Exception e) {
			holiday = null;
		}
		return holiday;
	}
	
}
