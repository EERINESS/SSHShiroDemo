package com.zzq.pojo;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1579882286018897725L;
	private Integer empId;
	private String empName;
	private String empSex;
	private String empAge;
	private Integer depId;//所属部门id
	private Wages wages;//工资表
	private Department department;//所属部门
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}

	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	public Wages getWages() {
		return wages;
	}
	public void setWages(Wages wages) {
		this.wages = wages;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Employee() {
		super();
	}
	public Employee(Integer empId, String empName, String empSex, String empAge, Integer depId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSex = empSex;
		this.empAge = empAge;
		this.depId = depId;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSex=" + empSex + ", empAge=" + empAge
				+ ", depId=" + depId + ", wages=" + wages + ", department=" + department + "]";
	}
	
	
}
