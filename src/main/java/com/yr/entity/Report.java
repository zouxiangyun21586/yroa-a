package com.yr.entity;

/**
 * 当天考勤报告实体
 * @author 林水桥
 * 2018年5月30日下午9:44:51
 */
public class Report {
	/**早上*/
	private String am;
	/**中午*/
	private String pm;
	/**晚上*/
	private String nt;
	
	public String getAm() {
		return am;
	}
	public void setAm(String am) {
		this.am = am;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public String getNt() {
		return nt;
	}
	public void setNt(String nt) {
		this.nt = nt;
	}
}
