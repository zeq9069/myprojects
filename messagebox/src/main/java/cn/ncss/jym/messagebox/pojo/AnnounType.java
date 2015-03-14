package cn.ncss.jym.messagebox.pojo;

import java.io.Serializable;

/**
 * ***********************
 * 
 *  公告类型
 *
 * ***********************
 * @author kyrin [2015年3月13日]
 *
 */
public class AnnounType implements Serializable{
	private static final long serialVersionUID = -3785062172284268334L;
	private int id;
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
