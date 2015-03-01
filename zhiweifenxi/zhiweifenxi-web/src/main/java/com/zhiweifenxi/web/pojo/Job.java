package com.zhiweifenxi.web.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *职位
 * @author kyrin
 */
@Entity
@Table(name="zwfx_job")
public class Job implements Serializable{

	private static final long serialVersionUID = 3153145106526632086L;
	
	/*
	 * 职位发布的URL
	 */
	@Id
	private String url;
	
	/*
	 * 职位名称
	 */
	@Column(name="TITLE",nullable=false,length=255)
	private String title;

	/*
	 * 薪资
	 */
	@Column(name="WAGE",nullable=false,length=32)
	private String wage;
	
	/*
	 * 职位要求
	 */
	@Column(name="JOB_REQUEST",nullable=true)
	private String job_request;
	
	/*
	 * 职位详情
	 */
	@Column(name="JOB_DETAIL",nullable=true)
	private String job_detail;
	
	/*
	 * 发布时间
	 */
	@Column(name="JOB_DATE",nullable=false)
	private String pub_date;
	
	/*
	 * 招聘网站的URL
	 */
	@Column(name="FROM_SITE",nullable=false)
	private String from_site;
	
	/*
	 * 公司信息
	 */
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity=Company.class)
	private Company  company;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWage() {
		return wage;
	}

	public void setWage(String wage) {
		this.wage = wage;
	}

	public String getJob_request() {
		return job_request;
	}

	public void setJob_request(String job_request) {
		this.job_request = job_request;
	}

	public String getJob_detail() {
		return job_detail;
	}

	public void setJob_detail(String job_detail) {
		this.job_detail = job_detail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	public String getFrom_site() {
		return from_site;
	}

	public void setFrom_site(String from_site) {
		this.from_site = from_site;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
