package cn.ncss.jym.messagebox.system.pojo;

import java.io.Serializable;

/**
 * ***********************
 * 
 * 目标院校
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月18日]
 *
 */
public class TargetSchool implements Serializable{

	private static final long serialVersionUID = 220638005079126009L;
	public TargetSchool() {
	}
	
	//yxdm
	private String id;
	//yxmc+fxmc
	private String text;
	
	//fxmc
	private String fxmc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFxmc() {
		return fxmc;
	}

	public void setFxmc(String fxmc) {
		this.fxmc = fxmc;
	}
	
	
}
