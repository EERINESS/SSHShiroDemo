package com.zzq.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.zzq.commons.BaseAction;
import com.zzq.commons.UploadFile;
import com.zzq.pojo.User;

@Controller
public class UserAction extends BaseAction<User> {
	private static final long serialVersionUID = -8527216080929976680L;

	private Integer ided;
	private Integer[] ids;
	private User user;
	private List<User> users;
	
	/*上传需要的参数*/
	
	//File对象，目的是获取页面上传的文件
	private File uploadFile;
	//文件名，要和你下載文件名一致，否則無法找到文件
	private String fileName;
	private InputStream excelFile;
	private String uploadFileFileName;
	//要下載文件的目錄
	private String inputPath;
	private String contentType;
	//下載文件的輸入流，直接返回給前臺，会以文件形式下载
	private InputStream ipts;

	
	
	@Action(value = "userList")
	public void userList() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			if(user != null) {
				users = userService.searchUser(user);
			}else {
				users = userService.findAll();
			}
			Map<String, Object> map = new HashMap<>();
			map.put("rows", users);
			map.put("total", users.size());
			System.out.println("after");
			String jsonMap = JSON.toJSONString(map);
			System.out.println("jsonMap："+jsonMap);
			pw = response.getWriter();
			pw.write(jsonMap);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Action(value = "userAdd", results = { @Result(name = "success", location = "/userList", type = "redirect") })
	public String userSave() {
		System.out.println("huhu");
		userService.save(user);
		return SUCCESS;
	}


	@Action(value = "userUpdate", results = { @Result(name = "success", location = "/userList", type = "redirect") })
	public String userUpdate() {
		System.out.println(user.toString());
		userService.update(user);
		return SUCCESS;
	}

	@Action(value = "userDelete")
	public void userDelete() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		System.out.println("id为"+ided);
		PrintWriter out = null;
		    try {
		    	if (ided != null) {
					System.out.println("进来了单删：" + ided);
					user = userService.findById(ided);
					System.out.println(user.toString());
					userService.delete(user);
				} else {
					System.out.println("进来了多删：" + ids);
					for (Integer id : ids) {
						user = userService.findById(id);
						userService.delete(user);
					}
				}
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  	out.print("success");
	}
	
	@Action(value = "userExportt")
	public void userExportt() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		System.out.println("huhu");
		if(ids != null) {
			String str1=StringUtils.join(ids, ",");
			users = userService.selUsersByIds(str1);
		}else if(user != null) {
			System.out.println("用户值为："+user.toString());
			users = userService.searchUser(user);
		}
		System.out.println("用户信息："+users);
		//方法一：导出的数据中可以有空值
				HSSFWorkbook wb=new HSSFWorkbook();
				//表格下面的名称
				HSSFSheet sheet=wb.createSheet("用户列表");
				//每列的宽度
				sheet.setDefaultColumnWidth((short) 17);
				HSSFCellStyle style=wb.createCellStyle();
				HSSFRow row=sheet.createRow(0);
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				
				HSSFCell cell=row.createCell((short) 0);
				cell.setCellValue("ID");
				cell.setCellStyle(style);
				cell=row.createCell((short) 1);
				cell.setCellValue("用户名");
				cell.setCellStyle(style);
				cell=row.createCell((short) 2);
				cell.setCellValue("密码");
				cell.setCellStyle(style);
				cell=row.createCell((short) 3);
				cell.setCellValue("生日");
				cell.setCellStyle(style);
				cell=row.createCell((short) 4);
				cell.setCellValue("性别");
				cell.setCellStyle(style);
				cell=row.createCell((short) 5);
				cell.setCellValue("电话");
				cell.setCellStyle(style);
				cell=row.createCell((short) 6);
				cell.setCellValue("来自");
				cell.setCellStyle(style);
				
				for(short i=0;i<users.size();i++){
					row=sheet.createRow(i+1);
					row.createCell(0).setCellValue(users.get(i).getId());
					if(users.get(i).getUsername()!=null&&!users.get(i).getUsername().equals("")) {
						row.createCell(1).setCellValue(users.get(i).getUsername());
					}else {
						row.createCell(1).setCellValue(null+"");
					}
					
					if(users.get(i).getPassword()!=null&&!users.get(i).getPassword().equals("")) {
						row.createCell(2).setCellValue(users.get(i).getPassword());
					}else {
						row.createCell(2).setCellValue(null+"");
					}
					
					if(users.get(i).getBirthday()!=null&&!users.get(i).getBirthday().equals("")) {
						String time=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(users.get(i).getBirthday());
						row.createCell(3).setCellValue(time);
					}else {
						row.createCell(3).setCellValue(null+"");
					}
					
					if(users.get(i).getSex()!=null&&!users.get(i).getSex().equals("")) {
						row.createCell(4).setCellValue(users.get(i).getSex());
					}else {
						row.createCell(4).setCellValue(null+"");
					}
					
					if(users.get(i).getTel()!=null&&!users.get(i).getTel().equals("")) {
						row.createCell(5).setCellValue(users.get(i).getTel());
					}else {
						row.createCell(5).setCellValue(null+"");
					}

					if(users.get(i).getPlace()!=null&&!users.get(i).getPlace().equals("")) {
						row.createCell(6).setCellValue(users.get(i).getPlace()+"");
					}else {
						row.createCell(6).setCellValue(null+"");
					}
					
				}
				
				//用当前时间给文件创建名称
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String DateName = "用户表";
				
				//获取项目中存放Excel文件的xls文件夹的绝对路径
				String contextPath = request.getSession().getServletContext().getRealPath("/xls/");
				contextPath = contextPath.replace("\\", "/");
				
				//下载到项目文件夹中完整路径和文件名称
				String fileName=contextPath+DateName+".xls";
				
				try {
					//文件创建的路径地址和名称
					FileOutputStream out=new FileOutputStream(fileName);
					wb.write(out);
					wb.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String fName=DateName+".xls";
				System.out.println("导出表："+fName);
				String data = JSON.toJSONString(fName);
				PrintWriter pw = null;
				try {
					pw = response.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pw.write(data);
				pw.flush();
				pw.close();
			}
	
	/*
	 * Name: 下载模板
	 */
	@Action(value="downloadTemplate",params= {"inputPath","/xls"},
			results={@Result(name="success",type="stream",
			params= {
					"contentType","application/octet-stream",
					"inputName","ipts",
					"contentDisposition","attachment;filename=\"${fileName}\"",
					"bufferSize","4096"})})
	public String ckDown(){
		return SUCCESS;
	}
	
	
	/*导入数据*/
	@Action(value="userImport",results= {@Result(location="views_user_user_list", type = "redirect")})
	public String userImport() throws Exception {
		System.out.println("上传文件名："+uploadFile);
		String directory = "/file";
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
		File target = UploadFile.Upload(uploadFile, uploadFileFileName,targetDirectory);
		List<User> userlist = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			
		excelFile = new FileInputStream(target);
		// 初始化一个工作簿
		Workbook workBook = new XSSFWorkbook(excelFile);
		// 第一张表单
		Sheet sheet = workBook.getSheetAt(0);
		int rowNum = sheet.getLastRowNum() + 1;
		for (int i = 1; i < rowNum; i++) {
			User u = new User();
			Row row = sheet.getRow(i);
			int cellNum = row.getLastCellNum();
			for (int j = 0; j < cellNum; j++) {
				Cell cell = row.getCell(j);
				String cellValue = null;
				switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
					case 0:
						cellValue = String
								.valueOf((int) cell.getNumericCellValue());
						break;
					case 1:
						cellValue = cell.getStringCellValue();
						break;
					case 2:
						cellValue = cell.getStringCellValue();
						break;
					case 3:
						cellValue = cell.getStringCellValue();
						break;
					case 4:
						cellValue = cell.getStringCellValue();
						break;
					case 5:
						cellValue = cell.getStringCellValue();
						break;
					case 6:
						cellValue = cell.getStringCellValue();
						break;
				}
				switch (j) {// 通过列数来判断对应插如的字段
					case 0:
						u.setId(null);
						break;
					case 1:
						u.setUsername(cellValue);
						break;
					case 2:
						u.setPassword(cellValue);
						break;
					case 3:
						u.setBirthday(format.parse(cellValue));
						break;
					case 4:
						u.setSex(cellValue);
						break;
					case 5:
						u.setTel(cellValue);
						break;
					case 6:
						u.setPlace(cellValue);
						break;
				}
			}
			System.out.println("导入的user数据有："+u.toString());
			userlist.add(u);
		}
		userService.saveUsers(userlist);
		} catch (Exception e) {
			e.printStackTrace();
			}
		return SUCCESS;
	}

	/* 
	 *Name: set get 方法
	 */
	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}


	public Integer getIded() {
		return ided;
	}

	public void setIded(Integer ided) {
		this.ided = ided;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStream getIpts() throws FileNotFoundException {
		String path = ServletActionContext.getServletContext().getRealPath(inputPath);
		FileInputStream fis = new FileInputStream(path+"\\"+fileName);
		return new BufferedInputStream(fis);
	}

	public void setIpts(InputStream ipts) {
		this.ipts = ipts;
	}
			
	
}
