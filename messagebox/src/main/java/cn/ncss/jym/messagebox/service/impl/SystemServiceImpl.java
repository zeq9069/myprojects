package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.GroupService;
import cn.ncss.jym.messagebox.service.SystemService;
import cn.ncss.jym.messagebox.system.pojo.SystemInfo;

/**
 * ***********************
 * 
 * 对外开放系统级服务接口实现
 *
 * ***********************
 * 
 * @author kyrin [2015年3月12日]
 *
 */
@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private GroupService groupService;

	@Autowired
	private AnnouncementService announcementService;

	public SystemInfo getAllInfo() {
		Map<String, Integer> announs = announcementService.getAnnounInfo();
		Map<String, Integer> groups = groupService.getGroupInfo();
		SystemInfo system = new SystemInfo(groups, announs);
		return system;
	}

	@Override
	public Map<String, String> getGroups() {
		Map<String, String> resultMap = new HashMap<String, String>();
		List<Group> groupList = groupService.getList();
		if (groupList != null && groupList.size() > 0) {
			for (Group group : groupList) {
				resultMap.put("" + group.getId(), group.getName());
			}
		}
		return resultMap;
	}
	
	@Override
	public Map<String,String> addGroup(Group group){
		return groupService.add(group);
	}
	
	@Override
	public Map<String,String> deletGroup(String name){
		return groupService.delete(name);
	}


	
	

}
