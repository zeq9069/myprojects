package cn.ncss.jym.messagebox.service;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.UserInfo;

/**
 * *************************
 * 
 *  userInfo 服务层接口
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
public interface UserInfoService {

	public void add(UserInfo userInfo);

	public void addList(List<UserInfo> userList);

	public UserInfo getUser(String areaCode, String orgCode, String orgName, String fxmc, String type);

}
