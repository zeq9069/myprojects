package cn.ncss.jym.messagebox.ThreadFactory;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ncss.jym.messagebox.dao.AnnouncementDao;
import cn.ncss.jym.messagebox.dao.RecordDao;
import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Record;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.AnnouncementService;
import cn.ncss.jym.messagebox.service.RecordService;
import cn.ncss.jym.messagebox.service.UserInfoService;
import cn.ncss.jym.messagebox.system.pojo.TargetSchool;
import freemarker.log.Logger;

/**
 * **************************
 * 
 * 当前用户的级别处于（1000-99999）中级
 * 
 *  把message发送到每一个目标用户
 *
 *
 * **************************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月17日]
 *
 */
public class SendMessageTask implements Callable<Boolean>{
	
	private static Logger logger=Logger.getLogger(SendMessageTask.class.getName());

	public SendMessageTask(Announcement announ,UserInfo userInfo,List<TargetSchool> targetSchoolList,UserInfoService userInfoService,RecordService recordService) {
		
	}
	@Override
	public Boolean call() throws Exception {
		
		return true;
	}
	
}
