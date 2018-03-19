package com.zzq.pojo;

import java.io.Serializable;
import java.util.Set;

public class Department implements Serializable {
	private static final long serialVersionUID = 9195107543054983174L;
	private Integer depId;
	private String depName;
	private Integer postId;
	private Set<Post> post;
	private Set<Employee> employee;
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Set<Post> getPost() {
		return post;
	}
	public void setPost(Set<Post> post) {
		this.post = post;
	}
	public Set<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
	public Department() {
		super();
	}
	public Department(Integer depId, String depName, Integer postId, Set<Post> post, Set<Employee> employee) {
		super();
		this.depId = depId;
		this.depName = depName;
		this.postId = postId;
		this.post = post;
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Department [depId=" + depId + ", depName=" + depName + ", postId=" + postId + ", post=" + post
				+ ", employee=" + employee + "]";
	}
	
	
	

}
