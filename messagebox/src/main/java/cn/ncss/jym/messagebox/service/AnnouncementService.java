package cn.ncss.jym.messagebox.service;

import java.util.List;
import java.util.Map;

import cn.ncss.jym.messagebox.pojo.Announcement;

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

	public boolean add(Announcement announ);

	public boolean delete(Announcement announ);

	public boolean update(Announcement announ);

	public Announcement get(String id);

	public List<Announcement> getListByType(String type);

	public List<Announcement> getListByType(String type, boolean online);

	public List<Announcement> getListByUser(String publisher);

	public List<Announcement> getListByUser(String publisher, boolean online);
	
	public Map<String,Integer> getAnnounInfo();
	
}
