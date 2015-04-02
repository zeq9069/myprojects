package cn.ncss.jym.messagebox.service.impl;

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
	public List<Record> getListByUId(String u_id,int currentIndex,int pageSize) {
		return recordDao.getListByUId(u_id,currentIndex,pageSize);
	}
	
	@Transactional(readOnly = true)
	@Override
	public long getCount(String u_id) {
		return recordDao.getCount(u_id);
	}


	@Transactional(readOnly = true)
	@Override
	public List<Record> getListByAnnounId(int announ_id,int currentIndex,int pageSize) {
		return recordDao.getListByAnnounId(announ_id,currentIndex,pageSize);
	}
	@Transactional(readOnly = true)
	@Override
	public int getCount(int announ_id) {
		return recordDao.getCount(announ_id);
	}


	@Transactional(readOnly = true)
	public boolean isExists(UserInfo user, Announcement announ) {
		return recordDao.isExists(user, announ);
	}

}
