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

	public void add(Announcement announ);

	public void delete(Announcement announ);

	public void update(Announcement announ);

	public Announcement get(String id);

	public List<Announcement> getListByType(String type);

	public List<Announcement> getListByType(String type, boolean online);

	public List<Announcement> getListByUser(String publisher);

	public List<Announcement> getListByUser(String publisher, boolean online);

}
