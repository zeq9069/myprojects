package com.zhiweifenxi.web.service;

import com.zhiweifenxi.web.pojo.User;

/**
 * 
 * @author kyrin
 *
 */
public interface UserService {

	public boolean regist(User user);
	public boolean login(String name,String password);
	public boolean isExit(User user);
	
}
