package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.utils.Constant;

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
	private AnnouncementDao announcementDao;
	@Transactional(readOnly=false)
	@Override
	public boolean add(Announcement announ) {
		return announcementDao.add(announ);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean delete(Announcement announ) {
		return announcementDao.delete(announ);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean update(Announcement announ) {
		return announcementDao.update(announ);
	}

	@Transactional(readOnly=true)
	@Override
	public Announcement get(String id) {
		return announcementDao.get(id);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Announcement> getListByType(String type) {
		return announcementDao.getListByType(type);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Announcement> getListByType(String type, boolean online) {
		return announcementDao.getListByType(type, online);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Announcement> getListByUser(String publisher) {
		return announcementDao.getListByUser(publisher);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Announcement> getListByUser(String publisher, boolean online) {
		return announcementDao.getListByUser(publisher, online);
	}

	@Transactional(readOnly=true)
	@Override
	public Map<String, Integer> getAnnounInfo() {
		Map<String,Integer> announs_info=new HashMap<String, Integer>();
		int online=announcementDao.getByStatus(Constant.ANNOUN_OFFLINE);
		int offline=announcementDao.getByStatus(Constant.ANNOUN_OFFLINE);
		announs_info.put(Constant.ANNOUN_NUM, online+offline);
		announs_info.put(Constant.ONLINE_NUM, online);
		announs_info.put(Constant.OFFLINE_NUM, offline);
		return announs_info;
	}

}