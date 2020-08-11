package com.lambdaschool.orders.controllers;


import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerServices;
import com.lambdaschool.orders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;
    //localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllCustomerOrders(){
        List<Customer> myList = customerServices.findAllCustomerOrders();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    //localhost:2019/customers/customer/7
    @GetMapping(value = "/customer/{custcode}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long custcode){
        Customer myCustomer = customerServices.findCustomerById(custcode);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }
    //localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{cusname}", produces = "application/json")
    public ResponseEntity<?> findCustomerByNameLike(@PathVariable String cusname){
        List myList = customerServices.findAllCustomersByNameLike(cusname);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getOrderCount(){
        List<OrderCounts> myList = customerServices.getOrderCounts();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }


}
