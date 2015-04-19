package cn.ncss.jym.messagebox.service;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Announcement;
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

	/**
	 * 更改查看状态,更改为“已经查看”
	 * @param announ_id 
	 * @param status
	 * @return
	 */
	public boolean updateStatus(int announ_id);
	
	/**
	 * 获取已经查看过的公告
	 * @return
	 */
	public List<Announcement> getListByView();
	
	public List<Announcement> getListByView(int currentIndex,int pageSize);
	
	public long getCountByView();
	
	
	/**
	 * 获取未查看过的公告
	 * @return
	 */
	public List<Announcement> getListByNotView();
	
	public List<Announcement> getListByNotView(int currentIndex,int pageSize);
	
	public long getCountByNotView();
	
	/**
	 * 获取接收到的公告
	 * @return
	 */
	
	public List<Announcement> getList(int currentIndex,int pageSize);
	
	public long getCount();
}
