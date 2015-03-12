package cn.ncss.jym.messagebox.service;

import java.util.Map;

import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.system.pojo.SystemInfo;

/**
 * ***********************
 * 
 *  对外开放系统服务接口
 *
 * ***********************
 * @author kyrin [2015年3月12日]
 *
 */
public interface SystemService {

	/**
	 * 获取所有的统计信息（群组数量、以及每个群组的用户数、目前上线的公告数量、下线公告的数量）
	 */
	public SystemInfo getAllInfo();
	
	/**
	 * 获取所有的group群组
	 * @return
	 */
	public Map<String,String> getGroups();
	
	/**
	 * 添加group
	 * @param group 群组
	 * @return
	 */
	public Map<String, String> addGroup(Group group);
	
	/**
	 * 删除group
	 * @param group 群组实体
	 * @return
	 */
	public Map<String,String> deletGroup(String name);
	
}
