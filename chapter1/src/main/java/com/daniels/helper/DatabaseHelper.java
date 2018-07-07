package com.daniels.helper;

import com.daniels.util.CollectionUtil;
import com.daniels.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
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
    List<T> entities;
    Connection connection = getConnection();
    try {
      entities = QUERY_RUNNER
          .query(connection, sql, new BeanListHandler<T>(entityClass), params);
    } catch (SQLException e) {
      LOGGER.error("Query entity list failed.", e);
      throw new RuntimeException(e);
    } finally {
      closeConnection(connection);
    }
    return entities;
  }

  public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
    T entity;
    Connection connection = getConnection();
    try {
      entity = QUERY_RUNNER
          .query(connection, sql, new BeanHandler<T>(entityClass), params);
    } catch (SQLException e) {
      LOGGER.error("Query entity list failed.", e);
      throw new RuntimeException(e);
    } finally {
      closeConnection(connection);
    }
    return entity;
  }

  public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
    List<Map<String, Object>> result;
    Connection connection = getConnection();
    try {
      result = QUERY_RUNNER
          .query(connection, sql, new MapListHandler(), params);
    } catch (SQLException e) {
      LOGGER.error("Query entity list failed.", e);
      throw new RuntimeException(e);
    } finally {
      closeConnection(connection);
    }
    return result;
  }

  public static int executeUpdate(String sql, Object... params) {
    int row = 0;
    Connection connection = getConnection();
    try {
      row = QUERY_RUNNER.update(connection, sql, params);
    } catch (SQLException e) {
      LOGGER.error("Execute update failed.", e);
      throw new RuntimeException(e);
    } finally {
      closeConnection(connection);
    }
    return row;
  }

  public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fields) {
    if (CollectionUtil.isEmpty(fields)) {
      LOGGER.error("Can't insert entity: fields is empty.");
      return false;
    }

    String sql = "INSERT INTO " + getTableName(entityClass);
    StringBuilder columns = new StringBuilder("(");
    StringBuilder values = new StringBuilder("(");
    for (String fieldName : fields.keySet()) {
      columns.append(fieldName).append(", ");
      values.append("?, ");
    }
    columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
    values.replace(columns.lastIndexOf(", "), values.length(), ")");

    sql += columns + " VALUES " + values;

    Object[] params = fields.values().toArray();
    return executeUpdate(sql, params) == 1;
  }

  public static <T> boolean updateEntity(Class<T> entityClass, long id,
      Map<String, Object> fields) {
    if (CollectionUtil.isEmpty(fields)) {
      LOGGER.error("Update entity failed: fields is empty.");
      return false;
    }

    StringBuilder sql = new StringBuilder("UPDATE ").append(getTableName(entityClass))
        .append(" SET ");
    StringBuilder conlumns = new StringBuilder();
    for (String fieldName : fields.keySet()) {
      conlumns.append(fieldName).append("=?, ");
    }
    sql.append(conlumns.substring(0, conlumns.lastIndexOf(", "))).append(" WHERE id=?");
    List<Object> params = new ArrayList<Object>(fields.values());
    params.add(id);
    return executeUpdate(sql.toString(), params) == 1;
  }

  public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
    String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=?";
    return executeUpdate(sql, id) == 1;
  }

  private static String getTableName(Class<?> entityClass) {
    return entityClass.getSimpleName();
  }
}
