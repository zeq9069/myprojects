package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.pojo.UserInfo.UserType;

/**
 * ************************
 *     用户持久层接口
 * 
 * ************************
 * @author zeq [2015年3月10日]
 *
 */
public interface UserInfoDao {

	public boolean addOne(UserInfo userInfo);

	public boolean deleteOne(UserInfo userInfo);

	public boolean update(UserInfo userInfo);

	public UserInfo get(String areacCode, String orgCode, String orgName, String fxmc, UserType type);

	public UserInfo getById(String id);

	public List<UserInfo> getList(int page, int pageSize);

	public int getCount();




	/**
	 * 删除关系
	 * @param relation
	 * @return
	 */
	public boolean deleteRelationByUser(UserInfo userInfo);

	/**
	 * 添加公告查看记录
	 * @param record
	 * @return
	 */
	public boolean addRecord(Record record);
	

}
