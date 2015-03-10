package cn.ncss.jym.messagebox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.UserInfo;

/**
 * *********************
 *  用户信息服务层实现
 *
 * *********************
 * @author zeq [2015年3月10日]
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoDao {

	@Autowired
	private UserInfoDao userInfoDao;

	@Transactional(readOnly = false)
	@Override
	public void addOne(UserInfo userInfo) {
		if (userInfo == null) {

		}
		userInfoDao.addOne(userInfo);
	}

	@Override
	public void deleteOne(UserInfo userInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(UserInfo userInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserInfo get(String areacCode, String orgCode, String orgName, String fxmc, String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
