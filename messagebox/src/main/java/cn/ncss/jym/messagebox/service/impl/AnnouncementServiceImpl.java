package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.ThreadFactory.SendMessageTask;
import cn.ncss.jym.messagebox.ThreadFactory.ThreadFactory;
import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.dao.RecordDao;
import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.RecordService;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.system.pojo.TargetSchool;
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
@SuppressWarnings("static-access")
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementDao announcementDao;
	
	@Autowired
	private RecordDao recordDao;
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private UserInfoService userInfoService;

	@Transactional(readOnly=false,rollbackFor={InterruptedException.class, ExecutionException.class, Exception.class})
	@Override
	public Map<String,String> create(Announcement announ,List<TargetSchool> targetSchoolList) throws InterruptedException, ExecutionException, Exception {
		//TODO 业务层获取用户信息，初始化announcement的部分信息
		//根据当前发送message的用户
		//从用户表获取所有用户（如果是省用户------>获取外省用户和本省学校用户，学校用户-------->获取本省其他学校用户和本院系用户，院系用户----->获取本院校其他院系的用户）
		//只要是message的目标用户，那就往record表中插入记录，message处于未读状态

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
		announcementDao.create(announ);
		
		//获取发送的目的用户
		List<UserInfo> userInfos=this.getUsers(u);
		//找不到相关用户的话视为发送失败
		if(userInfos==null || userInfos.size()==0){
			resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_SUCCESS);
			resultMap.put(Constant.HTTP_MESSAGE, "发布失败");
			return resultMap;
		}
		//只要有一个用户没接收到消息就视为发送失败
		for(UserInfo userInfo:userInfos){
			if(!send(userInfo,announ, targetSchoolList)){
				resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_SUCCESS);
				resultMap.put(Constant.HTTP_MESSAGE, "发布失败");
				return resultMap;
			} 
		}
		
		//Future<Boolean> future=ThreadFactory.getInstance().submit(new SendMessageTask(announ,u,targetSchoolList,userInfoService,recordService));
		resultMap.put(Constant.HTTP_STATUS, Constant.HTTP_SUCCESS);
		resultMap.put(Constant.HTTP_MESSAGE, "发布成功");
		return resultMap;
	}
	
	private List<UserInfo> getUsers(UserInfo userInfo){
		List<UserInfo> userInfos=null;
		if(userInfo.isProvinceUser()){
			userInfos=userInfoService.getCurrentProvinceSchoolAndAllProvinceUsers(userInfo.getAreaCode(), userInfo.getId());
		}else if(userInfo.isSchoolUser()){
			userInfos=userInfoService.getCurrentProvinceSchoolAndSchoolDepartmentUsers(userInfo.getAreaCode(), userInfo.getOrgCode(), 
					userInfo.getOrgName(), userInfo.getFxmc(),userInfo.getId());
			System.out.println(""+userInfos.size());
		}else if(userInfo.isDepartmentUser()){
			userInfos=userInfoService.getCurrentSchoolAllDepartmentUsers(userInfo.getAreaCode(), userInfo.getOrgCode(), 
					userInfo.getOrgName(), userInfo.getFxmc(),userInfo.getId());
		}
		
		return userInfos;
	}
	
	private boolean send(UserInfo user,Announcement announ,List<TargetSchool> targetSchoolList){
		//TODO yxdm的格式要改
		
		String targetProvinceCode[];
		String targetYxlx[];
		String targetSzyx[];
		
		targetProvinceCode=announ.getTargetProvinceCode()==null?null:announ.getTargetProvinceCode().split(",");
		targetYxlx=announ.getTargetYxlx()==null?null:announ.getTargetYxlx().split(",");
		targetSzyx=announ.getTargetDepartment()==null?null:announ.getTargetDepartment().split(",");
		
		Record r=new Record();
		r.setAnnoun(announ);
		r.setUser(user);
		if(user.isProvinceUser()){
			for(String provinceCode:targetProvinceCode){
				if(user.getAreaCode().equals(provinceCode)){
					return recordService.create(r);
				}
			}
		}else if(user.isSchoolUser()){
			for(TargetSchool school:targetSchoolList){
				if(user.getOrgCode().equals(school.getId()) && school.getText().contains(user.getOrgName()) && user.getFxmc().equals(school.getFxmc())){
					return recordService.create(r);
				}
			}
			
			for(String yxlx:targetYxlx){
				switch(yxlx){
				case "school_all":
					return recordService.create(r);
				case "school_211":
					if(user.is211()) {
						return recordService.create(r);
					}
				case "school_985":
					if(user.is985()) {
						return recordService.create(r);
					}
				case "school_bachelor_new":
					if(user.isNew()){
						return recordService.create(r);
					}
				case "school_by_ministry":
					if(user.isByministry()){
						return recordService.create(r);
					}
				case "shcool_by_province":
					 if(user.isByprovince()){
						 return recordService.create(r);
					 }
				case "school_independent":
					if(user.isIndependent()){
						return recordService.create(r);
					}
				}
			}
			
		}else if(user.isDepartmentUser()){
			for(String szyx:targetSzyx){
				if(user.getDepartment().equals(szyx)){
					return recordService.create(r);
				}
			}
		}
		return true;
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
