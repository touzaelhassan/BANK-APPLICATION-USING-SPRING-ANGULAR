package com.application.dtos;

import lombok.Data;
import com.application.enums.AccountStatus;
import java.util.Date;

@Data
public class SavingAccountDTO extends AccountDTO{
    private Integer id;
    private double balance;
    private Date createdAt;
    private AccountStatus accountStatus;
    private CustomerDTO customerDTO;
    private double interestRate;
}
