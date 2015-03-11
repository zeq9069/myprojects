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
	
	public boolean add(Record record);

	public boolean delete(Record record);

	public List<Record> getListByUId(String u_id);

	public List<Record> getListByAnnounId(String announ_id);
	

}
