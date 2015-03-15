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
	public List<UserInfo> getUsers(int page,int pageSize);
	
	
	/**
	 * 根据群组获取用户列表
	 * @param name
	 * @return
	 */
	public List<UserInfo> getUsersByGroup(int page,int pageSize,String groupName);
	
	/**
	 * 获取所用用户数量
	 * @return
	 */
	public int getCount() ;
	
	/**
	 * 发布公告
	 * @param announ
	 * @return
	 */
	public Map<String,String> publishAnnoun(Announcement announ);
	
	
	/**
	 * 更改announ上线状态
	 * @param announ_id
	 * @param online
	 * @return
	 */
	public Map<String,String> updateOnline(int announ_id,String online);
	
	/**
	 * 获取所有公告
	 * @param online 是否上线
	 * @return
	 */
	public List<Announcement> getAnnouns(String online);
	
	/**
	 * 获取所有该分组的用户数量
	 * @param group
	 * @return
	 */
	public int getCountByGroup(String groupName);
	
	/**
	 * 添加浏览记录
	 * @param u_id
	 * @param announ_id
	 * @return
	 */
	public Map<String, String> addRecord(String u_id ,int announ_id) ;
	
	/**
	 * 获取用户
	 * @param u_id
	 * @return
	 */
	public UserInfo getUser(String u_id);
	
}
