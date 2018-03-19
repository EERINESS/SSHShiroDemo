package com.zzq.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.zzq.commons.BaseDaoImpl;
import com.zzq.dao.StaffDao;
import com.zzq.pojo.City;
import com.zzq.pojo.County;
import com.zzq.pojo.Province;
import com.zzq.pojo.Staff;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {

	@Override
	public List<Province> provinceList() {
		List<Province> provinceList = (List<Province>) this.getHibernateTemplate().find("from Province");
		return provinceList;
	}

	@Override
	public List<City> cityListByPid(Integer pid) {
		String hql="from City  where p_id ="+pid+"";
		List<City> cityList = (List<City>) this.getHibernateTemplate().find(hql);
		return cityList;
	}

	@Override
	public List<County> countyListByCid(Integer cid) {
		String hql="from County  where c_id ="+cid+"";
		List<County> countyList = (List<County>) this.getHibernateTemplate().find(hql);
		return countyList;
	}

	@Override
	public List<Staff> searchStaff(Staff staff) {
		String hql="from Staff  where 1=1 ";
		if(staff.getName()!=null&&!staff.getName().equals("")) {
			hql = hql + "and name like "+"'%"+staff.getName()+"%'"+" ";
		}
		if(staff.getSex()!=null&&!staff.getSex().equals("")) {
			hql = hql + "and sex like "+"'%"+staff.getSex()+"%'"+" ";
		}
		if(staff.getProvince()!=null&&!staff.getProvince().equals("")) {
			hql = hql + "and province like "+"'%"+staff.getProvince()+"%'"+" ";
		}
		if(staff.getCity()!=null&&!staff.getCity().equals("")) {
			hql = hql + "and city like "+"'%"+staff.getCity()+"%'"+" ";
		}
		if(staff.getCounty()!=null&&!staff.getCounty().equals("")) {
			hql = hql + "and county like "+"'%"+staff.getCounty()+"%'"+" ";
		}
		List<Staff> staffList = (List<Staff>) this.getHibernateTemplate().find(hql);
		return staffList;
	}

	@Override
	public List<Staff> selStaffsByIds(String ids) {
		String hql="from Staff  where id in( "+ids+")";
		List<Staff> staffList = (List<Staff>) this.getHibernateTemplate().find(hql);
		return staffList;
	}

	@Override
	public String provinceNametById(Integer pId) {
		List<Province> provinces = (List<Province>)this.getHibernateTemplate().find("from Province where p_id=?", pId);
		if(provinces != null && provinces.size() > 0){
			System.out.println("id为"+provinces.get(0).getpId());
			return provinces.get(0).getPname();
		}
		return null;
	}

	@Override
	public String cityNameById(Integer cId) {
		List<City> citys = (List<City>)this.getHibernateTemplate().find("from City where c_id=?", cId);
		if(citys != null && citys.size() > 0){
			System.out.println("id为"+citys.get(0).getpId());
			return citys.get(0).getCname();
		}
		return null;
	}

	@Override
	public String countyNameById(Integer contyId) {
		List<County> countys = (List<County>)this.getHibernateTemplate().find("from County where county_id=?", contyId);
		if(countys != null && countys.size() > 0){
			return countys.get(0).getCountyName();
		}
		return null;
	}

	@Override
	public List<Staff> staffList() {
		// TODO Auto-generated method stub
		return (List<Staff>) this.getHibernateTemplate().find("from Staff");
	}
	


}
