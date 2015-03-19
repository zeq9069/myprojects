package cn.ncss.jym.messagebox.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.Group_announ;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;
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
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementDao announcementDao;

	@Transactional(readOnly = false)
	@Override
	public Map<String, String> add(Announcement announ, List<Group> groupList) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (announ == null) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "公告不能为空");
			return resultMap;
		}
		List<Group_announ> gaList = new ArrayList<Group_announ>();
		try {
			int announ_id = announcementDao.add(announ);
			announ.setId(announ_id);
			for (Group group : groupList) {
				Group_announ ga = new Group_announ();
				ga.setAnnoun(announ);
				ga.setGroup(group);
				gaList.add(ga);
			}
			boolean res = announcementDao.addGroup_announ(gaList);
			resultMap.put(Constant.HTTP_STATUS, res == true ? Constant.HTTP_SUCCESS : Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, res == true ? "发布成功" : "发布失败");
			return resultMap;
		} catch (Exception e) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "保存失败");
			return resultMap;
		}
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

	@Override
	@Transactional(readOnly = false)
	public Map<String, String> updateOnline(int announ_id, String online) {
		Map<String, String> resultMap = new HashMap<String, String>();

		if (!isExists(announ_id)) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "指定的公告不存在");
			return resultMap;
		}

		boolean res = announcementDao.updateOnline(announ_id, online);

		resultMap.put(Constant.HTTP_STATUS, res == true ? Constant.HTTP_SUCCESS : Constant.HTTP_ERROR);
		resultMap.put(Constant.HTTP_MESSAGE, res == true ? "更改成功" : "更改成功");
		return resultMap;
	}

	@Transactional(readOnly = true)
	@Override
	public Announcement get(int id) {
		return announcementDao.get(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByOnline(String online) {
		return announcementDao.getListByOnline(online);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByType(String type, boolean online) {
		return announcementDao.getListByType(type, online);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByUser(String publisher) {
		return announcementDao.getListByUser(publisher);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByUser(String publisher, boolean online) {
		return announcementDao.getListByUser(publisher, online);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Integer> getAnnounInfo() {
		Map<String, Integer> announs_info = new HashMap<String, Integer>();
		int online = announcementDao.getByStatus(Constant.ANNOUN_ONLINE);
		int offline = announcementDao.getByStatus(Constant.ANNOUN_OFFLINE);
		announs_info.put(Constant.ANNOUN_NUM, online + offline);
		announs_info.put(Constant.ONLINE_NUM, online);
		announs_info.put(Constant.OFFLINE_NUM, offline);
		return announs_info;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Group> getGroupsOfAnnoun(Announcement announ) {
		List<Group_announ> list = announcementDao.getGroupsOfAnnoun(announ);
		List<Group> groups = new ArrayList<Group>();
		if (list != null) {
			for (Group_announ ga : list) {
				groups.add(ga.getGroup());
			}
		}
		return groups;
	}

	@Override
	@Transactional(readOnly=true)
	public List<UserInfo> getAnnounByViews(Announcement announ) {
		List<Record> recordList = announcementDao.getAnnounByViews(announ);
		List<UserInfo> list = new ArrayList<UserInfo>();
		if (recordList != null) {
			for (Record re : recordList) {
				list.add(re.getUser());
			}
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Announcement> getListByOnline(int currentIndex,int pageSize,String online) {
		return announcementDao.getByStatus(currentIndex, pageSize,online);
	}

	public boolean isExists(int announ_id) {
		return announcementDao.get(announ_id) == null ? false : true;
	}

}
