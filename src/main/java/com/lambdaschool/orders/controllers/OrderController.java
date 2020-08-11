package com.lambdaschool.orders.controllers;


import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.services.OrderServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServices orderServices;

    @GetMapping(value = "/order/{orderid}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long orderid){
        Order myOrder = orderServices.findOrderById(orderid);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }
}
