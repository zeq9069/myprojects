package cn.ncss.jym.messagebox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.dao.RecordDao;
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
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementDao announcementDao;
	
	@Autowired
	private RecordDao recordDao;

	@Override
	public void create(Announcement announ) {
		announcementDao.create(announ);
	}
	@Transactional(readOnly = false)
	@Override
	public boolean delete(Announcement announ) {
		return announcementDao.delete(announ);
	}

	@Transactional(readOnly = false)
	@Override
	public boolean update(Announcement announ) {
		return announcementDao.update(announ);
	}

	@Transactional(readOnly = true)
	@Override
	public Announcement get(int id) {
		return announcementDao.get(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByType(String publisherId,String type,int currentIndex,int pageSize) {
		return announcementDao.getListByType(publisherId,type, currentIndex,pageSize);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByUser(String publisher,int currentIndex,int pageSize) {
		return announcementDao.getListByUser(publisher,currentIndex,pageSize);
	}

	@Override
	@Transactional(readOnly=true)
	public long getAnnounByViews(Announcement announ){
		return recordDao.getAnnounByViews(announ.getId());
	}

	public boolean isExists(int announ_id) {
		return announcementDao.get(announ_id) == null ? false : true;
	}
	@Transactional(readOnly=true)
	@Override
	public long getCount(List<String> typeList, String yxdm) {
		return announcementDao.getCount(typeList, yxdm);
	}
	
	@Transactional(readOnly=true)
	@Override
	public long getCount(String provinceCode) {
		return announcementDao.getCount(provinceCode);
	}
	@Transactional(readOnly=true)
	@Override
	public long getCount(String yxdm, String szyx) {
		return announcementDao.getCount(yxdm, szyx);
	}
	@Transactional(readOnly=true)
	@Override
	public long getCountByUser(String publisherId) {
		return announcementDao.getCountByUser(publisherId);
	}
	
	

}
