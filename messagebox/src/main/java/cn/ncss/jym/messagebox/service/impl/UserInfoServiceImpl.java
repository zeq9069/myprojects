package cn.ncss.jym.messagebox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.UserInfoService;


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
	
	@Override
	public List<String> getSchoolType() {
		List<String> typelist=new ArrayList<String>();
		return typelist;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserInfo getById(String id) {
		return userInfoDao.getById(id);
	}

	/*
	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private AnnouncementDao announcementDao;

	@Autowired
	private RecordDao recordDao;

	@Transactional(readOnly = false)
	@Override
	public boolean create(UserInfo userInfo) {
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
	public int getCount() {
		return userInfoDao.getCount();
	}


	private boolean isExists(UserInfo userInfo) {
		UserInfo user = userInfoDao.get(userInfo.getAreaCode(), userInfo.getOrgCode(), userInfo.getOrgName(),
				userInfo.getFxmc(), userInfo.getUserType());
		if (user == null) {
			return false;
		}
		return true;
	}
	*/
	

}
