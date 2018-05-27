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
	/**
	 * 权限id
	 */
	private Integer id;
	/**
	 * 权限信息
	 */
	private String name;
	/**
	 * 权限编号
	 */
	private String code;
	/**
	 * 权限路径
	 */
	private String url;
	/**
	 * 使用状态
	 */
	private Integer use;
	/**
	 * Shiro 操作
	 */
	private String caozuo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	 /**
     * 最后修改时间
     */
    private Date updateTime;
    //子级数据
    private String[] data;
	
	private Set<Role> perRoleItems = new HashSet<>();
	private String createTimeStr = "";
    private String updateTimeStr = "";
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
	public Integer getUse() {
		return use;
	}
	public void setUse(Integer use) {
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
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
		return "Auth [id=" + id + ", name=" + name + ", code=" + code + ", url="
				+ url + ", use=" + use + ", caozuo=" + caozuo + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	@Transient
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	
}
