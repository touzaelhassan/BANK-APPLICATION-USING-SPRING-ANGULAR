package com.application.controllers;

import com.application.dtos.CustomerDTO;
import com.application.exceptions.CustomerNotFoundException;
import com.application.services.specifications.CustomerServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerServiceSpecification customerServiceBean;

    @Autowired
    public CustomerController(CustomerServiceSpecification customerServiceBean) {
        this.customerServiceBean = customerServiceBean;
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getCustomers(){
        return customerServiceBean.getCustomers();
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable Integer id) throws CustomerNotFoundException {
        return customerServiceBean.getCustomerById(id);
    }

}
