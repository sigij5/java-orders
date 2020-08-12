package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.models.Payment;
import com.lambdaschool.orders.repositories.CustomerRepository;
import com.lambdaschool.orders.repositories.PaymentRepository;
import com.lambdaschool.orders.views.OrderCounts;
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

    @Autowired
    PaymentRepository paymentrepos;

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

    @Override
    public List<OrderCounts> getOrderCounts() {
        List<OrderCounts> list=customerrepos.findOrderCounts();
        return list;
    }

    @Transactional
    @Override
    public Customer save(Customer customer){

        Customer newCustomer = new Customer();

        if(customer.getCustcode() != 0){
            customerrepos.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Restaurant " + customer.getCustcode() + " Not Found"));

            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()){
            Order newOrder = new Order();
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setCustomer(newCustomer);
            newCustomer.setAgent(customer.getAgent());

            newOrder.getPayments().clear();
            for (Payment p : o.getPayments()){
                Payment newPay = paymentrepos.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));

                newOrder.getPayments().add(newPay);
            }

            newCustomer.getOrders().add(newOrder);
        }

        return customerrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if(customerrepos.findById(id).isPresent()){
            customerrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer " + id + " Not Found");
        }
    }

    @Override
    public Customer update(Customer customer, long id) {
            Customer currentCustomer = customerrepos.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));

            if(customer.getCustname() != null) {
                currentCustomer.setCustname(customer.getCustname());
            }
            if(customer.getCustcity() != null) {
                currentCustomer.setCustcity(customer.getCustcity());
            }
            if(customer.getCustcountry() != null) {
                currentCustomer.setCustcountry(customer.getCustcountry());
            }
            if(customer.getGrade() != null) {
                currentCustomer.setGrade(customer.getGrade());
            }
            if(customer.getPhone() != null) {
                currentCustomer.setPhone(customer.getPhone());
            }
            if(customer.getWorkingarea() != null) {
                currentCustomer.setWorkingarea(customer.getWorkingarea());
            }
            if(customer.getAgent() != null) {
                currentCustomer.setAgent(customer.getAgent());
            }
            if(customer.hasvalueforopeningamt){
                currentCustomer.setOpeningamt(customer.getOpeningamt());
            }
            if(customer.hasvalueforreceiveamt){
                currentCustomer.setReceiveamt(customer.getReceiveamt());
            }
            if(customer.hasvalueforpaymentamt){
                currentCustomer.setPaymentamt(customer.getPaymentamt());
            }
            if(customer.hasvalueforoutstandingamt){
                currentCustomer.setOutstandingamt(customer.getOutstandingamt());
            }


            if(customer.getOrders().size() > 0) {
                currentCustomer.getOrders()
                        .clear();
                for (Order o : customer.getOrders()) {
                    Order newOrder = new Order();
                    newOrder.setAdvanceamount(o.getAdvanceamount());
                    newOrder.setOrdamount(o.getOrdamount());
                    newOrder.setOrderdescription(o.getOrderdescription());
                    newOrder.setCustomer(currentCustomer);
                if(o.getPayments().size() > 0)
                newOrder.getPayments().clear();
                for (Payment p : o.getPayments()){
                    Payment newPay = paymentrepos.findById(p.getPaymentid())
                            .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));

                    newOrder.getPayments().add(newPay);
                }
                    currentCustomer.getOrders().add(newOrder);
                }
            }

            return customerrepos.save(currentCustomer);
    }
}
