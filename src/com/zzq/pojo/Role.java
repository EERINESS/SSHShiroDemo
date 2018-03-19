package com.zzq.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {
	private static final long serialVersionUID = -5053191061751937571L;
	private Integer id;
	private String name;
	private String code;
	private String description;
	private Set users = new HashSet(0);
	private Set functions = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Role(Integer id, String name, String code, String description,
			Set users, Set functions) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.users = users;
		this.functions = functions;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	public Set getFunctions() {
		return this.functions;
	}

	public void setFunctions(Set functions) {
		this.functions = functions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code + ", description=" + description + ", users="
				+ users + ", functions=" + functions + "]";
	}
	
	

}
