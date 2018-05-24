package com.yr.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 账户实体类
 * @author 周业好
 *
 * 2018年5月22日 上午8:55:09
 *
 */
@Cacheable(true)
@Table(name = "yr_account")
@Entity
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
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date updateTime;
    
    private Set<Role> usersRoleItems = new HashSet<>();
	private String strRole = "";
	private String createTimeStr = "";
    private String updateTimeStr = "";

   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name = "username")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    @Column(name = "createTime")
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "password")
	public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    @Column(name = "id_admin")
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
	
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "yr_account_role", 
			joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"), 
			inverseJoinColumns = @JoinColumn(name = "role_code", referencedColumnName = "code"))
	public Set<Role> getUsersRoleItems() {
		return usersRoleItems;
	}
	public void setUsersRoleItems(Set<Role> usersRoleItems) {
		this.usersRoleItems = usersRoleItems;
	}
	@Transient
	public String getStrRole() {
		return strRole;
	}

	public void setStrRole(String strRole) {
		this.strRole = strRole;
	}
	/**
	 * 得到创建时间 的字符串
	 * @return 时间的字符串
	 */
	@Transient
	public String getCreateTimeStr() {
		String fmt = "yyyy-MM-dd E HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		String dateStr = sdf.format(createTimeStr);
		createTimeStr = dateStr;
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	/**
	 * 得到最后修改时间 的字符串
	 * @return 时间的字符串
	 */
	@Transient
	public String getUpdateTimeStr() {
		String fmt = "yyyy-MM-dd E HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		String dateStr = sdf.format(updateTimeStr);
		updateTimeStr = dateStr;
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
	
}
