package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.AuthDao;
import com.yr.entity.Auth;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;


/**
 * 用户Dao实现类
 * @author 周业好
 * 2018年5月22日 上午10:52:12
 */
@Repository
public class AuthDaoImpl implements AuthDao {
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	@PersistenceContext
	private EntityManager em;
	/**
	 * 添加
	 * @param users 权限对象
	 * @return 操作是否成功
	 */
	public int addId(Auth users) {
		String code = em.createNativeQuery("select max(code) from yr_auth").getSingleResult().toString();
		Integer codeInt = Integer.valueOf(code);
		codeInt = codeInt + 1; //编号+1
		users.setCode(codeInt.toString());
		em.persist(users);
		return 1;
	}
	/**
	 * 删除
	 * @param code 权限编号
	 * @return 1 编号不存在,2 有人在使用此角色
	 */
	public int del(String code) {
		Auth auth = (Auth) em.createQuery("from Auth r where r.code=?").setParameter(0, code).getSingleResult();
		if (null == auth && "".equals(auth)) { //判断编号是否存在
			return 1;
		}
		String sql = "select auth_code from yr_role_auth where auth_code=?";
		List list = em.createNativeQuery(sql).setParameter(1, code).getResultList();
		if (null != null && list.size() > 0) { //此权限有人在使用无法停用
			return TWO;
		}
		Query qu = em.createQuery("delete from Auth r where r.code=?").setParameter(0, code); //删除角色
		qu.executeUpdate();
		return 0;
	}
	/**
	 * 修改
	 * @param emp 权限对象
	 * @return 1 不存在编号, 0成功
	 */
	public int upd(Auth emp) {
		Auth auth = (Auth) em.createQuery("from Auth r where r.code=?").setParameter(0, emp.getCode())
				.getSingleResult();
		if (null == auth && "".equals(auth)) { //判断编号是否存在
			return 1;
		}
		Query q = em.createQuery("update Auth a set a.name=?,a.url=?,a.caozuo=?,a.updateTime=? where a.code=?");
		q.setParameter(0, emp.getName());
		q.setParameter(1, emp.getUrl());
		q.setParameter(TWO, emp.getCaozuo());
		q.setParameter(THREE, emp.getUpdateTime());
		q.setParameter(FOUR, emp.getCode());
		q.executeUpdate();
		return 0;
	}
	/**
	 * 查询单个
	 * @param code 权限编号
	 * @return 1 权限编号不存在,json 成功
	 */
	public String query(String code) {
		Auth qu = (Auth) em.createQuery("from Auth r where r.code=?").setParameter(0, code).getSingleResult();
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
            String jpql = "FROM Auth ORDER BY updateTime desc";
            if (null != name && !"".equals(name)) {
                jpql = "FROM Auth where name like :name ORDER BY updateTime desc";
            }
            List<Auth> list = new ArrayList<Auth>();
            name = pageUtil.decodeSpecialCharsWhenLikeUseSlash(name);
            if (null != name && !"".equals(name)) {
                list = em.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).setParameter("name", "%" + name + "%").getResultList();
                count = Integer.parseInt(em.createNativeQuery(
                		"SELECT COUNT(*) FROM yr_auth where name like :name")
                                        .setParameter("name", "%" + name + "%").getSingleResult().toString());

            } else {
                list = em.createQuery(jpql).setFirstResult((page - 1) * limit)
                        .setMaxResults(limit).getResultList();
                count = Integer.parseInt(em
                       .createNativeQuery("SELECT COUNT(*) FROM yr_auth").getSingleResult().toString());
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
        return JsonUtils.beanToJson(pageUtil, new String[] {"perRoleItems"}, false);
    }
	 /**
     * 查询所有的权限
     * @author 周业好
     * @return json
     */
	@Override
	public String queryAuthAll() {
		try {
			List<Auth> list = em.createQuery("from Auth").getResultList();
			String json = JsonUtils.beanListToJson(list, 
					new String[] {"perRoleItems", "updateTime", "createTime"}, false);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
     * 启用停用
     * @author 周业好
     * @param code 权限编号
     * @return 操作是否成功 1 角色不存在 ,0成功
     */
	@Override
	public int kaiguan(String code) {
		Auth ac = (Auth) em.createQuery("from Auth where code=?").setParameter(0, code)
				.getSingleResult();
		if ("".equals(ac) && null == ac) { //判断账号是否存在
			return 1;
		}
		
		Integer val = 0;
		if (0 == ac.getUse()) {
			val = 1;
			List list = em.createNativeQuery("select auth_code from yr_role_auth where auth_code=?")
					.setParameter(1, ac.getCode()).getResultList();
			if (null != list && list.size() > 0) { //此角色有人在使用无法停用
				return TWO;
			}
		} else {
			val = 0;
		}
		Query qu = em.createQuery("update Auth a set a.use=?,a.updateTime=? where a.code=?");
		qu.setParameter(0, val);
		qu.setParameter(1, new Date());
		qu.setParameter(TWO, code);
		qu.executeUpdate();
		return 0;
	}
	
	/**
     * 查询角色拥有的权限
     * @return list
     */
	@Override
    public List<Auth> getResource() {
        return em.createQuery("FROM Auth").getResultList();
    }
	
	/**
     * 根据角色 code 查出 对应角色的  权限name
     * @author 周业好
     * @param code 权限编号
     * @return list
     */
	@Override
    @SuppressWarnings("unchecked")
    public List<Auth> codeTogetResource(String code) {
        String sql = "select r2.name title from yr_role r1,yr_auth r2,yr_role_auth rr where r1.code=rr.role_code and"
        		+ " r2.code=rr.auth_code and r1.code=:rcode";
        return em.createNativeQuery(sql).setParameter("rcode", code).getResultList();
    }
	/**
     * 根据角色 code 查出 对应角色的 全部权限信息
     * @author 周业好
     * @param code 角色编号
     * @return list
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Auth> roleCodeTogetResource(String code) {
        String sql = "select  r2.* from yr_role r1,yr_auth r2,yr_role_auth rr where "
        		+ "r1.code=rr.role_code and r2.code=rr.auth_code and r1.code=:AuthCode";
        return em.createNativeQuery(sql, Auth.class).setParameter("AuthCode", code)
                .getResultList();
    }
    /**
     * 查出权限表全部信息
     * @author 周业好
     * @return list 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Auth> findAll() {
        String hql = "from Auth";
        return em.createQuery(hql).getResultList();
    }
}
