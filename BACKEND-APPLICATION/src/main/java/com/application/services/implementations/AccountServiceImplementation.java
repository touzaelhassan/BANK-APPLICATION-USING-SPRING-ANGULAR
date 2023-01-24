package com.application.services.implementations;

import com.application.entities.*;
import com.application.enums.OperationType;
import com.application.exceptions.AccountNotFoundException;
import com.application.exceptions.BalanceNotSufficientException;
import com.application.exceptions.CustomerNotFoundException;
import com.application.repositories.AccountRepository;
import com.application.services.specifications.AccountServiceSpecification;
import com.application.services.specifications.CustomerServiceSpecification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("accountServiceBean")
public class AccountServiceImplementation implements AccountServiceSpecification {

    private final CustomerServiceSpecification customerServiceBean;
    private final OperationServiceImplementation operationServiceBean;
    private final AccountRepository accountRepositoryBean;

    public AccountServiceImplementation(CustomerServiceSpecification customerServiceBean, OperationServiceImplementation operationServiceBean, AccountRepository accountRepositoryBean) {
        this.customerServiceBean = customerServiceBean;
        this.operationServiceBean = operationServiceBean;
        this.accountRepositoryBean = accountRepositoryBean;
    }

    @Override
    public CurrentAccount addCurrentAccount(Integer customerId, double initialBalance, double overDraft) throws CustomerNotFoundException {
        Customer customer = customerServiceBean.getCustomerById(customerId);
        if(customer == null){ throw new CustomerNotFoundException("Customer not found !!."); }
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        return accountRepositoryBean.save(currentAccount);
    }

    @Override
    public SavingAccount addSavingAccount(Integer customerId, double initialBalance, double interestRate) throws CustomerNotFoundException {
        Customer customer = customerServiceBean.getCustomerById(customerId);
        if(customer == null){ throw new CustomerNotFoundException("Customer not found !!."); }
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        return accountRepositoryBean.save(savingAccount);
     }
    @Override
    public Account getAccountById(Integer id) throws AccountNotFoundException {
        Account account = accountRepositoryBean.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Not Found !!."));
        return account;
    }
    @Override
    public List<Account> getAccounts() {
        return accountRepositoryBean.findAll();
    }

    @Override
    public void debit(Integer accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException {

        Account account = this.getAccountById(accountId);

        if(account.getBalance() < amount){
            throw new BalanceNotSufficientException("Balance Not Sufficient");
        }

        Operation operation = new Operation();
        operation.setOperationType(OperationType.DEBIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setAccount(account);
        operationServiceBean.addOperation(operation);
        account.setBalance(account.getBalance() - amount);
        accountRepositoryBean.save(account);

    }

    @Override
    public void credit(Integer accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException {

        Account account = this.getAccountById(accountId);

        Operation operation = new Operation();
        operation.setOperationType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setAccount(account);
        operationServiceBean.addOperation(operation);
        account.setBalance(account.getBalance() + amount);
        accountRepositoryBean.save(account);
    }

    @Override
    public void transfer(Integer accountIdSource, Integer accountIdDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource, amount, "Transfer To " + accountIdDestination);
        credit(accountIdDestination, amount, "Transfer From " + accountIdSource);

    }
}
