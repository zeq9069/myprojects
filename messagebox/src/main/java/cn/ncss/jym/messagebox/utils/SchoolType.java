package cn.ncss.jym.messagebox.utils;
/**
 * ************************
 *  
 *  学校类型枚举
 *
 * ***********************
 * @author kyrin kyrincloud@qq.com 
 *
 * @date [2015年4月2日]
 *
 */
public enum SchoolType {
	//所有高校、211高校、985高校、省属高校、部属高校、独立院校、新建本科院校
	SCHOOL_ALL("school_all"),
	SCHOOL_211("school_211"),
	SCHOOL_985("school_985"),
	SCHOOL_BY_PROVINCE("shcool_by_province"),
	SCHOOL_BY_MINISTRY("school_by_ministry"),
	SCHOOL_INDEPENDENT("school_independent"),
	SCHOOL_BACHELOR_NEW("school_bachelor_new");
	String value;
	SchoolType(String value){
		this.value=value;
	}
	public String value(){
		return value;
	}
	public String getString(String school_type){
		switch (school_type) {
		case "school_all":
			return "所有高校";
		case "school_211":
			return "211高校";
		case "school_985":
			return "985高校";
		case "school_by_province":
			return "省属高校";
		case "school_by_ministry":
			return "部属高校";
		case "school_independent":
			return "独立高校";
		case "school_bachelor_new":
			return "新建本科高校";
		default:
			return "";
		}
	}
}
