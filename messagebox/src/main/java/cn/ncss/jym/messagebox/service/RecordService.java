package cn.ncss.jym.messagebox.service;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Record;

/**
 * ***********************
 * 
 *    公告查看记录服务层接口
 *
 * ***********************
 * @author kyrin [2015年3月11日]
 *
 */
public interface RecordService {
	
	public boolean create(Record record);

	public boolean delete(Record record);

	public List<Record> getListByUId(String u_id,int currentIndex,int pageSize);
	
	public long getCount(String u_id);

	public List<Record> getListByAnnounId(int announ_id,int currentIndex,int pageSize);
	
	public int getCount(int anoun_id);
}
