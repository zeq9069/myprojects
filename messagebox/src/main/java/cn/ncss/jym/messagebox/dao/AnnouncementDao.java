package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.UserInfo;

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

	public List<Announcement> getListByType(UserInfo userInfo,String type,int currentIndex,int pageSize);
	
	public List<Announcement> getListByUser(UserInfo userInfo,int currrentIndex,int pageSize);
	
	/**
	 * 获取用户发布公告的数量
	 * @param publisherId 发布者ID
	 * @return
	 */
	public long getCountByUser(UserInfo userInfo);
	
	/***********************************************
	 * 
	 * 用户查询发给自己的公告的一些规则：
	 * 
	 *   暂定只能 省和学校发送公告 
	 * 
	 * <1>省
	 * 需要满足一下条件：
	 * 
	 * publish_role='province' and targetProvinceCode=本省代码
	 * 
	 * <2>学校
	 * 需要满足一下两个条件：
	 * （1）publish_role='province' and publish_dm=当前用户的省代码
	 *     and ( targetYxlx………… or targetYxdm=当前用户学校代码 )
	 * （2）publish_role='school' and targetYxdm=当前用户的院校代码
	 * 
	 * <3>院系
	 * 学要满足以下条件：
	 *    publish_role='school' and publish_dm=当前用户的院校代码
	 *     and targetSzyx=当前用户的szyx
	 *   
	 * 
	 * 
	 **********************************************/
	
	/**
	 * 学校用户查询所有发送给自己的公告
	 * @param typeList 院校类型
	 * @param yxdm 院校代码
	 * @return
	 */
	public int getCount(List<String> typeList,String provinceCode,String yxdm,UserInfo userInfo);
	
	public List<Announcement> getReceiveByYxdm(List<String> typeList,String provinceCode,String yxdm,UserInfo userInfo,int currentIndex,int pageSize);
	
	public List<Announcement> getReceiveByYxdm(List<String> typeList,String provinceCode,String yxdm,UserInfo userinfo);
	
	/**
	 * 省用户查询所有发送给自己的公告
	 * @param provinceCode 省代码
	 * @return
	 */
	public int getCount(String provinceCode);
	
	public List<Announcement> getReceiveByProvince(String provinceCode,int currentIndex,int pageSize);
	
	public List<Announcement> getReceiveByProvince(String provinceCode);
	
	/**
	 * 院系用户查询所有发送给自己的公告
	 * @param provinceCode
	 * @return
	 */
	public int getCount(String yxdm,String szyx);
	
	public List<Announcement> getReceiveBySzyx(String yxdm,String szyx,int currentIndex,int pageSize);
	
	public List<Announcement> getReceiveBySzyx(String yxdm,String szyx);
	
}
