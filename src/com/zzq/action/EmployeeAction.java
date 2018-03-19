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
import com.zzq.pojo.Department;
import com.zzq.pojo.Employee;

@Controller
public class EmployeeAction extends BaseAction<Employee> {
	private static final long serialVersionUID = 9070031758178036829L;
	private Integer empid;
	private Integer[] empids;
	private Integer depid;
	private Employee employee;
	private List<Employee> employees;
	private List<Department> departments;
	
	
	@Action(value="employeeList")
	public void employeeList() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			employees = employeeService.findAll();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", employees);
			map.put("total", employees.size());
			String jsonMap = JSON.toJSONString(map);
			System.out.println("jsonMap:"+jsonMap);
			printWriter.write(jsonMap);
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			printWriter.flush();
			printWriter.close();
		}
	}
	
	@Action(value = "employeeDelete")
	public void employeeDelete() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		System.out.println("id为"+empid);
		PrintWriter out = null;
		    try {
		    	if (empid != null) {
					System.out.println("进来了单删：" + empid);
					employee = employeeService.findById(empid);
					employeeService.delete(employee);
				} else {
					System.out.println("进来了多删：" + empid);
					for (Integer id : empids) {
						employee = employeeService.findById(id);
						employeeService.delete(employee);
					}
				}
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  	out.print("success");
	}
	
	@Action(value = "employeeEdit", results = { @Result(name = "success", location = "/employeeList", type = "redirect") })
	public String userSave() {
		System.out.println("huhu");
		try {
			if (employee.getEmpId() != null) {
				employeeService.update(employee);
			} else {
				employeeService.save(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	@Action(value="departmentList")
	public void departmentList() {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		try {
			printWriter = response.getWriter();
			departments = employeeService.departmentList();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", departments);
			map.put("total", departments.size());
			String jsonMap = JSON.toJSONString(map);
			printWriter.write(jsonMap);
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			printWriter.flush();
			printWriter.close();
		}
		
	}
	
	@Action(value="employeeByDepid")
	public void employeeByDepid() {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		try {
			printWriter = response.getWriter();
			employees = employeeService.employeeByDepid(depid);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", employees);
			map.put("total", employees.size());
			String jsonMap = JSON.toJSONString(map);
			printWriter.write(jsonMap);
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			printWriter.flush();
			printWriter.close();
		}
		
	}
	
	@Action(value="departmentEntry", results= {@Result(name="success", location="/WEB-INF/views/relation/employee_list.jsp")})
	public String departmentEntry() {
		departments = employeeService.departmentList();
		System.out.println("部门有："+departments.size());
		return SUCCESS;
	}
	
	/* 
	 *Name: set get 方法
	 */
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	public Integer[] getEmpids() {
		return empids;
	}
	public void setEmpids(Integer[] empids) {
		this.empids = empids;
	}

	public Integer getDepid() {
		return depid;
	}

	public void setDepid(Integer depid) {
		this.depid = depid;
	}
	
}
