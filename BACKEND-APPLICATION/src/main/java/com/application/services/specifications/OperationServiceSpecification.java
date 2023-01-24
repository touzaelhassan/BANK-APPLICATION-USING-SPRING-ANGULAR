package com.application.services.specifications;

import com.application.dtos.AccountHistoryDTO;
import com.application.dtos.OperationDTO;
import com.application.entities.Operation;
import com.application.exceptions.AccountNotFoundException;

import java.util.List;

public interface OperationServiceSpecification {
    Operation addOperation(Operation operation);
    Operation getOperationById(Integer id);
    List<OperationDTO> getOperationsByAccountId(Integer id);
    AccountHistoryDTO getAccountHistory(Integer accountId, Integer page, Integer size) throws AccountNotFoundException;
}
