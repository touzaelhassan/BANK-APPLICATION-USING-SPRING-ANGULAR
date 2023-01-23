package com.application;

import com.application.entities.*;
import com.application.enums.AccountStatus;
import com.application.enums.OperationType;
import com.application.repositories.AccountRepository;
import com.application.repositories.CustomerRepository;
import com.application.repositories.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner start(
            CustomerRepository customerRepository,
            AccountRepository accountRepository,
            OperationRepository operationRepository
    ) {
        return (args) -> {
            // ADD NEW CUSTOMERS
            Stream.of("hassan", "kamal", "adil").forEach((name) -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });

            // ADD NEW ACCOUNTS
            customerRepository.findAll().forEach((DBCustomer) -> {

                // ADD NEW CURRENT ACCOUNTS
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setAccountStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(DBCustomer);
                currentAccount.setOverDraft(9000);
                accountRepository.save(currentAccount);

                // ADD NEW SAVING ACCOUNTS
                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setAccountStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(DBCustomer);
                savingAccount.setInterestRate(5.5);
                accountRepository.save(savingAccount);

            });

            // ADD NEW OPERATIONS
            accountRepository.findAll().forEach((account) -> {

                for(int i = 0; i < 5; i++){
                    Operation operation = new Operation();
                    operation.setOperationDate(new Date());
                    operation.setAmount(Math.random() * 12000);
                    operation.setOperationType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    operation.setAccount(account);
                    operationRepository.save(operation);
                }

            });

        };
    }
}

