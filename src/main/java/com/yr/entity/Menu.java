package com.yr.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
 * 菜单实体类
 * @author 周业好
 * 2018年5月31日 上午10:56:37
 */
@Cacheable(true)
@Table(name = "yr_menu")
@Entity
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单id
	 */
	private Integer id;
	/**
	 * 菜单名字
	 */
	private String name;
	/**
	 * 菜单编号
	 */
	private String code;
	/**
	 * 菜单父级编号
	 */
	private String pcode;
	/**
	 * 菜单路径
	 */
	private String url;
	/**
	 * 是否弹出
	 */
	private boolean spread;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
     * 最后修改时间
     */
    private Date updateTime;
    /**
     * 父类子级
     */
    private List<Menu> children;
    private Set<Role> menuRoleItems = new HashSet<>();
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
	
	@Column(name = "p_code")
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getSpread() {
		return spread;
	}
	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
	
	@ManyToMany(mappedBy = "roleMenuItems", fetch = FetchType.LAZY)//多对多,多维护 mappedBy="不维护(内容要和dept的那个集合一样)"
	public Set<Role> getMenuRoleItems() {
		return menuRoleItems;
	}
	public void setMenuRoleItems(Set<Role> menuRoleItems) {
		this.menuRoleItems = menuRoleItems;
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
	
	@Transient
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
    
}
