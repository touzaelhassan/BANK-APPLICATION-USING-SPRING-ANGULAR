package com.application.services.specifications;

import com.application.dtos.AccountDTO;
import com.application.dtos.CurrentAccountDTO;
import com.application.dtos.SavingAccountDTO;
import com.application.entities.Account;
import com.application.exceptions.AccountNotFoundException;
import com.application.exceptions.BalanceNotSufficientException;
import com.application.exceptions.CustomerNotFoundException;
import java.util.List;

public interface AccountServiceSpecification {
    CurrentAccountDTO addCurrentAccount(Integer customerId, double initialBalance, double overDraft) throws CustomerNotFoundException;
    SavingAccountDTO addSavingAccount(Integer customerId, double initialBalance, double interestRate) throws CustomerNotFoundException;
    AccountDTO getAccountById(Integer id) throws AccountNotFoundException;
    List<AccountDTO> getAccounts();
    void debit (Integer accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;
    void credit (Integer accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;
    void transfer(Integer accountIdSource, Integer accountIdDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException;
}
