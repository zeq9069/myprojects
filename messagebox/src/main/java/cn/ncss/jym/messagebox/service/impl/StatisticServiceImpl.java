package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.RecordService;
import cn.ncss.jym.messagebox.service.StatisticService;
import cn.ncss.jym.messagebox.service.UserInfoService;

/**
 * ***********************
 * 
 *   统计服务
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年3月27日]
 *
 */
@Service
public class StatisticServiceImpl implements StatisticService{

	@Autowired
	private RecordService recordService;
	
	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	/*
	 * 统计登录用户发送的公告数、接收的公告数、未查阅的公告数
	 */
	@Override
	@Transactional(readOnly=true)
	public Map<String,Long> getAllInfo() {
		long publish_num=announcementService.getCountByUser();
		long receive_num=announcementService.getReceiveCount();
		long record_num=recordService.getCountByUser();
		Map<String,Long> map=new HashMap<String, Long>();
		map.put("publish_num",publish_num);
		map.put("receive_num",receive_num);
		map.put("record_num", record_num);
		return map;
	}
	
}
