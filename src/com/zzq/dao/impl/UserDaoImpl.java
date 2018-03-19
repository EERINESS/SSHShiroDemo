package com.zzq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zzq.commons.BaseDaoImpl;
import com.zzq.dao.UserDao;
import com.zzq.pojo.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> searchUser(User user) {
		String hql="from User  where 1=1 ";
		if(user.getUsername()!=null&&!user.getUsername().equals("")) {
			hql = hql + "and username like "+"'%"+user.getUsername()+"%'"+" ";
		}
		if(user.getBirthday()!=null) {
			hql = hql + "and birthday like "+"'%"+user.getBirthday()+"%'"+" ";
		}
		if(user.getSex()!=null&&!user.getSex().equals("")) {
			hql = hql + "and sex like "+"'%"+user.getSex()+"%'"+" ";
		}
		if(user.getPlace()!=null&&!user.getPlace().equals("")) {
			hql = hql + "and place like "+"'%"+user.getPlace()+"%'"+" ";
		}
		List<User> userList = (List<User>) this.getHibernateTemplate().find(hql);
		return userList;
	}

	@Override
	public List<User> selUsersByIds(String ids) {
		String hql="from User  where id in( "+ids+")";
		List<User> userList = (List<User>) this.getHibernateTemplate().find(hql);
		return userList;
	}

	@Override
	public void saveUsers(List<User> users) {
		try {
			if(users.size()>0) {
				for(int i=0;i<users.size();i++) {
					User u = new User();
					u = users.get(i);
					this.getHibernateTemplate().save(u);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	

}
