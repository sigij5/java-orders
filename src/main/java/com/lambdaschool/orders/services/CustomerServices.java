package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.views.OrderCounts;

import java.util.List;

public interface CustomerServices {
    List<Customer> findAllCustomerOrders();
    Customer findCustomerById(long id);
    List<Customer> findAllCustomersByNameLike(String name);
    List<OrderCounts> getOrderCounts();

    Customer save(Customer customer);
}
