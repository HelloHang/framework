package com.daniels.helper;

import com.daniels.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Daniels Gao
 * @date: 2018/7/4 22:48
 */
public class DatabaseHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

  private static final String URL;
  private static final String USERNAME;
  private static final String PASSWORD;
  private static final QueryRunner QUERY_RUNNER = new QueryRunner();

  static {
    URL = PropertiesUtil.getString("jdbc.url");
    USERNAME = PropertiesUtil.getString("jdbc.name");
    PASSWORD = PropertiesUtil.getString("jdbc.password");
  }

  public static Connection getConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
      LOGGER.error("Get connection failed.", e);
    }
    return connection;
  }

  public static void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        LOGGER.error("Close connection failed.", e);
      }
    }
  }

  public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
    List entities;
    Connection connection = getConnection();
    try {
      entities = QUERY_RUNNER
          .execute(connection, sql, new BeanHandler<T>(entityClass), params);
    } catch (SQLException e) {
      LOGGER.error("Query entity list failed.", e);
      throw new RuntimeException(e);
    }
    finally {
      closeConnection(connection);
    }
    return entities;
  }
}
