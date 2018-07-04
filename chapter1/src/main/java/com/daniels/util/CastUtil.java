package com.daniels.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: Daniels Gao
 * @date: 2018/7/3 21:27
 */
public class CastUtil {

  public static String castString(Object object) {
    return castString(object, StringUtils.EMPTY);
  }

  public static String castString(Object object, String defaultValue) {
    return object == null ? defaultValue : String.valueOf(object);
  }

  public static double castDouble(Object object, double defaultValue) {
    double value = defaultValue;
    if (object != null) {
      String valueString = castString(object);
      if (StringUtils.isNotEmpty(valueString)) {
        value = Double.parseDouble(valueString);
      }
    }
    return value;
  }

  public static double castDouble(Object object) {
    return castDouble(object, 0);
  }

  public static long castLong(Object object, long defaultValue) {
    long value = defaultValue;
    if (object != null) {
      String stringValue = castString(object);
      if (StringUtils.isNotEmpty(stringValue)) {
        value = Long.parseLong(stringValue);
      }
    }
    return value;
  }

  public static long castLong(Object object) {
    return castLong(object, 0);
  }

  public static int castInt(Object object, int defaultValue) {
    int value = defaultValue;
    if (object != null) {
      String stringValue = castString(object);
      if (StringUtils.isNotEmpty(stringValue)) {
        value = Integer.parseInt(stringValue);
      }
    }
    return value;
  }

  public static int castInt(Object object) {
    return castInt(object, 0);
  }

  public static boolean castBoolean(Object object, boolean defaultValue) {
    boolean value = defaultValue;
    if (object != null) {
      String stringValue = castString(object);
      if (StringUtils.isNotEmpty(stringValue)) {
        value = Boolean.valueOf(stringValue);
      }
    }
    return value;
  }

  public static boolean castBoolean(Object object) {
    return castBoolean(object, false);
  }

}
