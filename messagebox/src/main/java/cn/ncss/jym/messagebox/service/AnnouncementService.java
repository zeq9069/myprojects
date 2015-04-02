package cn.ncss.jym.messagebox.service;

import java.util.List;
import java.util.Map;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.UserInfo;

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

	public void create(Announcement announ);
	
	public boolean delete(Announcement announ);

	public boolean update(Announcement announ);

	public Announcement get(int id);

	public List<Announcement> getListByType(String publisherId,String type,int currentIndex,int pageSize );

	public List<Announcement> getListByUser(String publisherId,int currentIndex,int pageSize);

	
	/**
	 * 获取用户发布公告的数量
	 * @param publisherId 发布者ID
	 * @return
	 */
	public long getCountByUser(String publisherId);
	
	
	public long getAnnounByViews(Announcement announ);
	
	/**
	 * 学校用户查询所有发送给自己的公告
	 * @param typeList 院校类型
	 * @param yxdm 院校代码
	 * @return
	 */
	public long getCount(List<String> typeList,String yxdm);
	
	/**
	 * 省用户查询所有发送给自己的公告
	 * @param provinceCode 省代码
	 * @return
	 */
	public long getCount(String provinceCode);
	
	/**
	 * 院系用户查询所有发送给自己的公告
	 * @param provinceCode
	 * @return
	 */
	public long getCount(String yxdm,String szyx);
	

}
