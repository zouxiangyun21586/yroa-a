package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.RoleDao;
import com.yr.entity.Role;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;


/**
 * 角色Dao实现类
 * @author 周业好
 * 2018年5月22日 上午10:52:12
 */
@Repository
public class RoleDaoImpl implements RoleDao {
	private static final int TWO = 2;
	@PersistenceContext
	private EntityManager em;
	/**
	 * 添加
	 * @param users 角色对象
	 * @return 操作是否成功
	 */
	public int addId(Role users) {
		String code = em.createNativeQuery("select max(code) from yr_role").getSingleResult().toString();
		Integer codeInt = Integer.valueOf(code);
		codeInt = codeInt + 1; //编号+1
		users.setCode(codeInt.toString());
		em.persist(users);
		return 1;
	}
	/**
	 * 删除
	 * @param code 角色编号
	 * @return 1 编号不存在,2 有人在使用此角色
	 */
	public int del(String code) {
		Role role = (Role) em.createQuery("from Role r where r.code=?").setParameter(0, code).getSingleResult();
		if (null == role && "".equals(role)) { //判断编号是否存在
			return 1;
		}
		String sql = "select role_code from yr_account_role where role_code=?";
		List list = em.createNativeQuery(sql).setParameter(0, code).getResultList();
		if (null != null && list.size() > 0) { //此角色有人在使用无法停用
			return TWO;
		}
		Query qu = em.createQuery("delete from Role r where r.code=?").setParameter(0, code); //删除角色
		qu.executeUpdate();
		return 0;
	}
	/**
	 * 修改
	 * @param emp 角色对象
	 * @return 操作是否成功
	 */
	public int upd(Role emp) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 修改密码
	 * @param id 角色id
	 * @param userN 角色
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
	 * @param code 角色编号
	 * @return 1 角色编号不存在,json 成功
	 */
	public String query(String code) {
		Role qu = (Role) em.createQuery("from Role r where r.code=?").setParameter(0, code).getSingleResult();
		if ("".equals(qu) || null == qu) { //判断账号是否存在
			return "1";
		}
		return JsonUtils.beanToJson(qu);
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
            String jpql = "FROM Role ORDER BY updateTime desc";
            if (null != name && !"".equals(name)) {
                jpql = "FROM Role where name like :name ORDER BY updateTime desc";
            }
            List<Role> list = new ArrayList<Role>();
            name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
            if (null != name && !"".equals(name)) {
                list = em.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).setParameter("name", "%" + name + "%").getResultList();
                count = Integer.parseInt(em.createNativeQuery(
                		"SELECT COUNT(*) FROM yr_role where name like :name")
                                        .setParameter("name", "%" + name + "%").getSingleResult().toString());

            } else {
                list = em.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).getResultList();
                count = Integer.parseInt(em
                       .createNativeQuery("SELECT COUNT(*) FROM yr_role").getSingleResult().toString());
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
        return JsonUtils.beanToJson(pageUtil, new String[] {"rolePermItems", "roleUsersItems" }, false);
    }
	 /**
     * 查询所有的角色
     * @author 周业好
     * @return json
     */
	@Override
	public String queryRoleAll() {
		try {
			List<Role> list = em.createQuery("from Role").getResultList();
			String json = JsonUtils.beanListToJson(list, 
					new String[] {"rolePermItems", "roleUsersItems", "createTime"}, false);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
     * 启用停用
     * @author 周业好
     * @param code 角色编号
     * @return 操作是否成功 1 角色不存在 ,0成功
     */
	@Override
	public int kaiguan(String code) {
		Role ac = (Role) em.createQuery("from Role where code=?").setParameter(0, code)
				.getSingleResult();
		if ("".equals(ac) && null == ac) { //判断账号是否存在
			return 1;
		}
		
		Integer val = 0;
		if (1 == ac.getUse()) {
			val = 0;
		} else {
			val = 1;
			List list = em.createNativeQuery("select role_code from yr_account_role where role_code=?")
					.setParameter(1, ac.getCode()).getResultList();
			if (null != null && list.size() > 0) { //此角色有人在使用无法停用
				return TWO;
			}
		}
		Query qu = em.createQuery("update Role a set a.use=?,a.updateTime=? where a.code=?");
		qu.setParameter(0, val);
		qu.setParameter(1, new Date());
		qu.setParameter(TWO, code);
		qu.executeUpdate();
		return 0;
	}
}
