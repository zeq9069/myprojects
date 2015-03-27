package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.dao.GroupDao;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.GroupService;
import cn.ncss.jym.messagebox.service.StatisticService;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.system.pojo.SystemInfo;
import cn.ncss.jym.messagebox.utils.Constant;

/**
 * ***********************
 * 
 *   统计服务
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年3月27日]
 *
 */
@Service
public class StatisticServiceImpl implements StatisticService{


	@Autowired
	private GroupDao groupDao;

	@Autowired
	private AnnouncementDao announcementDao;

	
	@Transactional(readOnly=true)
	@Override
	public SystemInfo getAllInfo() {
		Map<String, Integer> announs = getAnnounInfo();
		Map<String, Integer> groups = getGroupInfo();
		return new SystemInfo(groups, announs);
	}
	
	private Map<String,Integer> getAnnounInfo(){
		Map<String, Integer> announs_info = new HashMap<String, Integer>();
		int online = announcementDao.getByStatus(Constant.ANNOUN_ONLINE);
		int offline = announcementDao.getByStatus(Constant.ANNOUN_OFFLINE);
		announs_info.put(Constant.ANNOUN_NUM, online + offline);
		announs_info.put(Constant.ONLINE_NUM, online);
		announs_info.put(Constant.OFFLINE_NUM, offline);
		return announs_info;
	}
	
	private Map<String,Integer> getGroupInfo(){
		Map<String, Integer> groups = new HashMap<String, Integer>();
		List<Object[]> list = groupDao.getGroupInfo();
		if (list != null) {
			for (Object[] obj : list) {
				groups.put(((Group)obj[0]).getName(), Integer.parseInt(obj[1].toString()));
			}
		}
		return groups;
	}
	
	
}
