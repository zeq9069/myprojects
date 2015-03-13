package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Announcement;

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

	public boolean add(Announcement announ);

	public boolean delete(Announcement announ);

	public boolean update(Announcement announ);

	public Announcement get(String id);

	public List<Announcement> getListByType(String type);

	public List<Announcement> getListByType(String type, boolean online);

	public List<Announcement> getListByUser(String publisher);

	public List<Announcement> getListByUser(String publisher, boolean online);
	
	/**
	 * 根据online的状态获取相应的数量，null时时总的数量
	 * @param status online的状态
	 * @return
	 */
	public List<Announcement> getByStatus(String status);

}
