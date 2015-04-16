package cn.ncss.jym.messagebox.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.ThreadFactory.ThreadFactory;
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
	@SuppressWarnings("static-access")
	@Override
	@Transactional(readOnly=true)
	public Map<String,Long> getAllInfo() {
		
		Future<Long> record_num=ThreadFactory.getInstance().executor.submit(new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				return recordService.getCountByUser();
			}
		});
		
		Future<Long> publish_num=ThreadFactory.getInstance().executor.submit(new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				return announcementService.getCountByUser();
			}
		});
		
		Future<Long> receive_num=ThreadFactory.getInstance().executor.submit(new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				return announcementService.getReceiveCount();
			}
		});
		
		Map<String,Long> map=new HashMap<String, Long>();
		try {
			map.put("publish_num",publish_num.get());
			map.put("receive_num",receive_num.get());
			map.put("record_num", record_num.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
