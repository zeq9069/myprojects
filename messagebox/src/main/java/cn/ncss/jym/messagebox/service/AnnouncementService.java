package cn.ncss.jym.messagebox.service;

import java.util.List;
import cn.ncss.jym.messagebox.pojo.Announcement;

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

	public List<Announcement> getListByType(String type,int currentIndex,int pageSize );

	public List<Announcement> getListByUser(int currentIndex,int pageSize);
	
	/**
	 * 获取用户发布公告的数量
	 * @param publisherId 发布者ID
	 * @return
	 */
	public long getCountByUser();
	
	
	public long getAnnounByViews(Announcement announ);
	
	/**
	 * 学校用户查询所有发送给自己的公告
	 * @param typeList 院校类型
	 * @param yxdm 院校代码
	 * @return
	 */
//	public long getCount(List<String> typeList,String yxdm);
//	
//	/**
//	 * 省用户查询所有发送给自己的公告
//	 * @param provinceCode 省代码
//	 * @return
//	 */
//	public long getCount(String provinceCode);
//	
//	/**
//	 * 院系用户查询所有发送给自己的公告
//	 * @param provinceCode
//	 * @return
//	 */
//	public long getCount(String yxdm,String szyx);
//	
	
	/**
	 * 
	 * 当前用户(省、院系、院校)获取所有接收到的公告
	 * @param currentindex
	 * @param pageSize
	 * @return
	 */
	public List<Announcement> getReceiveList(int currentindex,int pageSize);
	
	/**
	 * 查询还没有查看的公告
	 * @return
	 */
	public List<Announcement> getAnnounsByNot();
	
	/**
	 * 查询当前用户(省、院系、院校)接收导的所有公告
	 * @return
	 */
	public long getReceiveCount();

}
