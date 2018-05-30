package com.yr.shiro;

import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.yr.dao.impl.AccountDaoImpl;
import com.yr.dao.impl.AuthDaoImpl;
import com.yr.dao.impl.RoleDaoImpl;
import com.yr.entity.Role;
/**
 *  实现认证和授权功能
 *自定义的realm，作用从数据库查询数据，并返回数据库认证的信息
 */
public class BosRealm extends AuthorizingRealm {
	private static final int YLES = 1024;
	/**
	 * ehcache缓存
	 * @author 周业好
	 * @param authenticationCacheName 缓存的名字
	 */
    //注入ehcache的缓存区域
    @Value("BosShiroCache") //注入缓存具体对象的名字,该名字在ehcache.xml中配置的
    public void setSuperAuthenticationCacheName(String authenticationCacheName) {
        super.setAuthenticationCacheName(authenticationCacheName);
    }
    
 // 设置realm的名称  
    @Override  
    public void setName(String name) {  
        super.setName("customRealm");  
    }  
     
    @Autowired
    private AccountDaoImpl userDao;

    //注入角色dao
    @Autowired
    private RoleDaoImpl roleDao;

    //注入功能(权限)的dao
    @Autowired
    private AuthDaoImpl functionDao;

    
  //认证:回调，认证管理器会将认证令牌放到这里（action层的令牌AuthenticationToken）
    //发现如果返回null，抛出用户不存在的异常UnknownAccountException
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户名密码令牌（action传过来）
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String userName = upToken.getUsername(); //得到登录的账号134238905020 ajfklasjdkl
        //调用业务层来查询(根据用户名来查询用户，无需密码)
        //判断用户是否存在
		String zhi = userDao.yanUs(userName); //判断账号是否存在
        if (zhi == null) {
            //用户不存在6
            return null; //抛出异常
        } else {
            //用户名存在
            //参数1：用户对象，将来要放入session,数据库查询出来的用户
            //参数2：凭证（密码）：密码校验：校验的动作交给shiro
            //参数3:当前使用的Realm在Spring容器中的名字(bean的名字，自动在spring容器中寻找)
        	String ps = userDao.yanPs(userName);
        	//AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(adminUser, 
//        	password,ByteSource.Util.bytes(salt), this.getName());
        	AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, ps, this.getName());
            return authenticationInfo; //密码校验失败，会自动抛出IncorrectCredentialsException
            //加密
        }
    	
    }
    
    
    //授权方法：获取用户的权限信息
    //授权:回调方法
    //如果返回null，说明没有权限，shiro会自动跳到<property name="unauthorizedUrl" value="/unauthorized.jsp" />
    //如果不返回null，根据配置/page_base_subarea.action = roles["weihu"]，去自动匹配
    //给授权提供数据的
    /**
     * 授权方法：获取用户的权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //给当前用户授权的权限（功能权限、角色）
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()  
        String currentUsername = (String) super.getAvailablePrincipal(principals);
        //两种方式：
        //方式1：工具类来获取(首长-)
//        Users user=(Users)SecurityUtils.getSubject().getPrincipal();
        //方式2：通过参数获取首长(推荐)
//        Users user = (Users) principals.getPrimaryPrincipal();
        String usName = userDao.yanUs(currentUsername);
        //实际：需要根据当前用户的角色和功能权限来构建一个授权信息对象，交给安全管理器
        if (!currentUsername.equals(usName)) { //判断当前账户是否存在
            throw new AuthenticationException("msg:用户不存在。");
        }
        List<Role> roleList = roleDao.queryR(currentUsername);
        if (roleList != null && roleList.size() > 0) {
            for (Role role : roleList) {
                authorizationInfo.addRole(role.getName());
                //导航查询,获取某角色的拥有的功能权限
                //查询出所有的功能权限，给认证对象
            }
            List<String> functionList = functionDao.queryPermOne(currentUsername);
//    					authorizationInfo.addStringPermissions(functionList);
            for (int i = 0; i < functionList.size(); i++) {
            	authorizationInfo.addStringPermission(functionList.get(i));
            }
        }
//        }

        return authorizationInfo;  //将授权信息交给安全管理器接口。
//        return null;
    }
    
   /**
    * 将数据库查询的密码加密
    * @author 周业好
    * @param password 要加盐的密码
    * @param salts 加密盐
    * @return 加盐(密)后的密码
    */
	public static String getPassword(String password, String salts) {
	    String saltSource = salts;
        
        String hashAlgorithmName = "MD5";
        String credentials = password;
        Object salt = new Md5Hash(saltSource);
        int hashIterations = YLES;
                
        String result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
	    
	    return result;
	}

    
    /**
     * 初始化给页面输入的密码加盐   使用请   init-method="setCredentialMatcher"
     */
    public void setCredentialMatcher() {
		HashedCredentialsMatcher  credentialsMatcher = new HashedCredentialsMatcher();
		
		credentialsMatcher.setHashAlgorithmName("MD5");
		
		credentialsMatcher.setHashIterations(YLES);
		
		setCredentialsMatcher(credentialsMatcher);
	}

}
