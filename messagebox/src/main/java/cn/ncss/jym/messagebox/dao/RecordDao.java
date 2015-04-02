package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;

/**
 * *************************
 * 	   公告查看记录持久层
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
public interface RecordDao {

	public boolean create(Record record);

	public boolean delete(Record record);

	public List<Record> getListByUId(String u_id,int currentIndex,int pageSize);
	
	public List<Record> getListByUId(String u_id);
	
	public long getCount(String u_id);

	public List<Record> getListByAnnounId(int announ_id,int currentIndex,int pageSize);
	
	public int getCount(int announ_id);
	
	public int getAnnounByViews(int announ_id);

	public boolean isExists(UserInfo user, Announcement announ);

}
