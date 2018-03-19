package com.zzq.dao;

import java.util.List;

import com.zzq.commons.IBaseDao;
import com.zzq.pojo.User;

public interface UserDao extends IBaseDao<User> {
	//模糊查询用户列表
	List<User> searchUser(User user);
	
	//根据id数组查询用户
	List<User> selUsersByIds(String ids);
	
	//导入批量保存
	void saveUsers(List<User> users);
}
