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
 * ****************************
 * 
 *   用户组与公告的对应关系
 *
 * ****************************
 * @author kyrin [2015年3月13日]
 *
 */
@Entity
@Table(name="GROUP_ANNOUN")
public class Group_announ implements Serializable{
	
	private static final long serialVersionUID = -7551580641315696227L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="GA_ID",length=32)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="ANNOUN_ID")
	private Announcement announ;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="GROUP_ID")
	private Group group;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Announcement getAnnoun() {
		return announ;
	}


	public void setAnnoun(Announcement announ) {
		this.announ = announ;
	}


	public Group getGroup() {
		return group;
	}


	public void setGroup(Group group) {
		this.group = group;
	}
	
	
	

}
