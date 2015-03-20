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

	public Map<String,String> delete(int group_id);

	public boolean update(Group group);

	public Group get(String name);

	public List<Group> getList();
	
	/**
	 * 获取所用的group信息
	 * @return
	 */
	public Map<String,Integer> getGroupInfo();
	
	/**
	 *根据ID判断
	 * @param group_id
	 * @return
	 */
	public boolean isExists(int group_id);
	/**
	 * 根据groupName判断
	 * @param groupName
	 * @return
	 */
	public boolean isExists(String groupName);

	
}
