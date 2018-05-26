package com.yr.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 权限表
 * @author 周业好
 * 2018年5月23日 下午8:54:02
 */
@Cacheable(true)
@Table(name = "yr_auth")
@Entity
public class Auth implements Serializable {
	private Integer id;
	private String name;
	private String code;
	private String url;
	private String use;
	private String caozuo;
	private Date createTime;
	private Set<Role> perRoleItems = new HashSet<>();
	private boolean checked;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "is_use")
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getCaozuo() {
		return caozuo;
	}
	public void setCaozuo(String caozuo) {
		this.caozuo = caozuo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@ManyToMany(mappedBy = "rolePermItems", fetch = FetchType.LAZY)//多对多,多维护 mappedBy="不维护(内容要和dept的那个集合一样)"
	public Set<Role> getPerRoleItems() {
		return perRoleItems;
	}
	public void setPerRoleItems(Set<Role> perRoleItems) {
		this.perRoleItems = perRoleItems;
	}
	
	@Transient
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
