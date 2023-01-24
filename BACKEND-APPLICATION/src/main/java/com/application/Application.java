package com.application;

import java.util.List;
import java.util.stream.Stream;
import com.application.entities.*;
import com.application.exceptions.AccountNotFoundException;
import com.application.exceptions.BalanceNotSufficientException;
import com.application.exceptions.CustomerNotFoundException;
import com.application.services.specifications.AccountServiceSpecification;
import com.application.services.specifications.CustomerServiceSpecification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner start(
          CustomerServiceSpecification customerServiceBean,
          AccountServiceSpecification accountServiceBean
    ) {
        return (args) -> {

           Stream.of("hassan", "imane", "mohammed").forEach((name) -> {
               Customer customer = new Customer();
               customer.setName(name);
               customer.setEmail(name + "@gmail.com");
               customerServiceBean.addCustomer(customer);
           });

           customerServiceBean.getCustomers().forEach((customer) -> {
               try {
                   accountServiceBean.addCurrentAccount(customer.getId(),Math.random() * 90000, 9000);
                   accountServiceBean.addSavingAccount(customer.getId(), Math.random() * 120000, 5.5);
                   List<Account> accounts = accountServiceBean.getAccounts();
                   for(Account account: accounts){
                       for (int i = 0; i < 5; i++) {
                           accountServiceBean.credit(account.getId(), 10000 + Math.random() * 120000, "Credit");
                           accountServiceBean.credit(account.getId(), 10000 + Math.random() * 120000, "Credit");
                       }
                   }
               } catch (CustomerNotFoundException | AccountNotFoundException | BalanceNotSufficientException e) {
                   e.printStackTrace();
               }
           });
        };
    }
}

