package com.application.services.implementations;

import com.application.entities.Account;
import com.application.services.specifications.AccountServiceSpecification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("accountServiceBean")
public class AccountServiceImplementation implements AccountServiceSpecification {
    @Override
    public Account addAccount(Account account) { return null; }
    @Override
    public Account getAccountById(Integer id) { return null; }
    @Override
    public List<Account> getAccounts() { return null; }
    @Override
    public void debit(Integer id, double amount, String description) { }
    @Override
    public void credit(Integer id, double amount, String description) { }
    @Override
    public void transfer(Integer accountIdSource, Integer accountIdDestination, double amount) {}
}
