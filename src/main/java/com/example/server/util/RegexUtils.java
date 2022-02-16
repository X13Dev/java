package com.example.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex Utils
 */
public class RegexUtils {

    /**
     * 正则表达式通用匹配
     * 
     * @param regexExpre 正则表达式
     * @param testStr 待测试字符串
     * @return 检测结果
     */
    public static boolean genericMatcher(String regexExpre, String testStr) {
        Pattern pattern = Pattern.compile(regexExpre);
        Matcher matcher = pattern.matcher(testStr);
        return matcher.matches();
    }
}
