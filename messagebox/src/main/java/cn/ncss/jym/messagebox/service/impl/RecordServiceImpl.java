package cn.ncss.jym.messagebox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.dao.RecordDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.pojo.Record.RecordType;
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
	
	@Autowired
	private AnnouncementDao announcementDao;

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


	@Transactional(readOnly=false)
	@Override
	public boolean updateStatus(int announ_id) {
		//TODO
		UserInfo userInfo=new UserInfo();
		Announcement announ=announcementDao.get(announ_id);
		Record record=recordDao.getRecord(announ, userInfo);
		record.setStatus(RecordType.READ.getValue());
		return recordDao.updateStatus(record);
	}

	@Override
	public List<Announcement> getListByView() {
		UserInfo userInfo=new UserInfo();
		List<Record> list=recordDao.getListByStatus(userInfo, RecordType.READ.getValue());
		List<Announcement> announList=new ArrayList<Announcement>();
		if(list==null){
			return announList;
		}
		for(Record record:list){
			announList.add(record.getAnnoun());
		}
		return announList;
	}

	@Override
	public List<Announcement> getListByView(int currentIndex, int pageSize) {
		UserInfo userInfo=new UserInfo();
		List<Record> list=recordDao.getListByStatus(userInfo, RecordType.READ.getValue(),currentIndex,pageSize);
		List<Announcement> announList=new ArrayList<Announcement>();
		if(list==null){
			return announList;
		}
		for(Record record:list){
			announList.add(record.getAnnoun());
		}
		return announList;
	}

	@Override
	public long getCountByView() {
		UserInfo userInfo=new UserInfo();
		return recordDao.getCountByStatus(userInfo, RecordType.READ.getValue());
	}

	@Override
	public List<Announcement> getListByNotView() {
		UserInfo userInfo=new UserInfo();
		List<Record> list=recordDao.getListByStatus(userInfo, RecordType.UNREAD.getValue());
		List<Announcement> announList=new ArrayList<Announcement>();
		if(list==null){
			return announList;
		}
		for(Record record:list){
			announList.add(record.getAnnoun());
		}
		return announList;
	}

	@Override
	public List<Announcement> getListByNotView(int currentIndex, int pageSize) {
		UserInfo userInfo=new UserInfo();
		List<Record> list=recordDao.getListByStatus(userInfo, RecordType.UNREAD.getValue(),currentIndex,pageSize);
		List<Announcement> announList=new ArrayList<Announcement>();
		if(list==null){
			return announList;
		}
		for(Record record:list){
			announList.add(record.getAnnoun());
		}
		return announList;
	}

	@Override
	public long getCountByNotView() {
		UserInfo userInfo=new UserInfo();
		return recordDao.getCountByStatus(userInfo, RecordType.UNREAD.getValue());
	}

	@Override
	public List<Announcement> getList(int currentIndex, int pageSize) {
		UserInfo userInfo=new UserInfo();
		List<Record> list=recordDao.getList(userInfo,currentIndex,pageSize);
		List<Announcement> announList=new ArrayList<Announcement>();
		if(list==null){
			return announList;
		}
		for(Record record:list){
			announList.add(record.getAnnoun());
		}
		return announList;
	}

	@Override
	public long getCount() {
		UserInfo userInfo=new UserInfo();
		return recordDao.getCount(userInfo);
	}
	
	
	
	public boolean isExists(UserInfo user, Announcement announ) {
		return recordDao.isExists(user, announ);
	}

}
