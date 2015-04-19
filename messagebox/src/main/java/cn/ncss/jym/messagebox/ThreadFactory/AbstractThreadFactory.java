package cn.ncss.jym.messagebox.ThreadFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.security.auth.callback.Callback;

/**
 * ***********************
 * 
 * 抽象类
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月16日]
 *
 */
public abstract class AbstractThreadFactory {

	public static ExecutorService executor=Executors.newCachedThreadPool();
	
	public  static <T> Future<T> submit(Callable<T> call){
		return executor.submit(call);
	}
	
	public static  void shutdown(){
		executor.shutdown();
	}
	
}
