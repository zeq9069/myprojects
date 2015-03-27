package cn.ncss.jym.messagebox.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.dao.GroupDao;
import cn.ncss.jym.messagebox.dao.RecordDao;
import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.Group_announ;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.Relation;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.pojo.UserInfo.UserType;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.utils.Constant;
import cn.ncss.jym.messagebox.utils.StringUtil;

/**
 * *********************
 *  用户信息服务层实现
 *
 * *********************
 * @author zeq [2015年3月10日]
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private AnnouncementDao announcementDao;

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private RecordDao recordDao;

	@Transactional(readOnly = false)
	@Override
	public boolean addOne(UserInfo userInfo) {
		if (userInfo == null || isExists(userInfo)) {
			return false;
		}
		return userInfoDao.addOne(userInfo);
	}

	@Transactional(readOnly = false)
	@Override
	public boolean deleteOne(UserInfo userInfo) {
		if (userInfo == null || isExists(userInfo)) {
			return false;
		}
		try {
			userInfoDao.deleteOne(userInfo);
			userInfoDao.deleteRelationByUser(userInfo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional(readOnly = false)
	@Override
	public boolean update(UserInfo userInfo) {
		if (userInfo == null || !isExists(userInfo)) {
			return false;
		}
		return userInfoDao.update(userInfo);
	}

	@Transactional(readOnly = true)
	@Override
	public UserInfo get(String areacCode, String orgCode, String orgName, String fxmc, UserType type) {
		return userInfoDao.get(areacCode, orgCode, orgName, fxmc, type);
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserInfo> getList(int page, int pageSize) {
		return userInfoDao.getList(page, pageSize);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserInfo> getUsersByGroup(int page, int pageSize, Group group) {
		return userInfoDao.getUsersByGroup(page, pageSize, group);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCount() {
		return userInfoDao.getCount();
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByGroup(Group group) {
		return userInfoDao.getCountByGroup(group);
	}

	@Override
	@Transactional(readOnly = true)
	public UserInfo getById(String id) {
		return userInfoDao.getById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Map<String, String> addRelations(List<Relation> relations) {
		Map<String, String> resultMap = new HashMap<String, String>();

		if (relations == null) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "参数异常");
			return resultMap;
		}
		if (relations.size() == 0) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "参数为空");
			return resultMap;
		}

		List<Relation> list = new ArrayList<Relation>();

		for (Relation r : relations) {
			if (!isExist(r)) {
				list.add(r);
			}
		}
		if (list.size() == 0) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "关系已经全部存在");
			return resultMap;
		}

		boolean res = userInfoDao.addRelation(list);

		resultMap.put(Constant.HTTP_STATUS, res == true ? Constant.HTTP_SUCCESS : Constant.HTTP_ERROR);
		resultMap.put(Constant.HTTP_MESSAGE, res == true ? "" : "添加失败");
		return resultMap;
	}

	@Override
	@Transactional(readOnly = false)
	public Map<String, String> deleteRelation(String u_id, String groupName) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (!StringUtil.hasText(groupName) || !StringUtil.hasText(u_id)) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "参数为空");
			return resultMap;
		}

		UserInfo userInfo = userInfoDao.getById(u_id);
		if (userInfo == null) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "用户不存在");
			return resultMap;
		}

		Group group = groupDao.get(groupName);

		if (group == null) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "用户组不存在");
			return resultMap;
		}

		boolean isExist = userInfoDao.isRelation(userInfo, group);

		if (!isExist) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "关系不存在");
			return resultMap;
		}

		boolean res = userInfoDao.deleteRelation(userInfo, group);

		resultMap.put(Constant.HTTP_STATUS, res == true ? Constant.HTTP_SUCCESS : Constant.HTTP_ERROR);
		resultMap.put(Constant.HTTP_MESSAGE, res == true ? "" : "删除失败");
		return resultMap;
	}
	
	@SuppressWarnings("unused")
	@Override
	@Transactional(readOnly = false)
	public boolean addRecord(String u_id, int announ_id) {
		Map<String, String> resultMap = new HashMap<String, String>();
		UserInfo userInfo = userInfoDao.getById(u_id);
		if (userInfo == null) {
			return false;
		}

		Announcement announ = announcementDao.get(announ_id);

		if (announ == null) {
			return false;
		}

		boolean res = recordDao.isExists(userInfo, announ);

		if (res) {
			return true;
		}

		List<Relation> userGroups = userInfoDao.getGroups(userInfo);
		List<Group_announ> announGroups = announcementDao.getGroups(announ);

		boolean flag = false;
		for (Relation r : userGroups) {
			Group g = r.getGroup();
			for (Group_announ group_announ : announGroups) {
				if (group_announ.getGroup().equals(g)) {
					flag = true;
					break;
				}
			}
		}
		if (flag = false) {
			return false;
		}

		Record rec = new Record();
		rec.setAnnoun(announ);
		rec.setUser(userInfo);

		return userInfoDao.addRecord(rec);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Group> getGroupsByUser(UserInfo user){
		List<Relation> relations=userInfoDao.getGroups(user);
		List<Group> groups=new ArrayList<Group>();
		
		for(Relation r:relations){
			groups.add(r.getGroup());
		}
		return groups;
	}
	
	

	private boolean isExists(UserInfo userInfo) {
		UserInfo user = userInfoDao.get(userInfo.getAreaCode(), userInfo.getOrgCode(), userInfo.getOrgName(),
				userInfo.getFxmc(), userInfo.getUserType());
		if (user == null) {
			return false;
		}
		return true;
	}
	
	

	//判断关系是否存在
	public boolean isExist(Relation relation) {
		return userInfoDao.isRelation(relation.getUserInfo(), relation.getGroup());
	}
	

}
