package com.zhiweifenxi.web.util;

public class StringUtil {
	
	
	public static boolean isEmpty(String value){
		if(value==null || value.trim().equals("")){
			return true;
		}
		return false;
	}
	
	public static boolean isNull(Object obj){
		return obj==null?true:false;
	}
}
