package com.zhiweifenxi.web.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author kyrin
 *
 */

@Table(name="USER_INFO")
@Entity
public class User  implements Serializable{

	 
	private static final long serialVersionUID = 825554810777653071L;
	@Id
	@Column(name = "user_id", length = 22)
	private String id;

	@Column(name="user_name",length=60,nullable=false,unique=true)
	private String name;
	
	@Column(name="user_password")
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
