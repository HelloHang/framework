package com.daniels.service;

import com.daniels.helper.DatabaseHelper;
import com.daniels.model.Customer;
import java.sql.Connection;
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
    String sql = "SELECT * FROM customer";
    return DatabaseHelper.queryEntityList(Customer.class, sql);
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
