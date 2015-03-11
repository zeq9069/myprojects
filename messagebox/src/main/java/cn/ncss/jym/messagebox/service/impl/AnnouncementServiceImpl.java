package cn.ncss.jym.messagebox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.service.AnnouncementService;

/**
 * ***********************
 * 
 *  公告服务层实现
 *
 * ***********************
 * @author kyrin [2015年3月11日]
 *
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService{

	
	@Autowired
	private AnnouncementService announcementService;
	@Transactional(readOnly=false)
	@Override
	public boolean add(Announcement announ) {
		return announcementService.add(announ);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean delete(Announcement announ) {
		return announcementService.delete(announ);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean update(Announcement announ) {
		return announcementService.update(announ);
	}

	@Transactional(readOnly=true)
	@Override
	public Announcement get(String id) {
		return announcementService.get(id);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Announcement> getListByType(String type) {
		return announcementService.getListByType(type);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Announcement> getListByType(String type, boolean online) {
		return announcementService.getListByType(type, online);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Announcement> getListByUser(String publisher) {
		return announcementService.getListByUser(publisher);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Announcement> getListByUser(String publisher, boolean online) {
		return announcementService.getListByUser(publisher, online);
	}

}
