package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Record;

/**
 * *************************
 * 	   公告查看记录持久层
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
public interface RecordDao {

	public void add(Record record);

	public void delete(Record record);

	public List<Record> getListByUId(String u_id);

	public List<Record> getListByAnnounId(String announ_id);

}
