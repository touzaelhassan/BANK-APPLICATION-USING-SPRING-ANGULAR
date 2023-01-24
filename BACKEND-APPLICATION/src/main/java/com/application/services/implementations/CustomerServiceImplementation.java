package com.application.services.implementations;

import com.application.entities.Customer;
import com.application.repositories.CustomerRepository;
import com.application.services.specifications.CustomerServiceSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("customerServiceBean")
@Transactional
@Slf4j
public class CustomerServiceImplementation implements CustomerServiceSpecification {

    private final CustomerRepository customerRepositoryBean;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepositoryBean) {
        this.customerRepositoryBean = customerRepositoryBean;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepositoryBean.save(customer);
    }
    @Override
    public Customer getCustomerById(Integer id) { return customerRepositoryBean.findById(id).orElse(null); }
    @Override
    public List<Customer> getCustomers() { return null; }

}
