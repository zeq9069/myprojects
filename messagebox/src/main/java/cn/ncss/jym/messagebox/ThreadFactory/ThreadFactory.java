package cn.ncss.jym.messagebox.ThreadFactory;
/**
 * ***********************
 *   线程工厂
 *
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月16日]
 *
 */
public class ThreadFactory extends AbstractThreadFactory{

	private static class Instance{
		static ThreadFactory factory=new ThreadFactory();
	}
	public static ThreadFactory getInstance(){
		return Instance.factory;
	}
}
