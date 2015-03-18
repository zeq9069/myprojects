package cn.ncss.jym.messagebox.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ***********************
 * 
 * 关系表：group与userInfo的对应关系
 *
 * ***********************
 * 
 * @author kyrin [2015年3月13日]
 *
 */
@Entity
@Table(name = "RELATION")
public class Relation implements Serializable{
	private static final long serialVersionUID = 8111482652825927544L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "RELATION_ID")  
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="GROUP_ID")
	private Group group;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	private UserInfo userInfo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
