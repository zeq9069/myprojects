package cn.ncss.jym.messagebox.system.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * ***********************
 * 
 * 系统服务统计实体
 *
 * ***********************
 * @author kyrin [2015年3月12日]
 *
 */
public class SystemInfo implements Serializable{
	
	private static final long serialVersionUID = -4419006768398585183L;
	private Map<String,Integer> groups=new HashMap<String, Integer>();
	private Map<String,Integer> announs=new HashMap<String, Integer>();
	
	public SystemInfo(Map<String,Integer> groups,Map<String,Integer> announs){
		if(groups!=null){
			this.groups=groups;
		}
		if(announs!=null){
			this.announs=announs;
		}
	}
	
	public Map<String, Integer> getGroups() {
		return groups;
	}
	public void setGroups(Map<String, Integer> groups) {
		this.groups = groups;
	}
	public Map<String, Integer> getAnnouns() {
		return announs;
	}
	public void setAnnouns(Map<String, Integer> announs) {
		this.announs = announs;
	}
	
	
}
