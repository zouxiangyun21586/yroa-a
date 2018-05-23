package com.yr.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 学生实体类
 * @author Administrator
 *
 * 2018年5月22日 上午9:00:36
 *
 */
public class Student {
	private Integer id;
	
	//学生姓名
	private String name;
	
	//学生编号
	private String code;

	//届次
	private String year;
	
	//所属批次编码
	private String classCode;
	
	//性别
	private String sex;
	
	//出生年月日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	
	//年龄  age的值不需要存。在列表显示时不显示出生年月日，而是用当前时间－出生年月日得出年龄显示在列表中。
	private Integer age;
	
	//学生电话
	private String tel;

	//家庭住址
	private String addr;

	//家长电话
	private String homeTel;
	
	//在校学生是否展示 1代表展示,其他代表不展示
	private String isInPublish;
	
	//在校照片路径
	private String inImgUrl;
	
	//入学时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inTime;
	
	//编入学生信息时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	//毕业时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finishTime;

	//入职日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date offerTime;
	
	//工资
	private String offerIncome;
	
	//是否毕业
	private String isFinish;
	
	//该毕业生是否展示 1代表展示,其他代表不展示
	private String isFinishPublishl;
	
	
	public String getIsInPublish() {
		return isInPublish;
	}

	public void setIsInPublish(String isInPublish) {
		this.isInPublish = isInPublish;
	}

	public String getInImgUrl() {
		return inImgUrl;
	}

	public void setInImgUrl(String inImgUrl) {
		this.inImgUrl = inImgUrl;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public String getIsFinishPublishl() {
		return isFinishPublishl;
	}

	public void setIsFinishPublishl(String isFinishPublishl) {
		this.isFinishPublishl = isFinishPublishl;
	}

	public String getFinishImgUrl() {
		return finishImgUrl;
	}

	public void setFinishImgUrl(String finishImgUrl) {
		this.finishImgUrl = finishImgUrl;
	}

	//就业照片路径
	private String finishImgUrl;
	
	
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
		this.name = name == null ? null : name.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode == null ? null : classCode.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}
	
	public Date getBirth() {
		return birth;
	}


    @DateTimeFormat(pattern = "yyyy-MM-dd")  
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel == null ? null : homeTel.trim();
	}

	public Date getInTime() {
		return inTime;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(Date offerTime) {
		this.offerTime = offerTime;
	}

	public String getOfferIncome() {
		return offerIncome;
	}

	public void setOfferIncome(String offerIncome) {
		this.offerIncome = offerIncome;
	}
}
