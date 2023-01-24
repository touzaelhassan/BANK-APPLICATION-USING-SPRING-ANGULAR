package com.application.services.specifications;

import com.application.dtos.CustomerDTO;
import com.application.entities.Customer;
import com.application.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceSpecification {
    Customer addCustomer(Customer customer);
    CustomerDTO getCustomerById(Integer id) throws CustomerNotFoundException;
    List<CustomerDTO> getCustomers();
}
