package cn.ncss.jym.messagebox.service;

import java.util.List;

import cn.ncss.jym.messagebox.pojo.AnnounType;


/**
 * ***********************
 *    公告类型服务
 *
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月14日]
 *
 */
public interface AnnounTypeService {
	
	public void create(String type);
	
	public List<AnnounType> announTypeList(String key);
	
	public boolean isExist(String type);

}
