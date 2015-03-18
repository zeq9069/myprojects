package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group_announ;
import cn.ncss.jym.messagebox.pojo.Record;

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

	public int add(Announcement announ);

	public boolean addGroup_announ(List<Group_announ> lists);

	public boolean delete(Announcement announ);

	public boolean update(Announcement announ);

	public boolean updateOnline(int announ_id, String online);

	public Announcement get(int id);

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

	/**
	 * 获取公告发布的用户组
	 * @param announ
	 * @return
	 */
	public List<Group_announ> getGroups(Announcement announ);

	public List<Group_announ> getGroupsOfAnnoun(Announcement announ);

	public List<Record> getAnnounByViews(Announcement announ);

}
