package cn.ncss.jym.messagebox.service;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Group;

/**
 * ***********************
 * 
 *   用户组服务层接口
 *
 * ***********************
 * @author kyrin [2015年3月11日]
 *
 */
public interface GroupService {
	
	public boolean add(Group group);

	public boolean delete(Group group);

	public boolean update(Group group);

	public Group get(String name);

	public List<Group> getList();
	
	public boolean isExists(Group group);
	
}
