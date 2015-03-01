package com.zhiweifenxi.web.dao;

import com.zhiweifenxi.web.pojo.User;

/**
 * 
 * @author kyrin
 *
 */
public interface UserDao {
	public boolean add(User user);
	public boolean delete(User user);
	public boolean delById(String id);
	public boolean delByName(String name);
	public boolean update(User user);
	
	public User getByName(String name );
	public User getById(String id);
}
