package com.zzq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zzq.commons.BaseDaoImpl;
import com.zzq.dao.EmployeeDao;
import com.zzq.pojo.Department;
import com.zzq.pojo.Employee;
@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

	@Override
	public List<Department> departmentList() {
		return (List<Department>)this.getHibernateTemplate().find("from Department");
	}

	@Override
	public List<Employee> employeeByDepid(Integer depId) {
		// TODO Auto-generated method stub
		return (List<Employee>)this.getHibernateTemplate().find("from Employee where dep_id=?",depId);
	}


}
