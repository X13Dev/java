package com.example.server.util;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * String Utils
 */
public class StringUtils {

	private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

	/**
	 * decodeBase64ToString
	 * @param base64 base64
	 * @param keepIllegalArgs keepIllegalArgs
	 * @return string
	 */
    public static String decodeBase64ToString(String base64, boolean keepIllegalArgs) {
    	String text = null;
    	if (base64 != null) {
        	try {
            	byte[] decoded = Base64.getDecoder().decode(base64);
            	text = new String(decoded, StandardCharsets.UTF_8);
        	}
        	catch(IllegalArgumentException ex) {
        		if (keepIllegalArgs) {
        		    text = base64;
        		} else {
					logger.error("base64 decode exception", ex);
        		}
        	}
        	catch(Exception ex) {
				logger.error("base64 decode exception", ex);
        	}
    	}
    	return text;
    }

    /**
     * encodeStringToBase64
     * @param text text
     * @return base64
     */
    public static String encodeStringToBase64(String text) {
    	String base64 = null;
    	if (text != null) {
        	byte[] bytes = text.getBytes();
            base64 = Base64.getEncoder().encodeToString(bytes);
    	}
        return base64;
	}

	/**
	 * 字符串判断是否为空或者NULL
	 * @param text 待判断字符串
	 * @return true: 空或NULL， false：不为空且不为NULL
	 */
	public static boolean stringIsEmptyOrNull(String text) {
		//当字符串去空之后不为Null则返回false，否则为true
		return text == null || (("").equals(text));
	}

	/**
	 * 将List<String>类型的集合转换为List<Integer>类型的集合
	 * @param strList 待转换的集合
	 * @return Integer类型的集合
	 */
	public static List<Integer> toIntList(List<String> strList, boolean isStrict) {
		//遍历List集合中的字符串信息，并将其转换成Integer
		if (strList == null) {
			return null;
		}
		
		List<Integer> intList = new ArrayList<>();
		for (String str : strList) {
			Integer in = null;
			try {
				in = Integer.parseInt(str);
			}
			catch (Exception ex) {
				if (isStrict) {
					throw ex;
				}
				else {
					logger.error("parse int exception", ex);
					continue;
				}
			}
			
			intList.add(in);
		}
		
		return intList;
	}
	
	public static List<Integer> toIntList(List<String> strList) {
		return toIntList(strList, true);
	}
	
	public static List<Integer> toIntList(String[] strArray, boolean isStrict) {
		if (strArray == null) {
			return null;
		}
		
		List<String> strList = Arrays.asList(strArray);
		return toIntList(strList, isStrict);
	}
	
	public static List<Integer> toIntList(String[] strArray) {
		return toIntList(strArray, true);
	}

	public static String trimToEmpty(final String str) {
		return org.apache.commons.lang3.StringUtils.trimToEmpty(str);
	}

	public static String trimToNull(final String str) {
		return org.apache.commons.lang3.StringUtils.trimToNull(str);
	}
	
	public static boolean isNotEmpty(final CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isNotEmpty(cs);
	}
	
	public static boolean isEmpty(final CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isEmpty(cs);
	}
	
	public static Integer toInteger(final String str, boolean isStrict) {
		Integer num = null;
		  try {
		    num = Integer.parseInt(str);
		  }
		  catch (Exception ex) {
			  if (isStrict) {
					throw ex;
			  }
			  else {
					logger.error("parse int exception", ex);
			  }
		  }
		  
		  return num;
	}
	
	
	public static Integer toInteger(final String str) {
		return toInteger(str, true);
	}
	
	
	@SuppressWarnings("rawtypes")
	public static String listToString(List list, char separator) {
		int listLen = list.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listLen; i++) {
            sb.append(list.get(i));
            if (i < listLen - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

}
