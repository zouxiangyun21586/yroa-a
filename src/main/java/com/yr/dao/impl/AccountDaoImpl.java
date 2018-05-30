package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.AccountDao;
import com.yr.entity.Account;
import com.yr.entity.Role;
import com.yr.util.EncryptUtils;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;


/**
 * 用户Dao实现类
 * @author 周业好
 * 2018年5月22日 上午10:52:12
 */
@Repository
public class AccountDaoImpl implements AccountDao {
	private static final int TWO = 2;
	@PersistenceContext
	private EntityManager em;
	/**
	 * 添加
	 * @param users 用户对象
	 * @param code 角色code
	 * @return 操作是否成功
	 */
	public int addId(Account users, String code) {
		try {
			String breaMi = EncryptUtils.encryptBASE64(users.getPassWord().getBytes()); //BASE64位加密
			String mdFiveMi = EncryptUtils.encryptToMD5(breaMi); //密文再次 MD5加密
			users.setCreateTime(new Date()); //添加开始时间
			users.setUpdateTime(new Date()); //添加最后修改时间
			users.setPassWord(mdFiveMi);
			users.setStatus("1");
			if ("否".equals(users.getIsAdmin())) {
				users.setIsAdmin("false");
			} else {
				users.setIsAdmin("true");
			}
			List qu = em.createQuery("select u from Account u where u.userName=?")
					.setParameter(0, users.getUserName()).getResultList();
			if (qu.size() > 0) {
				final int i = 2;
				return i;
			}
			Role r = (Role) em.createQuery("from Role r where r.code=?")
					.setParameter(0, code).getSingleResult();
			users.getUsersRoleItems().add(r);
			em.persist(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	/**
	 * 删除
	 * @param name 用户账号
	 */
	public void del(String name) {
		//解除与角色的关系
		Query jie = em.createNativeQuery("delete from yr_account_role where userName=?").setParameter(1, name);
		jie.executeUpdate();
		Query qu = em.createQuery("delete from Account r where r.userName=?").setParameter(0, name); //删除账户
		qu.executeUpdate();
	}
	/**
	 * 修改 
	 * @param emp 用户对象
	 * @return 操作是否成功
	 */
	public int upd(Account emp) {
		emp.setUpdateTime(new Date());
		Query qu = em.createQuery("update Account a set a.tel=?,a.updateTime=? where a.userName=?");
		qu.setParameter(0, emp.getTel());
		qu.setParameter(1, emp.getUpdateTime());
		qu.setParameter(TWO, emp.getUserName());
		qu.executeUpdate();
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
	 * @param i 用户账号
	 * @return 查出的用户对象
	 */
	public Account query(String i) {
		Account acc = (Account) em.createQuery("from Account a where a.userName=?").setParameter(0, i)
				.getSingleResult();
		return acc;
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
                jpql = "FROM Account where userName like :userName ORDER BY updateTime desc";
            }
            List<Account> list = new ArrayList<Account>();
            name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
            if (null != name && !"".equals(name)) {
                list = em.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).setParameter("userName", "%" + name + "%").getResultList();
                count = Integer.parseInt(em.createNativeQuery(
                		"SELECT COUNT(*) FROM yr_account where userName like :userName")
                                        .setParameter("userName", "%" + name + "%").getSingleResult().toString());

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
	 
	/**
     * 重置密码
     * @author 周业好
     * @param name 账号
     * @param newPass 新密码
     * @return json
     */
	@Override
	public String resetPassWord(String name, String newPass) {
		Query qu = em.createQuery("update Account a set a.password = ?,a.updateTime=? where a.userName=?");
		
		qu.setParameter(0, newPass);
		qu.setParameter(1, new Date());
		qu.setParameter(TWO, name);
		qu.executeUpdate();
		return "1";
	}
	 /**
     * 启用停用
     * @author 周业好
     * @param name 账号
     * @return 操作是否成功
     */
	@Override
	public int kaiguan(String name) {
		Account ac = (Account) em.createQuery("select a from Account a where a.userName=?")
				.setParameter(0, name)
				.getSingleResult();
		if ("".equals(ac) && null == ac) {
			return 1;
		}
		String val = "";
		if ("1".equals(ac.getStatus())) {
			val = "0";
		} else {
			val = "1";
		}
		Query qu = em.createQuery("update Account a set a.status=?,a.updateTime=? where a.userName=?");
		qu.setParameter(0, val);
		qu.setParameter(1, new Date());
		qu.setParameter(TWO, name);
		qu.executeUpdate();
		return 0;
	}
	
	/**
	 * 判断用户是否存在
	 * @param name 账号
	 * @return null 不存在 , 1存在
	 */
	public String yanUs(String name) {
		//验证用户
		Query qu = em.createQuery("select u from Account u where u.userName=? and u.status=0");
		qu.setParameter(0, name);
		Account user = (Account) qu.getSingleResult();
		if (null == user) {
			return null;
		} else {
			return user.getUserName();
		}
	}
	
	/**
	 * 根据用户名查询密码
	 * @param us 账号
	 * @return 密码
	 */
	public String yanPs(String us) {
		Account list1 = (Account) em.createQuery("select u from Account u where u.userName=?")
				.setParameter(0, us).getSingleResult();
		if (null == list1) {
			return null;
		} else {
			return list1.getPassWord();
		}
	}
}
