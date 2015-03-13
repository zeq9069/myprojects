package cn.ncss.jym.messagebox.service;

import java.util.List;
import java.util.Map;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.UserInfo;
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
	
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<UserInfo> getUsers();
	
	/**
	 * 发布公告
	 * @param announ
	 * @return
	 */
	public Map<String,String> publishAnnoun(Announcement announ);
	
	/**
	 * 获取所有公告
	 * @param online 是否上线
	 * @return
	 */
	public List<Announcement> getAnnouns(String online);
	
}
