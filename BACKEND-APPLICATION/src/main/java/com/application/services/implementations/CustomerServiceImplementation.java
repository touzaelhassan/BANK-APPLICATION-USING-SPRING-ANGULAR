package com.application.services.implementations;

import com.application.dtos.CustomerDTO;
import com.application.entities.Customer;
import com.application.mappers.CustomerMapperImplementation;
import com.application.repositories.CustomerRepository;
import com.application.services.specifications.CustomerServiceSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("customerServiceBean")
@Transactional
@Slf4j
public class CustomerServiceImplementation implements CustomerServiceSpecification {

    private final CustomerRepository customerRepositoryBean;
    private final CustomerMapperImplementation customerMapperBean;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepositoryBean, CustomerMapperImplementation customerMapperBean) {
        this.customerRepositoryBean = customerRepositoryBean;
        this.customerMapperBean = customerMapperBean;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepositoryBean.save(customer);
    }
    @Override
    public Customer getCustomerById(Integer id) { return customerRepositoryBean.findById(id).orElse(null); }
    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerRepositoryBean.findAll();
        return customers.stream().map(customerMapperBean::fromCustomer).collect(Collectors.toList());
    }

}
