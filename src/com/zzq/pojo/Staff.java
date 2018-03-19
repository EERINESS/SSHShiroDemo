package com.zzq.pojo;

import java.io.Serializable;

public class Staff implements Serializable {
	private static final long serialVersionUID = -6500842124968024454L;
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
	private String phone;
	private String province;//所在省
	private String city;//所在市
	private String county;//所在县
	private String pname;
	private String cname;
	private String coname;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}

	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getConame() {
		return coname;
	}
	public void setConame(String coname) {
		this.coname = coname;
	}
	public Staff() {
		super();
	}
	
	public Staff(Integer id, String name, Integer age, String sex, String phone, String province, String city,
			String county, String pname, String cname, String coname) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.county = county;
		this.pname = pname;
		this.cname = cname;
		this.coname = coname;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", phone=" + phone
				+ ", province=" + province + ", city=" + city + ", county=" + county + ", pname=" + pname + ", cname="
				+ cname + ", coname=" + coname + "]";
	}
	
	
	

}
