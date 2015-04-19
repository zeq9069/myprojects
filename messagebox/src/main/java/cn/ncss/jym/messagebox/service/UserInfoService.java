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

	/*public boolean create(UserInfo userInfo);

	public boolean delete(UserInfo userInfo);

	public boolean update(UserInfo userInfo);

	public UserInfo get(String areacCode, String orgCode, String orgName, String fxmc, UserType type);


	public List<UserInfo> getList(int page, int pageSize);

	public int getCount();
	*/
	
	public UserInfo getById(String id);
	
	/**
	 * 获取用户所在院校所属的学校类型列表
	 * @return
	 */
	public List<String> getSchoolType();
	
	
	/**
	 * 获取当前省得所有学校用户和所有省（当前省用户除外）
	 * @param provinceCode
	 * @return
	 */
	public List<UserInfo> getCurrentProvinceSchoolAndAllProvinceUsers(String currentProvinceCode,String userID);
	
	/**
	 * 获取当前学校所在省得所有学校用户和本校的所有院系用户（当前学校用户除外）
	 * @return
	 */
	public  List<UserInfo> getCurrentProvinceSchoolAndSchoolDepartmentUsers(String provinceCode,String yxdm,String yxmc,String fxmc,String userID);
	
	/**
	 * 获取本院系所在学校的所有院系用户（当前院系用户除外）
	 * @param yxdm 当前用户的
	 * @param yxmc
	 * @param fxmc
	 * @param szyx
	 * @return
	 */
	public List<UserInfo> getCurrentSchoolAllDepartmentUsers(String provinceCode,String yxdm,String yxmc,String fxmc,String userID);
	
}
