package com.application.controllers;

import com.application.dtos.AccountHistoryDTO;
import com.application.dtos.OperationDTO;
import com.application.exceptions.AccountNotFoundException;
import com.application.services.specifications.OperationServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OperationController {

    private final OperationServiceSpecification operationServiceBean;
    @Autowired
    public OperationController(OperationServiceSpecification operationServiceBean) {
        this.operationServiceBean = operationServiceBean;
    }

    @GetMapping("/account/{id}/operations")
    public List<OperationDTO> getOperationsByAccountId(@PathVariable Integer id){ return operationServiceBean.getOperationsByAccountId(id); }
    @GetMapping("/account/{accountId}/page-operations")
    public  AccountHistoryDTO getAccountHistoryDTO(
            @PathVariable Integer accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
           ) throws AccountNotFoundException {
        return operationServiceBean.getAccountHistory(accountId, page, size);
    }

}
























