package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.GroupService;
import cn.ncss.jym.messagebox.service.SystemService;
import cn.ncss.jym.messagebox.service.UserInfoService;
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
	private UserInfoService userInfoService;

	@Autowired
	private AnnouncementService announcementService;

	@Override
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
	public Map<String, String> addGroup(Group group) {
		return groupService.add(group);
	}

	@Override
	public Map<String, String> deletGroup(String name) {
		return groupService.delete(name);
	}

	@Override
	public List<UserInfo> getUsers(int page, int pageSize) {
		return userInfoService.getList(page, pageSize);
	}

	@Override
	public int getCount(String group) {
		Group g = groupService.get(group);
		return userInfoService.getCountByGroup(g);
	}

	@Override
	public Map<String, String> publishAnnoun(Announcement announ, List<Group> groupList) {
		return announcementService.add(announ, groupList);
	}

	@Override
	public List<Announcement> getAnnouns(String online) {
		return announcementService.getListByOnline(online);
	}

	@Override
	public Map<String, String> updateOnline(int announ_id, String online) {
		return announcementService.updateOnline(announ_id, online);
	}

	@Override
	public List<UserInfo> getUsersByGroup(int page, int pageSize, String groupName) {
		Group group = groupService.get(groupName);
		return userInfoService.getUsersByGroup(page, pageSize, group);
	}

	@Override
	public int getCount() {
		return userInfoService.getCount();
	}

	@Override
	public int getCountByGroup(String groupName) {
		Group group = groupService.get(groupName);
		return userInfoService.getCountByGroup(group);
	}

	@Override
	public boolean addRecord(String u_id, int announ_id) {
		return userInfoService.addRecord(u_id, announ_id);
	}

	@Override
	public List<UserInfo> getAnnounByViews(Announcement announ) {
		return announcementService.getAnnounByViews(announ);
	}

	@Override
	public UserInfo getUser(String u_id) {
		return userInfoService.getById(u_id);
	}

	@Override
	public Announcement getAnnoun(int announ_id) {
		return announcementService.get(announ_id);
	}

	@Override
	public List<Group> getGroupsOfAnnoun(Announcement announ) {
		return announcementService.getGroupsOfAnnoun(announ);
	}
}
