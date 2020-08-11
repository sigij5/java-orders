package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;

import java.util.List;

public interface CustomerServices {
    List<Customer> findAllCustomerOrders();
    Customer findCustomerById(long id);
    List<Customer> findAllCustomersByNameLike(String name);

    Customer save(Customer customer);
}
