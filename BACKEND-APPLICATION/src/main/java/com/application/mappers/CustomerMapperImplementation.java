package com.application.mappers;

import com.application.dtos.CustomerDTO;
import com.application.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service("customerMapperBean")
public class CustomerMapperImplementation {

    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        //customerDTO.setId(customer.getId());
        //customerDTO.setName(customer.getName());
        //customerDTO.setEmail(customerDTO.getEmail());
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

}
