package com.yr.entity;

/**
 * 参考数据库(搜索)
 * @author Administrator
 *
 * 2018年5月22日 上午9:01:17
 *
 */
public class Search  {
	
	private String dateStart;
	private String dateEnd;
	private String dayOf;
	private String pageNum;
	
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getDayOf() {
		return dayOf;
	}
	public void setDayOf(String dayOf) {
		this.dayOf = dayOf;
	}
	public String getPageNum() {
		return pageNum;
	}
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:02:26
	 * 
	 * @param pageNum 分页数
	 */
	public void setPageNum(String pageNum) {
		if (null == pageNum || "".equals(pageNum)) {
			this.pageNum = "1";
			return;
		}
		for (int i = 0; i < pageNum.length(); i++) {
			if (!Character.isDigit(pageNum.charAt(i))) {
				this.pageNum = "1"; //分页时传递的参数有问题时默认是第1页
				return;
			}
		}
		this.pageNum = pageNum;
	}
	@Override
	public String toString() {
		return "Search [dateStart = " + dateStart + ", dateEnd = " + dateEnd
				+ ", dayOf = " + dayOf + ", pageNum = " + pageNum
				+  "]";
	}
}
