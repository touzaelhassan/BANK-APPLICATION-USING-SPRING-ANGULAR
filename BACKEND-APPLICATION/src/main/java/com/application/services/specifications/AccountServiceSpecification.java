package com.application.services.specifications;

import java.util.List;
import com.application.entities.Account;
import com.application.entities.SavingAccount;
import com.application.entities.CurrentAccount;
import com.application.exceptions.AccountNotFoundException;
import com.application.exceptions.BalanceNotSufficientException;
import com.application.exceptions.CustomerNotFoundException;

public interface AccountServiceSpecification {
    CurrentAccount addCurrentAccount(Integer customerId, double initialBalance, double overDraft) throws CustomerNotFoundException;
    SavingAccount addSavingAccount(Integer customerId,double initialBalance, double interestRate) throws CustomerNotFoundException;
    Account getAccountById(Integer id) throws AccountNotFoundException;
    List<Account> getAccounts();
    void debit (Integer accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;
    void credit (Integer accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;
    void transfer(Integer accountIdSource, Integer accountIdDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException;
}
