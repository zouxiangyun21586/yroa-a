package com.yr.shiro;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yr.dao.impl.AuthDaoImpl;
import com.yr.entity.Auth;

/**
 * shiro权限获取类
 * @author 周业好
 * 2018年5月27日 上午11:09:00
 */
public class FilterChainDefinitionMapBuilder {
    
    @Autowired
    private AuthDaoImpl authDao;
    /**
     * 得到所有的权限信息
     * @author 周业好 
     * @return list
     */
    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        List<Auth> auths = authDao.findAll();
        map.put("/css/**", " anon");
        map.put("/js/**", " anon");
        map.put("/layui/**", "anon");
        map.put("/images/**", "anon");
        map.put("/log/loginYan*", "anon");
        map.put("/ImageServlet", "anon");
        for (Auth auth : auths) {
            map.put(auth.getUrl(), auth.getCaozuo());
        }
        map.put("/**", "authc");
        return map;
        
//        List<Auth> auths = authDao.findAll();
//        for (Auth auth : auths) {
//            map.put(auth.getUrl(), auth.getCaozuo());
//        }
//        map.put("js/**", "anon");
//        map.put("css/**", "anon");
//        map.put("layui/**", "anon");
//        map.put("/testSql/out", "logout");
//        map.put("/testLogin/**", "anon");
//        map.put("/testSql/testUaRegistered", "anon");
//        map.put("/testSql/addsRegistered", "anon");
//        map.put("/testSql/testAll", "anon");
//        map.put("/login.jsp", "anon");
//        map.put("/**", "authc");
//        map.put("/testSql/adds", "perms[testSql/adds]");
//        return map;  
    }  
}
