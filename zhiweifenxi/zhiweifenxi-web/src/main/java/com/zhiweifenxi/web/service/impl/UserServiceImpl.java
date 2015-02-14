package com.zhiweifenxi.web.service.impl;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiweifenxi.web.dao.UserDao;
import com.zhiweifenxi.web.pojo.User;
import com.zhiweifenxi.web.service.UserService;

/**
 * 
 * @author kyrin
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly=false)
	public boolean regist(User user) {
		if(isExit(user)){
			return false;
		}
		
		return userDao.add(user);
	}

	@Transactional(readOnly=true)
	public boolean login(String name, String password) {
		User user=userDao.get(name);
		if(user==null){
			return false;
		}
		return user.getName().equals(password);
	}
	
	public boolean isExit(User user){
		return userDao.get(user.getName())!=null;
	}
}
