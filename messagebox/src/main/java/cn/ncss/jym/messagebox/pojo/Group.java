package cn.ncss.jym.messagebox.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * *************************
 * 		用户组
 * 注意：要想删除级联关系表，必须去数据库进行手动配置级联
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
@Entity
@Table(name = "GROUP_INFO")
public class Group implements Serializable {

	private static final long serialVersionUID = 2758570929863603466L;

	/*
	 * 组ID
	 */
	@Id
	@Column(name = "GROUP_ID", length = 32)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/*
	 * 用户组名
	 */
	@Column(name = "GROUP_NAME", length = 32)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
