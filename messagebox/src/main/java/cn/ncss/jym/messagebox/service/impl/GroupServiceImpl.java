package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.GroupDao;
import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.service.GroupService;

/**
 * ***********************
 * 
 *   用户群组服务层实现
 *
 * ***********************
 * @author kyrin [2015年3月11日]
 *
 */
@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDao groupDao;
	
	@Transactional(readOnly=false)
	@Override
	public boolean add(Group group) {
		if(isExists(group)){
			return false;
		}
		return groupDao.add(group);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean delete(Group group) {
		if(!isExists(group)){
			return false;
		}
		return groupDao.delete(group);
	}
	@Transactional(readOnly=false)
	@Override
	public boolean update(Group group) {
		if(!isExists(group)){
			return false;
		}
		return groupDao.update(group);
	}
	@Transactional(readOnly=true)
	@Override
	public Group get(String name) {
		return groupDao.get(name);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Group> getList() {
		return groupDao.getList();
	}
	@Transactional(readOnly=true)
	@Override
	public Map<String,Integer> getGroupInfo(){
		Map<String,Integer> groups=new HashMap<String, Integer>();
		List<Group> groupList=groupDao.getList();
		if(groupList!=null && groupList.size()>0){
			for(Group group:groupList){
				groups.put(group.getName(),group.getUsers().size());
			}
		}
		return groups;
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isExists(Group group){
		return groupDao.isExists(group);
	}

}
