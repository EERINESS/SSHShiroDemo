package com.zzq.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzq.commons.PageBean;
import com.zzq.dao.EmployeeDao;
import com.zzq.pojo.Department;
import com.zzq.pojo.Employee;
import com.zzq.service.EmployeeService;
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Resource
	private EmployeeDao employeeDao;

	@Override
	public void save(Employee entity) {
		employeeDao.save(entity);
	}

	@Override
	public void delete(Employee entity) {
		employeeDao.delete(entity);
	}

	@Override
	public void update(Employee entity) {
		employeeDao.update(entity);
	}

	@Override
	public void saveOrUpdate(Employee entity) {
		employeeDao.saveOrUpdate(entity);
	}

	@Override
	public Employee findById(Serializable id) {
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		employeeDao.executeUpdate(queryName, objects);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		employeeDao.pageQuery(pageBean);
	}

	@Override
	public List<Employee> findByCriteria(DetachedCriteria detachedCriteria) {
		return employeeDao.findByCriteria(detachedCriteria);
	}

	@Override
	public List<Department> departmentList() {
		return employeeDao.departmentList();
	}

	@Override
	public List<Employee> employeeByDepid(Integer depId) {
		return employeeDao.employeeByDepid(depId);
	}

}
