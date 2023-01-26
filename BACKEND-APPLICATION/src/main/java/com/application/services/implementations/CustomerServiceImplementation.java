package com.application.services.implementations;

import com.application.dtos.CustomerDTO;
import com.application.entities.Customer;
import com.application.exceptions.CustomerNotFoundException;
import com.application.mappers.CustomerMapperImplementation;
import com.application.repositories.CustomerRepository;
import com.application.services.specifications.CustomerServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("customerServiceBean")
public class CustomerServiceImplementation implements CustomerServiceSpecification {

    private final CustomerRepository customerRepositoryBean;
    private final CustomerMapperImplementation customerMapperBean;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepositoryBean, CustomerMapperImplementation customerMapperBean) {
        this.customerRepositoryBean = customerRepositoryBean;
        this.customerMapperBean = customerMapperBean;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapperBean.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepositoryBean.save(customer);
        return customerMapperBean.fromCustomer(savedCustomer);
    }
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapperBean.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepositoryBean.save(customer);
        return customerMapperBean.fromCustomer(savedCustomer);
    }
    @Override
    public CustomerDTO getCustomerById(Integer id) throws CustomerNotFoundException {
        Customer customer = customerRepositoryBean.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found !!."));
        return customerMapperBean.fromCustomer(customer);
    }
    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerRepositoryBean.findAll();
        return customers.stream().map(customerMapperBean::fromCustomer).collect(Collectors.toList());
    }
    @Override
    public void deleteCustomer(Integer id) { customerRepositoryBean.deleteById(id); }

}
