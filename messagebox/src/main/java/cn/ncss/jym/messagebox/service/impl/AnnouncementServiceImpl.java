package cn.ncss.jym.messagebox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.dao.RecordDao;
import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.UserInfoService;
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

	@Override
	public void create(Announcement announ) {
		announcementDao.create(announ);
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
		return announcementDao.get(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByType(String publisherId,String type,int currentIndex,int pageSize) {
		return announcementDao.getListByType(publisherId,type, currentIndex,pageSize);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByUser(String publisher,int currentIndex,int pageSize) {
		return announcementDao.getListByUser(publisher,currentIndex,pageSize);
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
	public long getCountByUser(String publisherId) {
		return announcementDao.getCountByUser(publisherId);
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
		return announcementDao.getReceiveByYxdm(typeList, userInfo.getOrgCode(), currentIndex, pageSize);
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
		return announcementDao.getCount(typeList, userInfo.getOrgCode());
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
		List<Announcement> list=new ArrayList<Announcement>();
		List<String> typeList=userInfoService.getSchoolType();
		
		List<Announcement> receiveList=announcementDao.getReceiveByYxdm(typeList, userInfo.getOrgCode());
		
		if(receiveList==null || receiveList.size()==0){
			return list;
		}
		
		List<Record> lookover=recordDao.getListByUId(userInfo.getId());
		
		if(lookover==null || lookover.size()==0){
			return receiveList;
		}
		
		//效率太慢,待优化
		 for(Announcement announ:receiveList){
			 boolean flag=false;
				for(Record record:lookover){
					 if(record.getAnnoun().getId()==announ.getId()){
						 receiveList.remove(announ);
						 flag=true;
					 }
				}
			if(flag==false){
				UserInfo u= userInfoService.getById(announ.getPublisher());
				announ.setPublisher(u.toString());
				list.add(announ);
			}
		 }
		 
		return list;
	}

}
