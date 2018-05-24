package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.AccountDao;
import com.yr.entity.Account;
import com.yr.entity.Role;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

/**
 * 用户Dao实现类
 * @author 周业好
 * 2018年5月22日 上午10:52:12
 */
@Repository
public class AccountDaoImpl implements AccountDao {

	@PersistenceContext
	private EntityManager em;
	/**
	 * 添加
	 * @param users 用户对象
	 * @param code 角色code
	 * @return 操作是否成功
	 */
	public int addId(Account users, String code) {
		List qu = em.createQuery("select u from Account u where u.id=?").setParameter(1, users.getId())
				.getResultList();
		if (qu.size() > 0) {
			final int i = 2;
			return i;
		}
		Role r = (Role) em.createQuery("from Role r where r.code=?").setParameter(1, code).getSingleResult();
		
		users.getUsersRoleItems().add(r);
		em.persist(users);
		return 1;
	}
	/**
	 * 删除
	 * @param i 用户编号
	 * @return 是否操作成功
	 */
	public int del(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 修改
	 * @param emp 用户对象
	 * @return 操作是否成功
	 */
	public int upd(Account emp) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 修改密码
	 * @param id 账号id
	 * @param userN 账号
	 * @param oldpassword 旧密码
	 * @param passW 新密码
	 * @return 出错信息
	 */
	public String updatePass(String oldpassword, String userN, Integer id,
			String passW) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 查询单个
	 * @param i 用户id
	 * @return 查出的用户对象
	 */
	public Account query(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 班某人的分页
	 * @param page 当前页 
	 * @param limit 一页显示多少条
	 * @param name 是否使用模糊搜索
	 * @return 4
	 */
	@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
    public String getFenye(int page, int limit, String name) {
        PageUtil pageUtil = new PageUtil();
        try {
            int count = 0;
            String jpql = "FROM Account ORDER BY updateTime desc";
            if (null != name && !"".equals(name)) {
                jpql = "FROM Account where username like :username ORDER BY updateTime desc";
            }
            List<Account> list = new ArrayList<Account>();
            name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
            if (null != name && !"".equals(name)) {
                list = em.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).setParameter("username", "%" + name + "%").getResultList();
                count = Integer.parseInt(em.createNativeQuery(
                		"SELECT COUNT(*) FROM yr_account where username like :username")
                                        .setParameter("username", "%" + name + "%").getSingleResult().toString());

            } else {
                list = em.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).getResultList();
                count = Integer.parseInt(em
                       .createNativeQuery("SELECT COUNT(*) FROM yr_account").getSingleResult().toString());
            }
            pageUtil = new PageUtil(limit, page, count);
            pageUtil.setCount(count);
            pageUtil.setData(list);
            pageUtil.setCode(0);
            pageUtil.setMsg("OK");
        } catch (Exception e) {
            pageUtil.setCode(1);
            pageUtil.setMsg("-----出错啦-----");
            e.printStackTrace();
        }
        return JsonUtils.beanToJson(pageUtil, new String[] {"usersRoleItems" }, false);
    }
}
