package com.yr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.yr.util.AgeUtils;

/**
 * 学生实体类
 * @author Administrator
 *
 * 2018年5月22日 上午9:00:36
 *
 */
@Entity
@Table(name = "yr_student")
public class Student {
	
	private Integer id;
	
	//学生姓名
	private String name;
	
	//学生账号
	private String account;
	
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
	private String birth;
	
	//年龄  age的值不需要存。在列表显示时不显示出生年月日，而是用当前时间－出生年月日得出年龄显示在列表中。
	
	private Integer age;
	
	//学生电话
	private String tel;

	//家庭住址
	private String addr;

	//家长电话
	private String homeTel;
	
	//是否展示该学生  1代表展示,其他代表不展示
	private String isItDisplayed;
	
	//在校照片路径
	private String inImgUrl;
	//入学时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String inTime;
	
	//编入学生信息时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	//毕业时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String finishTime;

	//入职日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String offerTime;
	
	//工资
	private String offerIncome;
	
	//是否毕业
	private String isFinish;
	

	@Column(name = "in_img_url") 
	public String getInImgUrl() {
		return inImgUrl;
	}

	public void setInImgUrl(String inImgUrl) {
		this.inImgUrl = inImgUrl;
	}

	@Column(name = "is_finish") 
	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}
	
	@Column(name = "finish_img_url") 
	public String getFinishImgUrl() {
		return finishImgUrl;
	}
	
	public void setFinishImgUrl(String finishImgUrl) {
		this.finishImgUrl = finishImgUrl;
	}

	//就业照片路径
	private String finishImgUrl;
	
	@GeneratedValue
	@Id
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
		this.code = code == null ? null : code.trim();
	}
	@Column(name = "class_code") 
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
	
	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}
	@Transient
	public Integer getAge() {
		return AgeUtils.birthTime(birth);
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Column(name = "home_tel") 
	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	
	@Column(name = "in_time") 
	public String getInTime() {
		return inTime;
	}
	
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	@Column(name = "create_time") 
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", account="
				+ account + ", code=" + code + ", year=" + year
				+ ", classCode=" + classCode + ", sex=" + sex 
				+ ", birth=" + birth + ", age=" + age + ", tel=" + tel
				+ ", addr=" + addr + ", homeTel=" + homeTel 
				+ ", isItDisplayed=" + isItDisplayed + ", inImgUrl="
				+ inImgUrl + ", inTime=" + inTime + ", createTime=" 
				+ createTime + ", finishTime=" + finishTime
				+ ", offerTime=" + offerTime + ", offerIncome=" 
				+ offerIncome + ", isFinish=" + isFinish
				+ ", finishImgUrl=" + finishImgUrl + "]";
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
	
	@Column(name = "finish_time") 
	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	@Column(name = "offer_time") 
	public String getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(String offerTime) {
		this.offerTime = offerTime;
	}
	
	@Column(name = "offer_income") 
	public String getOfferIncome() {
		return offerIncome;
	}

	public void setOfferIncome(String offerIncome) {
		this.offerIncome = offerIncome;
	}
	
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name = "is_it_displayed") 
	public String getIsItDisplayed() {
		return isItDisplayed;
	}

	public void setIsItDisplayed(String isItDisplayed) {
		this.isItDisplayed = isItDisplayed;
	}
}
