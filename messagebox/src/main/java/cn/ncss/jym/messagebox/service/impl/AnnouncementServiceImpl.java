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
	public Map<String,String> add(Announcement announ) {
		Map<String,String> resultMap=new HashMap<String, String>();
		if(announ==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "公告不能为空");
			return resultMap;
		}
		boolean res=announcementDao.add(announ);
		resultMap.put(Constant.HTTP_STATUS,res==true?Constant.HTTP_SUCCESS:Constant.HTTP_ERROR);
		resultMap.put(Constant.HTTP_MESSAGE, res==true?"发布成功":"发布失败");
		return resultMap;
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
		int online=announcementDao.getByStatus(Constant.ANNOUN_OFFLINE).size();
		int offline=announcementDao.getByStatus(Constant.ANNOUN_OFFLINE).size();
		announs_info.put(Constant.ANNOUN_NUM, online+offline);
		announs_info.put(Constant.ONLINE_NUM, online);
		announs_info.put(Constant.OFFLINE_NUM, offline);
		return announs_info;
	}

	@Override
	public List<Announcement> getListByOnline(String online) {
		return announcementDao.getByStatus(online);
	}

}
