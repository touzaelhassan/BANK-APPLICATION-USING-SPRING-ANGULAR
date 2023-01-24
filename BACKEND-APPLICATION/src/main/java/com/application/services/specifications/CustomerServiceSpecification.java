package com.application.services.specifications;

import com.application.dtos.CustomerDTO;
import com.application.entities.Customer;
import java.util.List;

public interface CustomerServiceSpecification {
    Customer addCustomer(Customer customer);
    Customer getCustomerById(Integer id);
    List<CustomerDTO> getCustomers();
}
