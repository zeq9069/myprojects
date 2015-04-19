package cn.ncss.jym.messagebox.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.system.pojo.TargetSchool;

/**
 * ***********************
 * 
 *   公告服务层接口
 *
 * ***********************
 * @author kyrin [2015年3月11日]
 *
 */
public interface AnnouncementService {

	public Map<String,String> create (Announcement announ,List<TargetSchool> targetSchoolList)  throws InterruptedException, ExecutionException, Exception;
	
	public boolean delete(Announcement announ);

	public boolean update(Announcement announ);

	public Announcement get(int id);

	public List<Announcement> getListByUser(int currentIndex,int pageSize);
	
	/**
	 * 获取用户发布公告的数量
	 * @param publisherId 发布者ID
	 * @return
	 */
	public long getCount();
}
