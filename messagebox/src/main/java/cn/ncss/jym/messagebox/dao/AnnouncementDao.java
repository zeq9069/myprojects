package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.UserInfo;

/**
 * *************************
 * 
 *      公告持久层
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
public interface AnnouncementDao {
	
	public int create(Announcement announ);

	public boolean delete(Announcement announ);

	public boolean update(Announcement announ);

	public Announcement get(int id);

	/**
	 * 获取用户发布的公告列表
	 * @param userInfo
	 * @param currrentIndex
	 * @param pageSize
	 * @return
	 */
	public List<Announcement> getListByUser(UserInfo userInfo,int currentIndex,int pageSize);
	
	/**
	 * 获取用户发布公告的数量
	 * @param publisherId 发布者ID
	 * @return
	 */
	public long getCountByUser(UserInfo userInfo);
	
}
