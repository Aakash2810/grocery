package com.crio.grocery.service;

import com.crio.grocery.entity.Customer;
import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer updateCustomer(Long id, Customer customerDetails);

    void deleteCustomer(Long id);
}