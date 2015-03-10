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
 * *************************
 * 
 *       公告查看记录
 * 
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
@Entity
@Table(name = "RECORD")
public class Record implements Serializable {

	private static final long serialVersionUID = -495618146977401328L;

	@Id
	@Column(name = "ID", length = 32)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "U_ID")
	private UserInfo user;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ANNOUN_ID")
	private Announcement announ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Announcement getAnnoun() {
		return announ;
	}

	public void setAnnoun(Announcement announ) {
		this.announ = announ;
	}

}
