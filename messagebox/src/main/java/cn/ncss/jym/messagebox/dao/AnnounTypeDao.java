package cn.ncss.jym.messagebox.dao;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.AnnounType;

/**
 * ***********************
 *    公告类型
 *
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月14日]
 *
 */
public interface AnnounTypeDao {

	
	public void create(AnnounType type);
	
	public List<AnnounType> queryList(String key);
	
	public AnnounType find(String type);
	
}
