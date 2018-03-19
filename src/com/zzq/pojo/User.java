package com.zzq.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;


public class User implements Serializable {
	private static final long serialVersionUID = 4635268701113530007L;
	private Integer id;
	private String username;
	private String password;
	@DateTimeFormat(pattern="yyyy-MM-ddHH:mm:ss") 
	private Date birthday;
	private String sex;
	private String tel;
	private String place;
	private Set<Role> roles = new HashSet<Role>();


	public String getRoleNames() {
		String names = "";
		for (Role role : roles) {
			names += role.getName() + " ";
		}
		return names;
	}
	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public User(Integer id, String username, String password, Date birthday, String sex, String tel, String place,
			Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.sex = sex;
		this.tel = tel;
		this.place = place;
		this.roles = roles;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", birthday=" + birthday
				+ ", sex=" + sex + ", tel=" + tel + ", place=" + place + ", roles=" + roles + "]";
	}

	
	
	

}
