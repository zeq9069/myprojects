package com.zhiweifenxi.web.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *公司 
 * @author kyrin
 */
@Entity
@Table(name="zwfx_company")
public class Company implements Serializable{

	private static final long serialVersionUID = -349033243754139628L;
	
	/*
	 * 公司站点的url
	 */
	@Id
	private String company_url;
	
	/*
	 * 公司名
	 */
	@Column(name="name",nullable=false,length=64)
	private String name;
	
	/*
	 * 公司类型
	 */
	@Column(name="type",nullable=false,length=32)
	private String type;
	
	/*
	 * 公司地址
	 */
	@Column(name="address",nullable=false,length=64)
	private String address;
	
	/*
	 * 来自于哪一个招聘网站
	 */
	@Column(name="from_site",nullable=false,length=32)
	private String from_site;
	
	/*
	 * 公司在招聘网站的URL路径
	 */
	@Column(name="url",nullable=false,length=32)
	private String url;

	public String getCompany_url() {
		return company_url;
	}

	public void setCompany_url(String company_url) {
		this.company_url = company_url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFrom_site() {
		return from_site;
	}

	public void setFrom_site(String from_site) {
		this.from_site = from_site;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}
