package cn.ncss.jym.messagebox.akka;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

import cn.ncss.jym.messagebox.dao.UserInfoDao;
import cn.ncss.jym.messagebox.pojo.UserInfo;
import cn.ncss.jym.messagebox.service.UserInfoService;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.AskSupport;

/**
 * ***********************
 *  
 *   测试actor往数据库中插入数据时，
 *   如何进行事务的回滚
 *   
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月25日]
 *
 */
public class InsertActor extends UntypedActor{
	LoggingAdapter logger=Logging.getLogger(getContext().system(), SendMessageActor.class);
	private ApplicationContext applicationContext;
	private HttpSession session;

	public  InsertActor(ApplicationContext applicationContext,HttpSession session) {
		this.applicationContext=applicationContext;
		this.session=session;
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof String){
			UserInfoService userInfoService=(UserInfoService) applicationContext.getBean("userInfoService");
			logger.info("The insert users  is {}",userInfoService.insert(session));
		}
		
	}
}
