package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Announcement;

/**
 * *************************
 * 
 *      公告持久层
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
public interface AnnouncementDao {
	
	public int create(Announcement announ);

	public boolean delete(Announcement announ);

	public boolean update(Announcement announ);

	public Announcement get(int id);

	public List<Announcement> getListByType(String publisherId,String type,int currentIndex,int pageSize);
	
	public List<Announcement> getListByUser(String publisherId,int currrentIndex,int pageSize);
	
	/**
	 * 获取用户发布公告的数量
	 * @param publisherId 发布者ID
	 * @return
	 */
	public long getCountByUser(String publisherId);
	
	/**
	 * 学校用户查询所有发送给自己的公告
	 * @param typeList 院校类型
	 * @param yxdm 院校代码
	 * @return
	 */
	public int getCount(List<String> typeList,String yxdm);
	
	/**
	 * 省用户查询所有发送给自己的公告
	 * @param provinceCode 省代码
	 * @return
	 */
	public int getCount(String provinceCode);
	
	/**
	 * 院系用户查询所有发送给自己的公告
	 * @param provinceCode
	 * @return
	 */
	public int getCount(String yxdm,String szyx);
	
	
}
