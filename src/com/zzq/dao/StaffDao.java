package com.zzq.dao;

import java.util.List;

import com.zzq.commons.IBaseDao;
import com.zzq.pojo.City;
import com.zzq.pojo.County;
import com.zzq.pojo.Province;
import com.zzq.pojo.Staff;

public interface StaffDao extends IBaseDao<Staff> {
	//查询所有员工信息
	List<Staff> staffList();
	//查询所有省
	List<Province> provinceList();
	//根据省查询市
	List<City> cityListByPid(Integer pid);
	//根据市查询县
	List<County> countyListByCid(Integer cid);
	//模糊查询用户列表
	List<Staff> searchStaff(Staff staff);
	//根据id数组查询用户
	List<Staff> selStaffsByIds(String ids);
	//根据省 id 查询省 name
	String provinceNametById(Integer pId);
	//根据市 id 查询市 name
	String cityNameById(Integer cId);
	//根据县 id 查询县 name
	String countyNameById(Integer contyId);
	
}
