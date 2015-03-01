package com.zhiweifenxi.web.dao;

import java.util.List;

import com.zhiweifenxi.web.pojo.Job;

/**
 *职位 
 * @author kyrin
 */
public interface JobDao {

	public void add(Job job);
	
	public void add(List<Job> list);
	
	public boolean delete(Job job);
	
	public boolean delete(String url);
	
	public boolean update(Job job);
	
	public Job get(String url);
	
}
