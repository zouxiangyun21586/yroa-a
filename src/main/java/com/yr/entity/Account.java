package com.yr.entity;

/**
 * 账户实体类
 * @author 周业好
 *
 * 2018年5月22日 上午8:55:09
 *
 */
public class Account {
	/**
	 * 用户编号
	 */
    private Integer id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 电话
     */
    private String tel;
    /**
     * 是否是管理员
     */
    private String isAdmin;
    /**
     * 用户类型
     */
    private String type;
    /**
     * 权限
     */
    private String auth;

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIsAdmin() {
        return isAdmin;
    }
    
  
	public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin == null ? null : isAdmin.trim();
    }
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth == null ? null : auth.trim();
    }
}
