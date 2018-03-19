package com.zzq.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzq.commons.PageBean;
import com.zzq.dao.UserDao;
import com.zzq.pojo.User;
import com.zzq.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	UserDao userDao;
	
	@Override
	public void save(User entity) {
		userDao.save(entity);
	}

	@Override
	public void delete(User entity) {
		userDao.delete(entity);
	}

	@Override
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	public void saveOrUpdate(User entity) {		
		userDao.saveOrUpdate(entity);
	}

	@Override
	public User findById(Serializable id) {	
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {		
		return userDao.findAll();
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {		
		userDao.executeUpdate(queryName, objects);
	}

	@Override
	public void pageQuery(PageBean pageBean) {		
		userDao.pageQuery(pageBean);
	}

	@Override
	public List<User> findByCriteria(DetachedCriteria detachedCriteria) {		
		return userDao.findByCriteria(detachedCriteria);
	}

	@Override
	public List<User> searchUser(User user) {
		return userDao.searchUser(user);
	}

	@Override
	public List<User> selUsersByIds(String ids) {
		return userDao.selUsersByIds(ids);
	}

	@Override
	public void saveUsers(List<User> users) {
		userDao.saveUsers(users);
	}

	

}
