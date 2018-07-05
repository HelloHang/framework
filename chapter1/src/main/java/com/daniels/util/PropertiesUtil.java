package com.daniels.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Daniels Gao
 * @date: 2018/7/3 20:56
 */
public class PropertiesUtil {

  private final static Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

  private static Properties properties = loadProperties("config.properties");

  public static Properties loadProperties(String fileName) {
    Properties properties = null;
    InputStream in = null;
    try {
      in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
      if (in == null) {
        throw new FileNotFoundException(fileName + "file is not found.");
      }
      properties = new Properties();
      properties.load(in);
    } catch (IOException e) {
      LOG.error("load properties file failed", e);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          LOG.error("close input stream failed.", e);
        }
      }
    }
    return properties;
  }

  public static String getString(String key) {
    return getString(key, StringUtils.EMPTY);
  }

  public static String getString(String key, String defaultValue) {
    return properties.getProperty(key, defaultValue);
  }

  public static int getInt(String key) {
    return getInt(key, 0);
  }

  public static int getInt(String key, int defaultValue) {
    int value = defaultValue;
    if (properties.containsKey(key)) {
      value = CastUtil.castInt(properties.getProperty(key));
    }
    return value;
  }

  public static boolean getBoolean(String key) {
    return getBoolean(key, false);
  }

  public static boolean getBoolean(String key, boolean defaultValue) {
    boolean value = defaultValue;
    if (properties.containsKey(key)) {
      value = CastUtil.castBoolean(properties.getProperty(key));
    }
    return value;
  }
}
