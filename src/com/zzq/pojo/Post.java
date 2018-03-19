package com.zzq.pojo;

public class Post {
	private Integer postId;
	private String postName;
	private Integer depId;
	private Department department;
	public Post() {
		super();
	}
	public Post(Integer postId, String postName, Integer depId) {
		super();
		this.postId = postId;
		this.postName = postName;
		this.depId = depId;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postName=" + postName + ", depId=" + depId + ", department=" + department
				+ "]";
	}
	

}
