package com.zhiweifenxi.web.dao;

import com.zhiweifenxi.web.pojo.User;

/**
 * 
 * @author kyrin
 *
 */
public interface UserDao {
	public boolean add(User user);
	public User get(String name );
}
