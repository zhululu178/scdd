package cn.scdd.jxc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTool {
	final static String REGEX = "\\d+(\\.\\d+)?";
	
	/**
	 * 获得字符串中第一个串数字字符
	 * @param str
	 * @return
	 */
	public static String subSetFirstNum(String str) {
		String reStr = null;
        Pattern pattern =  Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()) {
        	reStr = matcher.group();
        	break;
        }
        return reStr;
	}
	
	/**
	 * 获得字符串中最后一串数字字符
	 * @param str
	 * @return
	 */
	public static String subSetLastNum(String str) {
		String reStr = null;
        Pattern pattern =  Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()) {
        	reStr = matcher.group();
        }
        return reStr;
	}
}