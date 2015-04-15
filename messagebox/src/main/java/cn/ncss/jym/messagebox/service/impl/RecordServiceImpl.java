package cn.ncss.jym.messagebox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.RecordDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.RecordService;

/**
 * ***********************
 * 
 *   公告查看记录服务层实现
 *
 * ***********************
 * @author kyrin [2015年3月11日]
 *
 */
@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordDao recordDao;

	@Transactional(readOnly = false)
	@Override
	public boolean create(Record record) {
		if(isExists(record.getUser(), record.getAnnoun())){
			return true;
		}
		return recordDao.create(record);
	}

	@Transactional(readOnly = false)
	@Override
	public boolean delete(Record record) {
		if (!isExists(record.getUser(), record.getAnnoun())) {
			return false;
		}
		return recordDao.delete(record);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Announcement> getListByUId(int currentIndex,int pageSize) {
		//TODO
		//服务层获取用户信息
		UserInfo userInfo=new UserInfo();
		
		List<Announcement> list=new ArrayList<Announcement>();
		List<Record> recordList=recordDao.getListByUId(userInfo,currentIndex,pageSize);
		if(recordList==null || recordList.size()==0){
			return list;
		}
		
		for(Record record:recordList){
			Announcement announ=record.getAnnoun();
			announ.setContent("");
			list.add(announ);
		}
		return list;
	}
	
	@Transactional(readOnly = true)
	@Override
	public long getCountByUser() {
		//TODO 
		//服务层获取用户信息
		UserInfo userInfo=new UserInfo();
		
		return recordDao.getCount(userInfo);
	}


	@Transactional(readOnly = true)
	@Override
	public List<Record> getListByAnnounId(int announ_id,int currentIndex,int pageSize) {
		return recordDao.getListByAnnounId(announ_id,currentIndex,pageSize);
	}
	@Transactional(readOnly = true)
	@Override
	public int getCountByAnnoun(int announ_id) {
		return recordDao.getCount(announ_id);
	}


	@Transactional(readOnly = true)
	public boolean isExists(UserInfo user, Announcement announ) {
		return recordDao.isExists(user, announ);
	}

}
