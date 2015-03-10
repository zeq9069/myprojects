package cn.ncss.jym.messagebox.service;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.pojo.UserInfo.UserType;

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

	public boolean addOne(UserInfo userInfo);

//	public boolean addList(List<UserInfo> userList);
	
	public boolean deleteOne(UserInfo userInfo) ;
	
	public boolean update(UserInfo userInfo) ;

	public UserInfo get(String areacCode, String orgCode, String orgName, String fxmc, UserType type);
	
	public List<UserInfo> getList();

}
