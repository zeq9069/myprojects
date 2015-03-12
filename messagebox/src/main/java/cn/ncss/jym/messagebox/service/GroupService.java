package cn.ncss.jym.messagebox.service;

import java.util.List;
import java.util.Map;

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
	
	public Map<String,String> add(Group group);

	public Map<String,String> delete(String name);

	public boolean update(Group group);

	public Group get(String name);

	public List<Group> getList();
	
	/**
	 * 获取所用的group信息
	 * @return
	 */
	public Map<String,Integer> getGroupInfo();
	
	public boolean isExists(String name);
	
}
