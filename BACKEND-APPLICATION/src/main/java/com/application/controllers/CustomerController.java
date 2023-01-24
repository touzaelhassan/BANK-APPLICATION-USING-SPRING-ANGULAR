package com.application.controllers;

import com.application.dtos.CustomerDTO;
import com.application.exceptions.CustomerNotFoundException;
import com.application.services.specifications.CustomerServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/customer/add")
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO){
        return customerServiceBean.addCustomer(customerDTO);
    }

    @PutMapping("/customer/update/{id}")
    public CustomerDTO updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(id);
        return customerServiceBean.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customer/delete/{id}")
    public void updateCustomer(@PathVariable Integer id){ customerServiceBean.deleteCustomer(id); }

}
