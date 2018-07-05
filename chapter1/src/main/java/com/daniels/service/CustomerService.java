package com.daniels.service;

import com.daniels.helper.DatabaseHelper;
import com.daniels.model.Customer;
import com.daniels.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Daniels Gao
 * @date: 2018/7/1 16:52
 */
public class CustomerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

  public List<Customer> getCustomers() {
    Connection connection = null;
    List<Customer> customers = new ArrayList<Customer>();
    String sql = "SELECT * FROM customer";
    try {
      connection = DatabaseHelper.getConnection();
      PreparedStatement statement = connection.prepareStatement(sql);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setContact(resultSet.getString("contact"));
        customer.setEmail(resultSet.getString("email"));
        customer.setRemark(resultSet.getString("remark"));
        customer.setTelephone(resultSet.getString("telephone"));
        customers.add(customer);
      }
    } catch (SQLException e) {
      LOGGER.error("execute sql failed.", e);
    } finally {
      DatabaseHelper.closeConnection(connection);
    }
    return customers;
  }

  public List<Customer> getCustomers(String keyword) {
    //TODO
    return null;
  }

  public Customer getCustomer(long id) {
    //TODO
    return null;
  }

  public boolean createCustomer(Map<String, Object> fields) {
    //TODO
    return false;
  }

  public boolean updateCustomer(long id, Map<String, Object> fields) {
    //TODO
    return false;
  }

  public boolean deleteCustomer(long id) {
    return false;
  }
}
