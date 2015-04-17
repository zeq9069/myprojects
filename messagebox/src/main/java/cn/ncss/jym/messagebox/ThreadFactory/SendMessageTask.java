package cn.ncss.jym.messagebox.ThreadFactory;

import java.util.concurrent.Callable;

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

	@Override
	public Boolean call() throws Exception {
		//根据当前发送message的用户
		//从用户表获取所有用户（如果是省用户------>获取外省用户和本省学校用户，学校用户-------->获取本省其他学校用户和本院系用户，院系用户----->获取本院校其他院系的用户）
		//只要是message的目标用户，那就往record表中插入记录，message处于未读状态
		return null;
	}

}
