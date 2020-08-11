package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices{
    @Autowired
    CustomerRepository customerrepos;

    @Override
    public List<Customer> findAllCustomerOrders(){
        List<Customer> list = new ArrayList<>();
        customerrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        return customerrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
    }

    @Override
    public List<Customer> findAllCustomersByNameLike(String cusname) {
        return customerrepos.findByCustnameContainingIgnoringCase(cusname);
    }

    @Transactional
    @Override
    public Customer save(Customer customer){
        return customerrepos.save(customer);
    }
}
