package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.dao.GroupDao;
import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.service.GroupService;
import cn.ncss.jym.messagebox.utils.Constant;
import cn.ncss.jym.messagebox.utils.StringUtil;

/**
 * ***********************
 * 
 *   用户群组服务层实现
 *
 * ***********************
 * @author kyrin [2015年3月11日]
 *
 */
@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;
	@Autowired 
	private UserInfoDao userInfoDao;
	
	@Autowired
	private AnnouncementDao announcementDao;

	@Transactional(readOnly = false)
	@Override
	public Map<String, String> add(Group group) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (!StringUtil.hasText(group.getName())) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "群组名不能为空");
			return resultMap;
		} else if (isExists(group.getName())) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "群组名已经存在");
		} else {
			String id = groupDao.add(group);
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_SUCCESS);
			resultMap.put(Constant.HTTP_MESSAGE, id);
		}
		return resultMap;
	}

	@Transactional(readOnly = false)
	@Override
	public Map<String, String> delete(int group_id) {
		Map<String, String> resultMap = new HashMap<String, String>();
		 if (!isExists(group_id)) {
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "群组不存在");
			return resultMap;
		} 
		boolean res = groupDao.delete(group_id);
		resultMap.put(Constant.HTTP_STATUS, res == true ? Constant.HTTP_SUCCESS : Constant.HTTP_ERROR);
		resultMap.put(Constant.HTTP_MESSAGE, res == true ? "删除成功" : "删除失败");
		return resultMap;
	}

	@Transactional(readOnly = false)
	@Override
	public boolean update(Group group) {
		if (!isExists(group.getName())) {
			return false;
		}
		return groupDao.update(group);
	}

	@Transactional(readOnly = true)
	@Override
	public Group get(String name) {
		return groupDao.get(name);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Group> getList() {
		return groupDao.getList();
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Integer> getGroupInfo() {
		Map<String, Integer> groups = new HashMap<String, Integer>();
		List<Object[]> list = groupDao.getGroupInfo();
		if (list != null) {
			for (Object[] obj : list) {
				groups.put(((Group)obj[0]).getName(), Integer.parseInt(obj[1].toString()));
			}
		}
		return groups;
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isExists(int group_id) {
		return groupDao.isExists(group_id);
	}
	@Transactional(readOnly = true)
	@Override
	public boolean isExists(String groupName){
		return groupDao.isExists(groupName);
	}

}
