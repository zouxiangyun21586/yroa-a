package com.yr.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yr.dao.RoleDao;
import com.yr.entity.Auth;
import com.yr.entity.Role;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

import net.sf.json.JSONObject;


/**
 * 角色Dao实现类
 * @author 周业好
 * 2018年5月22日 上午10:52:12
 */
@Repository
public class RoleDaoImpl implements RoleDao {
	private static final int TWO = 2;
	private static final int THREE = 3;
	@PersistenceContext
	private EntityManager em;
	/**
	 * 添加
	 * @param users 角色对象
	 * @param authcode 权限表 查询学生的code
	 * @return 操作是否成功
	 */
	public int addId(Role users, String authcode) {
		String code = em.createNativeQuery("select max(code) from yr_role").getSingleResult().toString();
		Integer codeInt = Integer.valueOf(code);
		codeInt = codeInt + 1; //编号+1
		users.setCode(codeInt.toString());
		Auth a = (Auth) em.createQuery("from Auth a from a.code=?").setParameter(0, authcode).getSingleResult();
		users.getRolePermItems().add(a);
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
		List list = em.createNativeQuery(sql).setParameter(1, code).getResultList();
		if (null != list && list.size() > 0) { //此角色有人在使用无法停用
			return TWO;
		}
		Query qu = em.createQuery("delete from Role r where r.code=?").setParameter(0, code); //删除角色
		qu.executeUpdate();
		return 0;
	}
	/**
	 * 修改
	 * @param emp 角色对象
	 * @return 1 不存在编号, 0成功
	 */
	public int upd(Role emp) {
		Role role = (Role) em.createQuery("from Role r where r.code=?").setParameter(0, emp.getCode())
				.getSingleResult();
		if (null == role && "".equals(role)) { //判断编号是否存在
			return 1;
		}
		Query qu = em.createQuery("update Role a set a.name=?,a.info=?,a.updateTime=? where a.code=?");
		qu.setParameter(0, emp.getName());
		qu.setParameter(1, emp.getInfo());
		qu.setParameter(TWO, emp.getUpdateTime());
		qu.setParameter(THREE, emp.getCode());
		qu.executeUpdate();
		return 0;
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
		return JsonUtils.beanToJson(qu, new String[]{"roleUsersItems", "rolePermItems"}, false);
	}
	
	/**
	 * 查询角色全部信息  单个(根据账号)
	 * @param name 用户账号
	 * @return list
	 */
	public List<Role> queryR(String name) {
		List<Role> qu = em.createQuery("select r from Role r,Account ac where r.use=0 and ac.userName=?")
				.setParameter(0, name).getResultList();
		return qu;
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
     * @return 操作是否成功 1 角色不存在 ,0成功,2 已经有人在使用
     */
	@Override
	public int kaiguan(String code) {
		Role ac = (Role) em.createQuery("from Role where code=?").setParameter(0, code)
				.getSingleResult();
		if ("".equals(ac) && null == ac) { //判断账号是否存在
			return 1;
		}
		
		Integer val = 0;
		if (0 == ac.getUse()) { //请求过来是0 表示他想要停用
			val = 1;
			List list = em.createNativeQuery("select role_code from yr_account_role where role_code=?")
					.setParameter(1, ac.getCode()).getResultList();
			if (null != list && list.size() > 0) { //此角色有人在使用无法停用
				return TWO;
			}
		} else {
			val = 0;
		}
		Query qu = em.createQuery("update Role a set a.use=?,a.updateTime=? where a.code=?");
		qu.setParameter(0, val);
		qu.setParameter(1, new Date());
		qu.setParameter(TWO, code);
		qu.executeUpdate();
		return 0;
	}
	
	/**
     * 保存权限
     * @author 周业好
     * @param resourceId 选中的权限id
     * @param roleCode 角色code
     * @return json
     */
	@Override
    public String roleEmpowerment(String[] resourceId, String roleCode) {
        Map<String, Object> map = new HashMap<>();
        try {
            Query query = em
                    .createNativeQuery("DELETE FROM `yr_role_auth` WHERE `role_code`=:role_id")
                    .setParameter("role_id", roleCode);
            query.executeUpdate();
            for (int i = 0; i < resourceId.length; i++) {

                String insert = "INSERT INTO `yr_role_auth` (`role_code`, `auth_code`) VALUES (:role_id,:resource_id)";
                em.createNativeQuery(insert) .setParameter("role_id", roleCode)
                .setParameter("resource_id", resourceId[i]).executeUpdate();

            }
            Query qu = em.createQuery("update Role a set a.updateTime=? where a.code=?");
    		qu.setParameter(0, new Date());
    		qu.setParameter(1, roleCode);
    		qu.executeUpdate();
            em.flush();
            em.clear();
            map.put("code", 0);
            map.put("msg", "修改成功");
        } catch (Exception e) {
            map.put("code", 1);
            map.put("msg", "修改失败");
            e.printStackTrace();
        }
        return JSONObject.fromObject(map).toString();
    }
	
	/**
     * 权限表 查询学生的code
     * @author 周业好
     * @return code
     */
	@Override
	public String querydic() {
		return em.createNativeQuery("select val from yr_dic where keyv='stuCode'").getSingleResult().toString();
	}
}
