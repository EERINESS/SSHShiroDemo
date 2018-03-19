package com.zzq.pojo;

public class Wages {
	private Integer wagesId;//工资id
	private String salary;//工资
	private Employee employee;//员工
	
	public Integer getWagesId() {
		return wagesId;
	}
	public void setWagesId(Integer wagesId) {
		this.wagesId = wagesId;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Wages() {
		super();
	}
	public Wages(Integer wagesId, String salary) {
		super();
		this.wagesId = wagesId;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Wages [wagesId=" + wagesId + ", salary=" + salary + ", employee=" + employee + "]";
	}
	

}
