package cn.scdd.jxc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTool {
	final static String REGEX = "\\d+(\\.\\d+)?";
	public static String subSetNum(String str) {
		String reStr = null;
        Pattern pattern =  Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()) {
        	reStr = matcher.group();
            break;
        }
        return reStr;
	}
}