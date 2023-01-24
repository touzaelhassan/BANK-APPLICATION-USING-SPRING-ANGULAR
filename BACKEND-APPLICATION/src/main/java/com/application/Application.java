package com.application;

import java.util.List;
import java.util.stream.Stream;

import com.application.dtos.AccountDTO;
import com.application.dtos.CurrentAccountDTO;
import com.application.dtos.CustomerDTO;
import com.application.dtos.SavingAccountDTO;
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
               CustomerDTO customerDTO = new CustomerDTO();
               customerDTO.setName(name);
               customerDTO.setEmail(name + "@gmail.com");
               customerServiceBean.addCustomer(customerDTO);
           });

           customerServiceBean.getCustomers().forEach((customer) -> {
               try {
                   accountServiceBean.addCurrentAccount(customer.getId(),Math.random() * 90000, 9000);
                   accountServiceBean.addSavingAccount(customer.getId(), Math.random() * 120000, 5.5);
                   List<AccountDTO> accountDTOS = accountServiceBean.getAccounts();
                   for(AccountDTO accountDTO: accountDTOS){
                       for (int i = 0; i < 5; i++) {
                           Integer accountDTOId;
                           if(accountDTO instanceof SavingAccountDTO){
                               accountDTOId = ((SavingAccountDTO) accountDTO).getId();

                           }else{
                               accountDTOId = ((CurrentAccountDTO) accountDTO).getId();
                           }
                           accountServiceBean.credit(accountDTOId, 10000 + Math.random() * 120000, "Credit");
                           accountServiceBean.debit(accountDTOId, 1000 + Math.random() * 9000, "Debit");
                       }
                   }
               } catch (CustomerNotFoundException | AccountNotFoundException | BalanceNotSufficientException e) {
                   e.printStackTrace();
               }
           });
        };
    }
}

