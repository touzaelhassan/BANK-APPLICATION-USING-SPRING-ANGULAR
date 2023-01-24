package com.application.controllers;

import com.application.dtos.AccountDTO;
import com.application.exceptions.AccountNotFoundException;
import com.application.services.specifications.AccountServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountServiceSpecification accountServiceBean;

    @Autowired
    public AccountController(AccountServiceSpecification accountServiceBean) { this.accountServiceBean = accountServiceBean; }

    @GetMapping("/account/{id}")
    public AccountDTO getAccountById(@PathVariable Integer id) throws AccountNotFoundException { return accountServiceBean.getAccountById(id); }

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts(){ return accountServiceBean.getAccounts(); }

}
