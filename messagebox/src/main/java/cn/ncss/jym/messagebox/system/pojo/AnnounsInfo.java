package cn.ncss.jym.messagebox.system.pojo;

import java.io.Serializable;
import java.util.List;

import cn.ncss.jym.messagebox.pojo.Announcement;
import cn.ncss.jym.messagebox.pojo.Group;

/**
 * *********************
 *   公告json实体
 *
 * *********************
 * @author zeq [2015年3月18日]
 *
 */
public class AnnounsInfo implements Serializable {

	private static final long serialVersionUID = 7636313566541466309L;
	public Announcement announ;
	public int views = 0;
	public List<Group> groups;

	public Announcement getAnnoun() {
		return announ;
	}

	public void setAnnoun(Announcement announ) {
		this.announ = announ;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}
