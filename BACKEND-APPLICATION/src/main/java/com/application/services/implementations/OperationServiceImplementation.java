package com.application.services.implementations;

import com.application.dtos.AccountDTO;
import com.application.dtos.AccountHistoryDTO;
import com.application.dtos.CurrentAccountDTO;
import com.application.dtos.OperationDTO;
import com.application.entities.Account;
import com.application.entities.Operation;
import com.application.exceptions.AccountNotFoundException;
import com.application.mappers.AccountMapperImplementation;
import com.application.mappers.OperationMapperImplementation;
import com.application.repositories.OperationRepository;
import com.application.services.specifications.AccountServiceSpecification;
import com.application.services.specifications.OperationServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("operationServiceBean")
public class OperationServiceImplementation implements OperationServiceSpecification {

    private final OperationRepository operationRepositoryBean;
    private final OperationMapperImplementation operationMapperBean;
    private final AccountServiceSpecification accountServiceBean;
    private final AccountMapperImplementation accountMapperBean;

    @Autowired
    public OperationServiceImplementation(OperationRepository operationRepositoryBean, OperationMapperImplementation operationMapperBean, AccountServiceSpecification accountServiceBean, AccountMapperImplementation accountMapperBean) {
        this.operationRepositoryBean = operationRepositoryBean;
        this.operationMapperBean = operationMapperBean;
        this.accountServiceBean = accountServiceBean;
        this.accountMapperBean = accountMapperBean;
    }

    @Override
    public Operation addOperation(Operation operation) { return operationRepositoryBean.save(operation); }
    @Override
    public Operation getOperationById(Integer id) { return null; }
    @Override
    public List<OperationDTO> getOperationsByAccountId(Integer id) {
        List<Operation> operations = operationRepositoryBean.findOperationByAccountId(id);
        return operations.stream().map((operation) -> operationMapperBean.fromOperation(operation)).collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(Integer accountId, Integer page, Integer size) throws AccountNotFoundException {
        AccountDTO accountDTO = accountServiceBean.getAccountById(accountId);
        Account account = accountMapperBean.fromCurrentAccountDTO((CurrentAccountDTO) accountDTO);
        Page<Operation> operations =  operationRepositoryBean.findOperationByAccountId(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<OperationDTO> operationDTOS = operations.getContent().stream().map((operation) -> operationMapperBean.fromOperation(operation)).collect(Collectors.toList());
        accountHistoryDTO.setOperationDTOS(operationDTOS);
        accountHistoryDTO.setAccountId(account.getId());
        accountHistoryDTO.setBalance(account.getBalance());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(operations.getTotalPages());
        return accountHistoryDTO;
    }


}
















