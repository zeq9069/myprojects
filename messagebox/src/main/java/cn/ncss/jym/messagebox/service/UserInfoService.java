package cn.ncss.jym.messagebox.service;

import java.util.List;
import java.util.Map;

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

	/*public boolean create(UserInfo userInfo);

	public boolean delete(UserInfo userInfo);

	public boolean update(UserInfo userInfo);

	public UserInfo get(String areacCode, String orgCode, String orgName, String fxmc, UserType type);

	public UserInfo getById(String id);

	public List<UserInfo> getList(int page, int pageSize);

	public int getCount();
	*/
	
	/**
	 * 获取用户所在院校所属的学校类型列表
	 * @return
	 */
	public List<String> getSchoolType();
	
}
