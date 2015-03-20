package cn.ncss.jym.messagebox.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;
import cn.ncss.jym.messagebox.utils.JsonDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * *************************
 *       公告
 *       
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
@Entity
@Table(name = "ANNOUNCEMENT")
public class Announcement implements Serializable {

	private static final long serialVersionUID = 7965777473746621699L;

	/*
	 * id
	 */
	@Id
	@Column(name = "ID", length = 32)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/*
	 * 公告标题
	 */
	@Column(name = "TITLE", length = 255, updatable = false, nullable = false)
	private String title;

	/*
	 * 发布时间
	 */
	@Column(name = "DATE", nullable = false, length = 255)
	private Date date = new DateTime().toDate();

	/*
	 * 发布者
	 */
	@Column(name = "PUBLISHER", nullable = false, length = 64)
	private String publisher;

	/*
	 * 发布内容
	 */
	@Column(name = "CONTENT", nullable = false)
	private String content;

	/*
	 * 公告类型
	 */
	@Column(name = "TYPE", nullable = false, length = 32)
	private String type;

	/*
	 * 是否上线
	 */
	@Column(name = "ONLINE", nullable = false, length = 32)
	private String online;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonSerialize(using=JsonDate.class)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

}
