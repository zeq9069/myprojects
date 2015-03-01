package com.zhiweifenxi.web.pojo;

import java.io.Serializable;

/**
 * 已经爬取的URL
 * @author kyrin
 */
public class CrawlerURL implements Serializable{

	private static final long serialVersionUID = -3608306780191426832L;
	
	private String  url;
	private String from_site;
	private int type;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFrom_site() {
		return from_site;
	}
	public void setFrom_site(String from_site) {
		this.from_site = from_site;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	
	public enum Type{
		JOB(1)  ,COMPANY(2);
		int value;
		Type(int value){
			this.value=value;
		}
		public int value(){
			return value;
		}
		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	

}
