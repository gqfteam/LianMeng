package com.hkd.lianmeng.model;

import java.io.Serializable;
import java.util.Date;

/**
 * �û���Ϣ
 * @author Administrator
 *
 */

public class ShowAllUserInfoView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ��ͼӳ��
	 */
	private int id;
	private String realName;
	private String nickName;
	private int age;
	private String sex;
	private String headImg;
	private int userType;
	private String studentNum;
	private String phone;
	private String  personalNote;
	private String collegeName; 
	private Date registerTime;
	private String className;
	private String academicName;
	private String gradeName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonalNote() {
		return personalNote;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAcademicName() {
		return academicName;
	}

	public void setAcademicName(String academicName) {
		this.academicName = academicName;
	}

	

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public void setPersonalNote(String personalNote) {
		this.personalNote = personalNote;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}



	
	
	
	
	
	


}
