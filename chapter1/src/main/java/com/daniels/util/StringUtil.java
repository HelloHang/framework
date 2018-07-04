package com.daniels.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: Daniels Gao
 * @date: 2018/7/3 21:57
 */
public class StringUtil {

  public static boolean isEmpty(String str) {
    if (str != null) {
      str = str.trim();
    }
    return StringUtils.isEmpty(str);
  }

  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }
}
