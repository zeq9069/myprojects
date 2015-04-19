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
	
	public Record getRecord(Announcement announ,UserInfo userInfo);
	
	/**
	 * 更改查看状态
	 * @param announ_id 
	 * @param status
	 * @return
	 */
	public boolean updateStatus(Record record);
	
	/**
	 * 获取用户已查看或未查看的公告列表
	 * @param userInfo
	 * @param status 查看状态 0:未查看，1：已查看
	 * @return
	 */
	public List<Record> getListByStatus(UserInfo userInfo,int status);
	
	public List<Record> getListByStatus(UserInfo userInfo,int status,int currentIndex,int pageSize);
	
	public long getCountByStatus(UserInfo userInfo,int status);
	
	/**
	 * 获取用户接收到的公告列表
	 * @param userInfo
	 * @param status
	 * @param currentIndex
	 * @param pageSize
	 * @return
	 */
	public List<Record> getList(UserInfo userInfo,int currentIndex,int pageSize);
	
	/**
	 * 获取用户接收到的公告的数量
	 * @param status
	 * @return
	 */
	public long getCount(UserInfo userInfo);
	
	public boolean isExists(UserInfo user, Announcement announ);

}
