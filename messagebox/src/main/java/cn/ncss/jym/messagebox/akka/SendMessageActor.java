package cn.ncss.jym.messagebox.akka;

import org.springframework.context.ApplicationContext;

import cn.ncss.jym.messagebox.dao.UserInfoDao;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * ***********************
 * 
 *  简单往数据库中插入数据:
 *  
 *  通过与spring整合，获取bean来
 *  操作数据库。当然@Transaction将失去作用
 *  直接通过sessionFactory来获取session操作事务
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月25日]
 *
 */
public class SendMessageActor extends UntypedActor{
	
	LoggingAdapter logger=Logging.getLogger(getContext().system(), SendMessageActor.class);
	private ApplicationContext applicationContext;

	public  SendMessageActor(ApplicationContext applicationContext) {
		this.applicationContext=applicationContext;
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof String){
			UserInfoDao userInfoDao=(UserInfoDao) applicationContext.getBean("userInfoDao");
			logger.info("The users count is {}",userInfoDao.getCount());
		}
	}
}
