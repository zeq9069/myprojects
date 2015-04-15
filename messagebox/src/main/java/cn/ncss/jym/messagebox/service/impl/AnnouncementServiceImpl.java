package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.dao.RecordDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.utils.Constant;
/**
 * ***********************
 * 
 *  公告服务层实现
 *
 * ***********************
 * @author kyrin [2015年3月11日]
 *
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementDao announcementDao;
	
	@Autowired
	private RecordDao recordDao;
	
	@Autowired
	private UserInfoService userInfoService;

	@Transactional(readOnly=false)
	@Override
	public Map<String,String> create(Announcement announ) {
		//TODO 业务层获取用户信息，初始化announcement的部分信息
		Map<String,String> resultMap=new HashMap<String, String>();
		
		/*
		 * 
		 * if(province){
			announ.setPublish_role("province");
			announ.setPublish_dm(provinceCode);
		}else if(school){
			announ.setPublish_role("school");
			announ.setPublish_dm(yxdm);
		}*/
		UserInfo u=new UserInfo();
		announ.setPublish_dm(u.getOrgCode());
		announ.setPublish_role("school");
		announ.setUser(u);
		try{
			announcementDao.create(announ);
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_SUCCESS);
			resultMap.put(Constant.HTTP_MESSAGE, "发布成功");
		}catch(Exception e){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_ERROR);
			resultMap.put(Constant.HTTP_MESSAGE, "发布失败");
		}
		return resultMap;
	}
	@Transactional(readOnly = false)
	@Override
	public boolean delete(Announcement announ) {
		return announcementDao.delete(announ);
	}

	@Transactional(readOnly = false)
	@Override
	public boolean update(Announcement announ) {
		return announcementDao.update(announ);
	}

	@Transactional(readOnly = true)
	@Override
	public Announcement get(int id) {
		//TODO 服务层获取用户信息 初始化
		Announcement announcement=announcementDao.get(id);
		return announcement;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByType(String type,int currentIndex,int pageSize) {
		//TODO 
		//服务层获取用户信息
		UserInfo userInfo=new UserInfo();
		return announcementDao.getListByType(userInfo,type, currentIndex,pageSize);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByUser(int currentIndex,int pageSize) {
		//TODO 
		//服务层获取用户信息
				UserInfo userInfo=new UserInfo();
		return announcementDao.getListByUser(userInfo,currentIndex,pageSize);
	}

	@Override
	@Transactional(readOnly=true)
	public long getAnnounByViews(Announcement announ){
		return recordDao.getAnnounByViews(announ.getId());
	}

	public boolean isExists(int announ_id) {
		return announcementDao.get(announ_id) == null ? false : true;
	}
	
	@Transactional(readOnly=true)
	@Override
	public long getCountByUser() {
		//TODO 
		//服务层获取用户信息
		UserInfo userInfo=new UserInfo();
		return announcementDao.getCountByUser(userInfo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Announcement> getReceiveList(int currentIndex,int pageSize){
		//TODO 
		//获取当前用户，根据用户来判断获取不同的分页
		UserInfo userInfo=new UserInfo();
		/*
		if(省){
			announcementDao.getReceiveByProvince(provinceCode, currentIndex, pageSize);
		}else if(院校){
			announcementDao.getReceiveByYxdm(typeList, yxdm, currentIndex, pageSize);
		}else if(院系){
			announcementDao.getReceiveByYxdm(typeList, yxdm, currentIndex, pageSize);
		}
		*/
		List<String> typeList=userInfoService.getSchoolType();
		return announcementDao.getReceiveByYxdm(typeList, userInfo.getAreaCode(),userInfo.getOrgCode(), userInfo,currentIndex, pageSize);
	}
	
	@Transactional(readOnly=true)
	@Override
	public long getReceiveCount() {
		//TODO
		//获取当前用户
		UserInfo userInfo=new UserInfo();
		/*
		if(省){
			announcementDao.getCount(provinceCode);
		}else if(院校){
			announcementDao.getCount(typeList, yxdm)
		}else if(院系){
			announcementDao.getCount(yxdm, szyx)
		}
		*/
		List<String> typeList=userInfoService.getSchoolType();
		return announcementDao.getCount(typeList, userInfo.getAreaCode(),userInfo.getOrgCode(),userInfo);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Announcement> getAnnounsByNot() {
		//TODO
		//获取用户信息
		UserInfo userInfo=new UserInfo();
		/*
		if(省){
			announcementDao.getReceiveByProvince(userInfo.getAreaCode());
		}else if(学校){
			announcementDao.getReceiveByYxdm(typeList, yxdm);
		}else if(院系){
			announcementDao.getReceiveBySzyx(yxdm, szyx)
		}
		*/
		List<String> typeList=userInfoService.getSchoolType();
		
		List<Announcement> receiveList=announcementDao.getReceiveByYxdm(typeList, userInfo.getAreaCode(),userInfo.getOrgCode(),userInfo);
		
		if(receiveList==null || receiveList.size()==0){
			return receiveList;
		}
		
		List<Record> lookover=recordDao.getListByUId(userInfo);
		
		if(lookover==null || lookover.size()==0){
			return receiveList;
		}
		
		//效率待优化
		 for(Announcement announ:receiveList){
			for(Record record:lookover){
				 if(record.getAnnoun().getId()==announ.getId()){
					 receiveList.remove(announ);
				 }
			}
		 }
		return receiveList;
	}

}
