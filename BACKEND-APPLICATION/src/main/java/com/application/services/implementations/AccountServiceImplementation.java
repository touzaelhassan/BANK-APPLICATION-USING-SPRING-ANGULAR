package com.application.services.implementations;

import com.application.dtos.AccountDTO;
import com.application.dtos.CurrentAccountDTO;
import com.application.dtos.CustomerDTO;
import com.application.dtos.SavingAccountDTO;
import com.application.entities.*;
import com.application.enums.AccountStatus;
import com.application.enums.OperationType;
import com.application.exceptions.AccountNotFoundException;
import com.application.exceptions.BalanceNotSufficientException;
import com.application.exceptions.CustomerNotFoundException;
import com.application.mappers.AccountMapperImplementation;
import com.application.mappers.CustomerMapperImplementation;
import com.application.repositories.AccountRepository;
import com.application.services.specifications.AccountServiceSpecification;
import com.application.services.specifications.CustomerServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("accountServiceBean")
public class AccountServiceImplementation implements AccountServiceSpecification {

    private final CustomerServiceSpecification customerServiceBean;
    private final OperationServiceImplementation operationServiceBean;
    private final AccountRepository accountRepositoryBean;
    private final CustomerMapperImplementation customerMapperBean;
    private final AccountMapperImplementation accountMapperBean;

    @Autowired
    public AccountServiceImplementation(CustomerServiceSpecification customerServiceBean, OperationServiceImplementation operationServiceBean, AccountRepository accountRepositoryBean, CustomerMapperImplementation customerMapperBean, AccountMapperImplementation accountMapperBean) {
        this.customerServiceBean = customerServiceBean;
        this.operationServiceBean = operationServiceBean;
        this.accountRepositoryBean = accountRepositoryBean;
        this.customerMapperBean = customerMapperBean;
        this.accountMapperBean = accountMapperBean;
    }

    @Override
    public CurrentAccountDTO addCurrentAccount(Integer customerId, double initialBalance, double overDraft) throws CustomerNotFoundException {
        CustomerDTO customerDTO = customerServiceBean.getCustomerById(customerId);
        Customer customer = customerMapperBean.fromCustomerDTO(customerDTO);
        if(customer == null){ throw new CustomerNotFoundException("Customer not found !!."); }
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setAccountStatus(AccountStatus.CREATED);
        currentAccount.setCustomer(customer);
        CurrentAccount savedCurrentAccount =  accountRepositoryBean.save(currentAccount);
        return accountMapperBean.fromCurrentAccount(savedCurrentAccount);
    }

    @Override
    public SavingAccountDTO addSavingAccount(Integer customerId, double initialBalance, double interestRate) throws CustomerNotFoundException {
        CustomerDTO customerDTO = customerServiceBean.getCustomerById(customerId);
        Customer customer = customerMapperBean.fromCustomerDTO(customerDTO);
        if(customer == null){ throw new CustomerNotFoundException("Customer not found !!."); }
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setAccountStatus(AccountStatus.CREATED);
        savingAccount.setCustomer(customer);
        SavingAccount savedSavingAccount = accountRepositoryBean.save(savingAccount);
        return accountMapperBean.fromSavingAccount(savedSavingAccount);
     }

    @Override
    public AccountDTO getAccountById(Integer id) throws AccountNotFoundException {
       Account account = accountRepositoryBean.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Not Found !!."));
       if(account  instanceof SavingAccount){
           SavingAccount savingAccount = (SavingAccount) account;
           return accountMapperBean.fromSavingAccount(savingAccount);
       }else{
           CurrentAccount currentAccount = (CurrentAccount) account;
           return accountMapperBean.fromCurrentAccount(currentAccount);
       }
    }


    @Override
    public List<AccountDTO> getAccounts() {
        List<Account> accounts = accountRepositoryBean.findAll();
       return accounts.stream().map((account) -> {
            if (account instanceof SavingAccount){
                SavingAccount savingAccount = (SavingAccount) account;
                return accountMapperBean.fromSavingAccount(savingAccount);
            }else{
                CurrentAccount currentAccount = (CurrentAccount) account;
                return accountMapperBean.fromCurrentAccount(currentAccount);
            }
        }).collect(Collectors.toList());

    }

    @Override
    public void debit(Integer accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException {
        Account account = accountRepositoryBean.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account Not Found !!."));
        if(account.getBalance() < amount){ throw new BalanceNotSufficientException("Balance Not Sufficient"); }
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
    public void credit(Integer accountId, double amount, String description) throws AccountNotFoundException {
        Account account = accountRepositoryBean.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account Not Found !!."));
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
