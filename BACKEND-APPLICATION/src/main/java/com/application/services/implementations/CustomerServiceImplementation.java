package com.application.services.implementations;

import com.application.entities.Customer;
import com.application.services.specifications.CustomerServiceSpecification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("customerServiceBean")
public class CustomerServiceImplementation implements CustomerServiceSpecification {
    @Override
    public Customer addCustomer(Customer customer) { return null; }
    @Override
    public Customer getCustomerById(Integer id) { return null; }
    @Override
    public List<Customer> getCustomers() { return null; }
}
