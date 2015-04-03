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
/*******************************************************
 * 
 * 用户查询发给自己的公告的一些规则：
 * 
 *   暂定只能 省和学校发送公告 
 * 
 * <1>省
 * 需要满足一下条件：
 * 
 * publish_role='province' and targetProvinceCode=本省代码
 * 
 * <2>学校
 * 需要满足一下两个条件：
 * （1）publish_role='province' and publish_dm=当前用户的省代码
 *     and ( targetYxlx………… or targetYxdm=当前用户学校代码 )
 * （2）publish_role='school' and targetYxdm=当前用户的院校代码
 * 
 * <3>院系
 * 学要满足以下条件：
 *    publish_role='school' and publish_dm=当前用户的院校代码
 *     and targetSzyx=当前用户的szyx
 *   
 * 
 * 
 ********************************************************
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
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "U_ID")
	private UserInfo user;
	
	/*
	 * 发布者代码：省代码或者院校代码
	 */
	@Column(name="PUBLISH_DM",length=8,nullable=false)
	private String publish_dm;
	
	/*
	 * 发布者角色：school或者province
	 */
	@Column(name="PUBLISH_ROLE",length=16,nullable=false)
	private String publish_role;

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
	 *目标省代码列表，以逗号隔开 
	 */
	@Column(name="TARGET_PROVINCECODE")
	private String targetProvinceCode;
	
	/*
	 * 目标院校代码列表，以逗号隔开
	 */
	@Column(name="TARGET_YXDM")
	private String targetYxdm;
	
	/*
	 * 目标院系列表，以逗号隔开
	 */
	@Column(name="TARGET_DEPARTMENT")
	private String targetDepartment;
	
	/*
	 * 目标院校类型（比如211院校、985高校等的代码列表），以逗号隔开
	 */
	@Column(name="TARGET_YXLX")
	private String targetYxlx;

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

	public String getTargetProvinceCode() {
		return targetProvinceCode;
	}

	public void setTargetProvinceCode(String targetProvinceCode) {
		this.targetProvinceCode = targetProvinceCode;
	}

	public String getTargetYxdm() {
		return targetYxdm;
	}

	public void setTargetYxdm(String targetYxdm) {
		this.targetYxdm = targetYxdm;
	}

	public String getTargetDepartment() {
		return targetDepartment;
	}

	public void setTargetDepartment(String targetDepartment) {
		this.targetDepartment = targetDepartment;
	}

	public String getTargetYxlx() {
		return targetYxlx;
	}

	public void setTargetYxlx(String targetYxlx) {
		this.targetYxlx = targetYxlx;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public String getPublish_dm() {
		return publish_dm;
	}

	public void setPublish_dm(String publish_dm) {
		this.publish_dm = publish_dm;
	}

	public String getPublish_role() {
		return publish_role;
	}

	public void setPublish_role(String publish_role) {
		this.publish_role = publish_role;
	}
	
	
	
}
