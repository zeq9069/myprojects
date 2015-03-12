package cn.ncss.jym.messagebox.service;

import cn.ncss.jym.messagebox.system.pojo.SystemInfo;

/**
 * ***********************
 * 
 *  系统服务接口
 *
 * ***********************
 * @author kyrin [2015年3月12日]
 *
 */
public interface SystemService {

	/**
	 * 获取所有的统计信息（群组数量、以及每个群组的用户数、目前上线的公告数量、下线公告的数量）
	 */
	public SystemInfo getAllInfo();

}
