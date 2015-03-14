package cn.ncss.jym.messagebox.service;

import java.util.List;
import java.util.Map;

import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.Relation;
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
	
	public UserInfo getById(String id);
	
	public List<UserInfo> getList(int page,int pageSize);
	
	public List<UserInfo> getUsersByGroup(int page,int pageSize,Group group);
	
	public int getCount() ;
	
	public int getCountByGroup(Group group);
	
	public Map<String,String> addRelations(List<Relation> relations);
	
	public Map<String,String> deleteRelation(String u_id,String groupName);



}
