package cn.ncss.jym.messagebox.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ***********************
 * 
 *  公告类型
 *
 * ***********************
 * @author kyrin [2015年3月13日]
 *
 */
@Table(name="ANNOUN_TYPE")
@Entity
public class AnnounType implements Serializable{
	private static final long serialVersionUID = -3785062172284268334L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="ANNOUN_TYPE",length=32,nullable=false,unique=true)
	private String type;
	public AnnounType(){
		
	}
	public AnnounType(String type){
		this.type=type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
