package com.zzq.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.zzq.commons.BaseAction;
import com.zzq.pojo.City;
import com.zzq.pojo.County;
import com.zzq.pojo.Province;
import com.zzq.pojo.Staff;

@Controller
public class StaffAction extends BaseAction<Staff> {
	private static final long serialVersionUID = 769732421705365574L;
	private Integer staffid;
	private Integer[] ids;
	private Staff staff;
	private List<Staff> staffs;
	private Integer cityid;
	private Integer provinceid;
	private List<Province> provinces;//所有省份
	private List<City> citys;
	private List<County> countys;
	
	@Action(value="staffList")
	public void  staffList() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = null;
		try {
			if(staff != null) {
				staffs = staffService.searchStaff(staff);
				for (Staff sta : staffs) {
					sta.setPname(staffService.provinceNametById(Integer.valueOf(sta.getProvince())));
					sta.setCname(staffService.cityNameById(Integer.valueOf(sta.getCity())));
					sta.setConame(staffService.countyNameById(Integer.valueOf(sta.getCounty())));
				}
			}else {
				staffs = staffService.staffList();
				for (Staff sta : staffs) {
					sta.setPname(staffService.provinceNametById(Integer.valueOf(sta.getProvince())));
					sta.setCname(staffService.cityNameById(Integer.valueOf(sta.getCity())));
					sta.setConame(staffService.countyNameById(Integer.valueOf(sta.getCounty())));
				}
			}
			Map<String, Object> map = new HashMap<>();
			map.put("rows", staffs);
			map.put("total", staffs.size());
			String jsonMap = JSON.toJSONString(map);
			System.out.println("JSON:"+jsonMap);
			pw = response.getWriter();
			pw.write(jsonMap);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	@Action(value = "staffEdit", results = { @Result(name = "success", location = "/staffList", type = "redirect") })
	public String userSave() {
		System.out.println("huhu");
		if (staff.getId() != null) {
			staffService.update(staff);
		} else {
			staffService.save(staff);
		}
		return SUCCESS;
	}

	/**
	 * 删除员工
	 */
	@Action(value = "staffDelete")
	public void staffDelete() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		System.out.println("id为"+staffid);
		PrintWriter out = null;
		    try {
		    	if (staffid != null) {
					System.out.println("进来了单删：" + staffid);
					staff = staffService.findById(staffid);
					staffService.delete(staff);
				} else {
					System.out.println("进来了多删：" + ids);
					for (Integer id : ids) {
						staff = staffService.findById(id);
						staffService.delete(staff);
					}
				}
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  	out.print("success");
	}
	
	@Action(value="provinceList", results= {@Result(name="success", location="/WEB-INF/views/staff/staff_list.jsp")})
	public String provinceList() {
		provinces = staffService.provinceList();
		System.out.println("省份有："+provinces.size());
		return SUCCESS;
	}
	
	/**
	 * 查询对应省份的城市
	 * 
	 * @return
	 * @throws IOException 
	 */
	@Action(value="cityList")
	public void queryCity() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		//防止乱码
		response.setCharacterEncoding("UTF-8");
		System.out.println("省份id为："+provinceid);
		citys = staffService.cityListByPid(provinceid);
		//list转换为json
		//JSONArray json = JSONArray.fromObject(cityList);
		String json = JSON.toJSONString(citys);
		System.out.println("json城市数据："+json);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		
	}
	/**
	 * 查询对应城市的城镇
	 * 
	 * @return
	 * @throws IOException 
	 */
	@Action(value="countyList")
	public void queryTown() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		countys = staffService.countyListByCid(cityid);
		String json = JSON.toJSONString(countys);
		System.out.println("json县镇数据："+json);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	
	

	/* 
	 *Name: set get 方法
	 */
	

	public Integer[] getIds() {
		return ids;
	}

	public Integer getStaffid() {
		return staffid;
	}


	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}


	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public Integer getCityid() {
		return cityid;
	}


	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}


	public Integer getProvinceid() {
		return provinceid;
	}


	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}


	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public List<City> getCitys() {
		return citys;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}

	public List<County> getCountys() {
		return countys;
	}

	public void setCountys(List<County> countys) {
		this.countys = countys;
	}

}
