package cn.ncss.jym.messagebox.service;

import java.util.List;
import java.util.Map;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.UserInfo;

/**
 * ***********************
 * 
 *   公告服务层接口
 *
 * ***********************
 * @author kyrin [2015年3月11日]
 *
 */
public interface AnnouncementService {

	public Map<String, String> add(Announcement announ, List<Group> groups);

	public boolean delete(Announcement announ);

	public boolean update(Announcement announ);

	public Map<String, String> updateOnline(int announ_id, String online);

	public Announcement get(int id);

	public List<Announcement> getListByType(String type);

	public List<Announcement> getListByType(String type, boolean online);

	public List<Announcement> getListByUser(String publisher);

	public List<Announcement> getListByUser(String publisher, boolean online);

	public List<Announcement> getListByOnline(String online);

	public Map<String, Integer> getAnnounInfo();

	public List<Group> getGroupsOfAnnoun(Announcement announ);

	public List<UserInfo> getAnnounByViews(Announcement announ);

}
