package cn.ncss.jym.messagebox.dao;

import cn.ncss.jym.messagebox.pojo.UserInfo;

/**
 * ************************
 *     用户持久层接口
 * 
 * ************************
 * @author zeq [2015年3月10日]
 *
 */
public interface UserInfoDao {

	public void addOne(UserInfo userInfo);

	public void deleteOne(UserInfo userInfo);

	public void update(UserInfo userInfo);

	public UserInfo get(String areacCode, String orgCode, String orgName, String fxmc, String type);

}
