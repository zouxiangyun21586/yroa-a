package com.yr.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
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
 * 角色表实体类
 * @author 周业好
 * 2018年5月23日 下午7:23:07
 */
@Cacheable(true)
@Table(name = "yr_role")
@Entity
public class Role implements Serializable {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 角色名
	 */
	private String name;
	/**
	 * 角色唯一编号
	 */
	private String code;
	/**
	 * 角色描述
	 */
	private String info;
	/**
	 * 是否激活0 激活,1 未激活
	 */
	private Integer use;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
     * 最后修改时间
     */
    private Date updateTime;
	private Set<Auth> rolePermItems = new HashSet<>();
	private Set<Account> roleUsersItems = new HashSet<>();
	
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	@Column(name = "is_use")
	public Integer getUse() {
		return use;
	}
	public void setUse(Integer use) {
		this.use = use;
	}
	@Column(name = "create_time")
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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "yr_role_auth",
	joinColumns = @JoinColumn(name = "role_code", referencedColumnName = "code"),
	inverseJoinColumns = @JoinColumn(name = "auth_code", referencedColumnName = "code")//
	)
	public Set<Auth> getRolePermItems() {
		return rolePermItems;
	}
	public void setRolePermItems(Set<Auth> rolePermItems) {
		this.rolePermItems = rolePermItems;
	}
	@ManyToMany(mappedBy = "usersRoleItems")//多对多,多维护 mappedBy="不维护(内容要和dept的那个集合一样)"
	public Set<Account> getRoleUsersItems() {
		return roleUsersItems;
	}
	public void setRoleUsersItems(Set<Account> roleUsersItems) {
		this.roleUsersItems = roleUsersItems;
	}
	
	/**
	 * 得到创建时间 的字符串
	 * @return 时间的字符串
	 */
	@Transient
	public String getCreateTimeStr() {
		String fmt = "yyyy-MM-dd E HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		String dateStr = sdf.format(createTime);
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
		String dateStr = sdf.format(updateTime);
		updateTimeStr = dateStr;
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code
				+ ", info=" + info + ", use=" + use + ", createTime="
				+ createTime + "]";
	}
}
