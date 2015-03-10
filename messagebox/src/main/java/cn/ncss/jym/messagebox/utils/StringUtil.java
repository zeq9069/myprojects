package cn.ncss.jym.messagebox.utils;

/**
 * *************************
 * 		string工具类
 * 
 * *************************
 * @author zeq [2015年3月10日]
 *
 */
public class StringUtil {

	public static boolean hasText(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

}
