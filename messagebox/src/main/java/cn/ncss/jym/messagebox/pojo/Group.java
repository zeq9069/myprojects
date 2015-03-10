package cn.ncss.jym.messagebox.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * *************************
 * 		用户组
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
@Entity
@Table(name = "GROUP_INFO")
public class Group implements Serializable {

	private static final long serialVersionUID = 2758570929863603466L;

	/*
	 * 组ID
	 */
	@Id
	@Column(name = "ID", length = 32)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/*
	 * 用户组名
	 */
	@Column(name = "GROUP_NAME", length = 24, updatable = false, nullable = false, unique = true)
	private String groupName;

	/*
	 * 是否上线
	 */
	@Column(name = "ONLINE", length = 24, updatable = false, nullable = false)
	private String online = "true";

	/**
	 * 自动简历userInfo与group的映射关系表
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "group_user", joinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "u_id", referencedColumnName = "ID"), })
	private Set<UserInfo> users = new HashSet<UserInfo>();

	/**
	 * 自动建立group与announcement的关系表
	 */
	@ManyToMany(mappedBy = "groups", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Announcement> announs = new HashSet<Announcement>();

	public Set<Announcement> getAnnouns() {
		return announs;
	}

	public void setAnnouns(Set<Announcement> announs) {
		this.announs = announs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(Set<UserInfo> users) {
		this.users = users;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

}
