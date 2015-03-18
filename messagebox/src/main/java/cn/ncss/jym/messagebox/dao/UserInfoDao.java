package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.Relation;
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

	public List<UserInfo> getUsersByGroup(int page, int pageSize, Group group);

	public int getCount();

	public int getCountByGroup(Group group);

	/**
	 * 保存关系
	 * @param relations
	 * @return
	 */
	public boolean addRelation(List<Relation> relations);

	/**
	 * 查询关系
	 * @param relations
	 * @return
	 */
	public boolean isRelation(UserInfo user, Group group);

	/**
	 * 删除关系
	 * @param relation
	 * @return
	 */
	public boolean deleteRelationByUser(UserInfo userInfo);

	/**
	 * 删除对应关系
	 * @param user
	 * @param group
	 * @return
	 */
	public boolean deleteRelation(UserInfo user, Group group);

	/**
	 * 添加公告查看记录
	 * @param record
	 * @return
	 */
	public boolean addRecord(Record record);

	/**
	 * 获取用户所有的用户组
	 * @param user
	 * @return
	 */
	public List<Relation> getGroups(UserInfo user);

}
