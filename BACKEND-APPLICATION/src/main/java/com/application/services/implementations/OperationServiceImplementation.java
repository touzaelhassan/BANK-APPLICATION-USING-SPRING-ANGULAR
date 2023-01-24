package com.application.services.implementations;

import com.application.dtos.OperationDTO;
import com.application.entities.Operation;
import com.application.mappers.OperationMapperImplementation;
import com.application.repositories.OperationRepository;
import com.application.services.specifications.OperationServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("operationServiceBean")
public class OperationServiceImplementation implements OperationServiceSpecification {

    private final OperationRepository operationRepositoryBean;
    private final OperationMapperImplementation operationMapperBean;

    @Autowired
    public OperationServiceImplementation(OperationRepository operationRepositoryBean, OperationMapperImplementation operationMapperBean) {
        this.operationRepositoryBean = operationRepositoryBean;
        this.operationMapperBean = operationMapperBean;
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

}
