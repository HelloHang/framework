package com.daniels.test;

import com.daniels.model.Customer;
import com.daniels.service.CustomerService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: Daniels Gao
 * @date: 2018/7/1 17:02
 */
public class CustomerServiceTest {

  private CustomerService customerService;

  public CustomerServiceTest() {
    customerService = new CustomerService();
  }

  @Before
  public void init() {
    //TODO
  }

  @Test
  public void getCustomersTest() {
    List<Customer> customers = customerService.getCustomers();
    Assert.assertEquals(2, customers.size());
  }

  @Test
  public void getCustomerTest() {
    Customer customer = customerService.getCustomer(1);
    Assert.assertNotNull(customer);
  }

  @Test
  public void createCustomerTest(){
    Map<String, Object> fields = new HashMap<String, Object>();
    fields.put("name", "customer100");
    fields.put("contact", "jack");
    fields.put("telephone", "123123131");
    fields.put("email", "564321@test.com");
    Assert.assertTrue(customerService.createCustomer(fields));
  }

  @Test
  public void updateCustomerTest(){
    Map<String, Object> fields = new HashMap<String, Object>();
    fields.put("name", "dandan");
    fields.put("telephone", "11111111111");
    Assert.assertTrue(customerService.updateCustomer(1, fields));
  }

  @Test
  public void deleteCustomerTest(){
    Assert.assertTrue(customerService.deleteCustomer(1));
  }

}
