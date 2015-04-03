package cn.ncss.jym.messagebox.pojo;

/**
 * 用户基本信息
 */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

/**
 * ************************
 *  用户表信息
 *  
 * 数据来源于jym2，不存储用户敏感信息
 * 
 * ************************
 * @author zeq 
 *
 */
@Entity
@Table(name = "USER_INFO")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 2758570929863603466L;
	/*
	 * 绑定的学信帐号的用户ID
	 */
	@Id
	@Column(name = "USER_ID", length = 32)
	private String id="123";
	/*
	 * 绑定的学信帐号的用户名
	 */
	@Column(name = "USERNAME", length = 24, updatable = false)
	private String username;
	/*
	 * 用户类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "USER_TYPE", updatable = false)
	private UserType userType;
	/*
	 * 所在省市或高校编码
	 */
	@Column(name = "ORG_CODE", length = 8, updatable = false)
	private String orgCode="32";
	/*
	 * 工作单位或高校名称
	 */
	@Column(name = "ORG_NAME", length = 24, updatable = false)
	private String orgName;
	/*
	 * 分校名称
	 */
	@Column(name = "FXMC", length = 24, updatable = false)
	private String fxmc;
	/*
	 * 地区代码
	 */
	@Column(name = "AREA_CODE", length = 2, updatable = false)
	private String areaCode="32";
	/*
	 * 联系人姓名
	 */
	@Column(name = "REAL_NAME", length = 16)
	private String realName;
	/*
	 * 部门/院系
	 */
	@Column(name = "DEPARTMENT", length = 24)
	private String department;
	/*
	 * 职务
	 */
	@Column(name = "JOB_TITLE", length = 24)
	private String jobTitle;
	/*
	 * 办公电话
	 */
	@Column(name = "OFFICE_PHONE", length = 32)
	private String officePhone;
	/*
	 * 传真
	 */
	@Column(name = "FAX", length = 32)
	private String fax;
	/*
	 * 移动电话
	 */
	@Column(name = "MOBILE_PHONE", length = 32, updatable = false)
	private String mobilePhone;
	/*
	 * 电子邮箱
	 */
	@Column(name = "EMAIL", length = 32)
	private String email;
	/*
	 *  QQ号码
	 */
	@Column(name = "QQ", length = 16)
	private String qq;
	/*
	 * 创建时间
	 */
	@Column(name = "CREATE_DATE", nullable = false, updatable = false)
	private Date createDate = new DateTime().toDate();
	/*
	 * 最后更新时间
	 */
	@Column(name = "UPDATE_DATE")
	private Date updateDate = new DateTime().toDate();
	/*
	 * 最后登录时间
	 */
	@Column(name = "LOGIN_DATE", updatable = false)
	private Date lastLoginDate;

	/*
	 * 判断是否为院校用户
	 * @return
	 */
	public boolean isSchoolUser() {
		return UserType.school.equals(userType);
	}

	/*
	 * 判断是否为院系用户
	 * @return
	 */
	public boolean isDepartmentUser() {
		return UserType.department.equals(userType);
	}

	/*
	* 判断是否为省级用户
	* @return
	*/
	public boolean isProvinceUser() {
		return UserType.province.equals(userType);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = StringUtils.trimAllWhitespace(id);
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = StringUtils.trimAllWhitespace(orgName);
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = StringUtils.trimAllWhitespace(realName);
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = StringUtils.trimAllWhitespace(department);
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = StringUtils.trimAllWhitespace(jobTitle);
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = StringUtils.trimAllWhitespace(orgCode);
	}

	public String getFxmc() {
		return fxmc;
	}

	public void setFxmc(String fxmc) {
		this.fxmc = StringUtils.trimAllWhitespace(fxmc);
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = StringUtils.trimAllWhitespace(officePhone);
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = StringUtils.trimWhitespace(fax);
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = StringUtils.trimWhitespace(mobilePhone);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = StringUtils.trimWhitespace(email);
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = StringUtils.trimWhitespace(qq);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = StringUtils.trimWhitespace(username);
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = StringUtils.trimWhitespace(areaCode);
	}

	@Override
	public String toString() {
		String fxmc = (this.fxmc == null || "".equals(this.fxmc)) ? "" : (" " + this.fxmc);
		if (UserType.department.equals(userType)) {
			String department = (this.department == null) ? "" : (" " + this.department);
			return String.format("%s [%s%s%s]", realName, orgName, fxmc, department);
		} else {
			return String.format("%s [%s%s]", realName, orgName, fxmc);
		}
	}

	public enum UserType {
		/**
		 * 省级用户
		 */
		province,
		/**
		 * 高校级用户
		 */
		school,
		/**
		 * 院系用户
		 */
		department
	}
}
