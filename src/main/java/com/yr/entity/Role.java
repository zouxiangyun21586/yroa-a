package com.yr.entity;

import java.util.Date;

/**
 * 角色表实体类
 * @author 周业好
 * 2018年5月23日 下午7:23:07
 */
public class Role {
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

	public Integer getUse() {
		return use;
	}

	public void setUse(Integer use) {
		this.use = use;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
}
