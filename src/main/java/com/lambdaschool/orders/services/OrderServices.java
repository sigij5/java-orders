package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Order;

public interface OrderServices {
    Order save(Order order);

    Order findOrderById(long id);

    void delete(long id);
}
