package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GlobalUtil {

	private GlobalUtil() {

	}

	public static String formatNumber(BigDecimal obj) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
		String formatStr = decimalFormat.format(obj);
		return formatStr;
	}

	public static boolean isNotNull(String str) {
		if (str != null && str.equals("") == false &&str.equalsIgnoreCase("null")==false) {
			return true;
		}
		return false;
	}
	
	public static boolean isNull(String str) {
		if (str == null || str.equals("") || str.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}
	
	
	public static String nullToStr(String str) {
		if (isNull(str)) {
			return "";
		}
		return str;
	}
	
	/**
	 * Title: UserType 
	 * Description: 系统用户的枚举类型  
	 * @author wjh
	 * @date 2020年7月28日
	 */
	public enum UserType{
		sysAdmin,regUser
	}
}
