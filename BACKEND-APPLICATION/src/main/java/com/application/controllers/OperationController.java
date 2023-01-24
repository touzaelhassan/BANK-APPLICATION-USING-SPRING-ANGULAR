package com.application.controllers;


import com.application.dtos.OperationDTO;
import com.application.services.specifications.OperationServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OperationController {

    private final OperationServiceSpecification operationServiceBean;
    @Autowired
    public OperationController(OperationServiceSpecification operationServiceBean) {
        this.operationServiceBean = operationServiceBean;
    }

    @GetMapping("/account/operations/{id}")
    public List<OperationDTO> getOperationsByAccountId(@PathVariable Integer id){ return operationServiceBean.getOperationsByAccountId(id); }
}
