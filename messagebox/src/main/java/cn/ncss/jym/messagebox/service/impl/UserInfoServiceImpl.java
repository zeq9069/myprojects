package cn.ncss.jym.messagebox.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.Relation;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.pojo.UserInfo.UserType;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.utils.Constant;
import cn.ncss.jym.messagebox.utils.StringUtil;

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

	@Transactional(readOnly = false)
	@Override
	public boolean addOne(UserInfo userInfo) {
		if (userInfo == null || isExists(userInfo)) {
			return false;
		}
		return userInfoDao.addOne(userInfo);
	}
	


	@Transactional(readOnly=false)
	@Override
	public boolean deleteOne(UserInfo userInfo) {
		if(userInfo==null || isExists(userInfo)){
			return false;
		}
		return userInfoDao.deleteOne(userInfo);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean update(UserInfo userInfo) {
		if (userInfo == null || !isExists(userInfo)) {
			return false;
		}
		return userInfoDao.update(userInfo);
	}
	@Transactional(readOnly=true)
	@Override
	public UserInfo get(String areacCode, String orgCode, String orgName, String fxmc, UserType type) {
		return userInfoDao.get(areacCode, orgCode, orgName, fxmc, type);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<UserInfo> getList(int page,int pageSize){
		return userInfoDao.getList(page,pageSize);
	}
	@Override
	@Transactional(readOnly=true)
	public List<UserInfo> getUsersByGroup(int page,int pageSize,Group group){
		return userInfoDao.getUsersByGroup(page, pageSize, group);
	}
	
	@Override
	@Transactional(readOnly=true)
	public int getCount() {
		return userInfoDao.getCount();
	}
	@Override
	@Transactional(readOnly=true)
	public int getCountByGroup(Group group){
		return userInfoDao.getCountByGroup(group);
	}
	
	@Override
	@Transactional(readOnly=true)
	public UserInfo getById(String id){
		return userInfoDao.getById(id);
	}
	
	@Override
	@Transactional(readOnly=false)
	public Map<String,String> addRelations(List<Relation> relations){
		Map<String,String> resultMap=new HashMap<String, String>();
		
		if(relations==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE,"参数异常");
			return resultMap;
		}
		if(relations.size()==0){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE,"参数为空");
			return resultMap;
		}
		
		List<Relation> list=new ArrayList<Relation>();
		
		for(Relation r:relations){
			if(!isExist(r)){
				list.add(r);
			}
		}
		if(list.size()==0){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE,"关系已经全部存在");
			return resultMap;
		}
		
		boolean res=userInfoDao.addRelation(list);
		
		resultMap.put(Constant.HTTP_STATUS, res==true?Constant.HTTP_SUCCESS:Constant.HTTP_ERROR);
		resultMap.put(Constant.HTTP_MESSAGE,res==true?"":"添加失败");
		return resultMap;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Map<String,String> deleteRelation(String u_id,String groupName){
		Map<String,String> resultMap=new HashMap<String, String>();
		Relation relation=null;
		if(!StringUtil.hasText(groupName) || !StringUtil.hasText(u_id)){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "参数为空");
			return resultMap;
		}
		
		UserInfo userInfo=userInfoDao.getById(u_id);
		if(userInfo==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "用户不存在");
			return resultMap;
		}
		
		Set<Relation> set=userInfo.getRelations();
		if(set==null || set.size()==0){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "关系不存在");
			return resultMap;
		}
		
		for(Relation r:set){
			Group g=r.getGroup();
			if(g.getName().equals(groupName)){
				relation=r;
			}
		}
		
		if(relation==null){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "关系不存在");
			return resultMap;
		}
		boolean res=userInfoDao.deleteRelation(relation);
		
		resultMap.put(Constant.HTTP_STATUS, res==true?Constant.HTTP_SUCCESS:Constant.HTTP_ERROR);
		resultMap.put(Constant.HTTP_MESSAGE, res==true?"":"删除失败");
		return resultMap;
		
	}

	
	private boolean isExists(UserInfo userInfo){
		UserInfo user=userInfoDao.get(userInfo.getAreaCode(), userInfo.getOrgCode(), userInfo.getOrgName(), userInfo.getFxmc(), userInfo.getUserType());
		if(user==null){
			return false;
		}
		return true;
	}
	
	//判断关系是否存在
	public boolean isExist(Relation relation){
		
		UserInfo userInfo=relation.getUserInfo();
		Set<Relation> set=userInfo.getRelations();
		for(Relation r:set){
			if(r.getUserInfo().equals(relation.getUserInfo()) && r.getGroup().equals(relation.getGroup())){
				return true;
			}
		}
		return false;
	}


}
