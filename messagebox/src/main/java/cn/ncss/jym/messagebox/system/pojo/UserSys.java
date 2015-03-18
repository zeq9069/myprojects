package cn.ncss.jym.messagebox.system.pojo;

import java.io.Serializable;
import java.util.List;

import cn.ncss.jym.messagebox.pojo.Group;
import cn.ncss.jym.messagebox.pojo.UserInfo;

/**
 * ***********************
 * 
 * 用户json实体，用于前段展示
 *
 * ***********************
 * @author kyrin [2015年3月18日]
 *
 */
public class UserSys implements Serializable{

	
	private static final long serialVersionUID = -296444032659677535L;
	
	private UserInfo user;
	private List<Group> groups;
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	
	

}
