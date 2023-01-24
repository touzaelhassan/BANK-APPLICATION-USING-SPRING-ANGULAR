package com.application.services.specifications;

import com.application.dtos.CustomerDTO;
import com.application.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceSpecification {
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Integer id) throws CustomerNotFoundException;
    List<CustomerDTO> getCustomers();
    void deleteCustomer(Integer id);
}
