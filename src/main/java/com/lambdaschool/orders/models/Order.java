package com.lambdaschool.orders.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;
    private double ordamount;
    private double advanceamount;
    private String orderdescription;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    private Customer customer;

    
}
