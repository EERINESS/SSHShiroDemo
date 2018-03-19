package com.zzq.service;

import java.util.List;

import com.zzq.commons.IBaseService;
import com.zzq.pojo.Department;
import com.zzq.pojo.Employee;

public interface EmployeeService extends IBaseService<Employee> {
	//查询所有部门
	List<Department> departmentList();
	//根据部门id查询该部门下的所有员工
	List<Employee> employeeByDepid(Integer depId);
}
