package com.application.services.specifications;

import java.util.List;
import com.application.entities.Account;

public interface AccountServiceSpecification {
    Account addAccount(Account account);
    Account getAccountById(Integer id);
    List<Account> getAccounts();
    void debit (Integer id, double amount, String description);
    void credit (Integer id, double amount, String description);
    void transfer(Integer accountIdSource, Integer accountIdDestination, double amount);
}
