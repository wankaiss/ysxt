package com.wondersgroup.qyws.tjfx.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public  class StringUtil {

	/**
	 * 根据link 地址获取所有占位符的placehold, 占位符形如 #{placehold}
	 * 
	 * @param link
	 *            chart的set节点的link地址,如
	 *            JavaScript:check_required_view(\"#{id}\",\"#{name}\",\"#{name}\
	 *            " )
	 * @return 含有占位符的所有placehold,上述例子对应的结果为"id","name","name"数组
	 */
	public static String[] getPlaceHoldArray(String link) {
		if (StringUtils.isEmpty(link)) {
			return null;
		}
		String[] result = new String[StringUtils.countMatches(link, "#{")];
		int start = 0, end = 0;
		for (int i = 0; i < result.length; i++) {
			start = StringUtils.indexOf(link, "#{", end);
			end = StringUtils.indexOf(link, "}", start);
			result[i] = StringUtils.substring(link, start + 2, end);
		}
		return result;
	}

	public static String replacePlaceHold(Map model, String link) {
		String[] placeholds = StringUtil.getPlaceHoldArray(link);
		for (String placehold : placeholds) {
			try {
				link = StringUtils.replaceOnce(link, "#{" + placehold + "}",
						(String) model.get(placehold));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return link;
	}

	/**
	 * 将一个指定格式的字符串转换为map
	 * 
	 * @param text
	 * @return
	 */

	public static Map string2map(String text) {
		Map map = new HashMap();

		String sub = ",";
		int count = count(text, sub);
		int i = 0;
		do {
			int index = text.indexOf(",");
			String text2 = text.substring(0, index);
			int index2 = text2.indexOf(":");
			String key = text2.substring(0, index2);
			String value = text2.substring(index2 + 1);
			map.put(key, value);
			text = text.substring(index + 1);
			i++;
		} while (i < count);
		int index2 = text.indexOf(":");
		String key = text.substring(0, index2);
		String value = text.substring(index2 + 1);
		map.put(key, value);
		return map;
	}

	/**
	 * 判断一个字符在字符串中出现的次数
	 * 
	 * @param text
	 * @param sub
	 * @return
	 */

	public static int count(String text, String sub) {
		int count = 0, start = 0;
		while ((start = text.indexOf(sub, start)) >= 0) {
			start += sub.length();
			count++;
		}
		return count;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger log = Logger.getLogger(StringUtil.class);

		// 1.测试getPlaceHoldArray
		String str = "javascript:link(#{id},#{name})";
		for (String s : getPlaceHoldArray(str)) {
			log.debug(s);
		}

	}

}
