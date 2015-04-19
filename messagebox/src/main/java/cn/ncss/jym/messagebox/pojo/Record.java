package cn.ncss.jym.messagebox.pojo;

import java.io.Serializable;
import java.util.Date;

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

import org.joda.time.DateTime;

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
public class Record implements Serializable,Cloneable{

	private static final long serialVersionUID = -495618146977401328L;

	@Id
	@Column(name = "ID", length = 32)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "U_ID")
	private UserInfo user;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ANNOUN_ID")
	private Announcement announ;

	@Column(name="DATE")
	private Date date=new DateTime().toDate();
	
	/*
	 * 查看状态：0->未读，1->已读
	 * 默认未读
	 */
	@Column(name="STATUS",length=1,nullable=false)
	private int status=RecordType.UNREAD.getValue();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	@Override
	public Record clone() throws CloneNotSupportedException {
		return (Record) super.clone();
	}
	
	

	public enum RecordType{
		READ(1),UNREAD(0);
		private int value;
		RecordType(int value){
			this.value=value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	
}
