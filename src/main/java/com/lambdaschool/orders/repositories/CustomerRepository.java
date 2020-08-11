package com.lambdaschool.orders.repositories;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.views.OrderCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByCustnameContainingIgnoringCase(String cusname);

    @Query(value = "SELECT c.custname as name, count(ordnum) as countorders\n" +
            "FROM customers c LEFT JOIN orders o\n" +
            "ON c.custcode = o.custcode\n" +
            "GROUP BY c.custname\n" +
            "ORDER BY countorders desc", nativeQuery = true)
    List<OrderCounts> findOrderCounts();
}
