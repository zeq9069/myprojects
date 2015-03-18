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

	public boolean add(Record record);

	public boolean delete(Record record);

	public List<Record> getListByUId(String u_id);

	public List<Record> getListByAnnounId(String announ_id);

	public boolean isExists(UserInfo user, Announcement announ);

}
