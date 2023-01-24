package com.application.mappers;

import com.application.dtos.CurrentAccountDTO;
import com.application.dtos.SavingAccountDTO;
import com.application.entities.CurrentAccount;
import com.application.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountMapperBean")
public class AccountMapperImplementation {

    private final CustomerMapperImplementation customerMapperBean;

    @Autowired
    public AccountMapperImplementation(CustomerMapperImplementation customerMapperBean) {
        this.customerMapperBean = customerMapperBean;
    }

    public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentAccountDTO);
        currentAccountDTO.setCustomerDTO(customerMapperBean.fromCustomer(currentAccount.getCustomer()));
        currentAccountDTO.setAccountType(currentAccount.getClass().getSimpleName());
        return currentAccountDTO;
    }

    public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO, currentAccount);
        currentAccount.setCustomer(customerMapperBean.fromCustomerDTO(currentAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public SavingAccountDTO fromSavingAccount(SavingAccount savingAccount){
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingAccountDTO);
        savingAccountDTO.setCustomerDTO(customerMapperBean.fromCustomer(savingAccount.getCustomer()));
        savingAccountDTO.setAccountType(savingAccount.getClass().getSimpleName());
        return savingAccountDTO;
    }

    public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, savingAccount);
        savingAccount.setCustomer(customerMapperBean.fromCustomerDTO(savingAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

}
