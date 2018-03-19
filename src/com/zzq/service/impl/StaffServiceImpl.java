package com.zzq.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzq.commons.PageBean;
import com.zzq.dao.StaffDao;
import com.zzq.pojo.City;
import com.zzq.pojo.County;
import com.zzq.pojo.Province;
import com.zzq.pojo.Staff;
import com.zzq.service.StaffService;
@Transactional
@Service
public class StaffServiceImpl implements StaffService {
	@Resource
	private StaffDao staffDao;

	@Override
	public void save(Staff entity) {
		staffDao.save(entity);
	}

	@Override
	public void delete(Staff entity) {
		staffDao.delete(entity);
	}

	@Override
	public void update(Staff entity) {
		staffDao.update(entity);
	}

	@Override
	public void saveOrUpdate(Staff entity) {
		staffDao.saveOrUpdate(entity);
	}

	@Override
	public Staff findById(Serializable id) {
		return staffDao.findById(id);
	}

	@Override
	public List<Staff> findAll() {
		return staffDao.findAll();
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		staffDao.executeUpdate(queryName, objects);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	@Override
	public List<Staff> findByCriteria(DetachedCriteria detachedCriteria) {
		return staffDao.findByCriteria(detachedCriteria);
	}

	@Override
	public List<Province> provinceList() {
		return staffDao.provinceList();
	}

	@Override
	public List<City> cityListByPid(Integer pid) {
		return staffDao.cityListByPid(pid);
	}

	@Override
	public List<County> countyListByCid(Integer cid) {
		return staffDao.countyListByCid(cid);
	}

	@Override
	public List<Staff> searchStaff(Staff staff) {
		return staffDao.searchStaff(staff);
	}

	@Override
	public List<Staff> selStaffsByIds(String ids) {
		return staffDao.selStaffsByIds(ids);
	}


	@Override
	public String provinceNametById(Integer pId) {
		return staffDao.provinceNametById(pId);
	}

	@Override
	public String cityNameById(Integer cId) {
		return staffDao.cityNameById(cId);
	}

	@Override
	public String countyNameById(Integer contyId) {
		return staffDao.countyNameById(contyId);
	}

	@Override
	public List<Staff> staffList() {
		return staffDao.staffList();
	}

}
