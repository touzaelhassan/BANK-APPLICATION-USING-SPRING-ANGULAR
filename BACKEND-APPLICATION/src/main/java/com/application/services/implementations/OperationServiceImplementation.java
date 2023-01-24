package com.application.services.implementations;

import com.application.entities.Operation;
import com.application.repositories.OperationRepository;
import com.application.services.specifications.OperationServiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("operationServiceBean")
public class OperationServiceImplementation implements OperationServiceSpecification {

    private final OperationRepository operationRepositoryBean;

    @Autowired
    public OperationServiceImplementation(OperationRepository operationRepositoryBean) {
        this.operationRepositoryBean = operationRepositoryBean;
    }

    @Override
    public Operation addOperation(Operation operation) { return operationRepositoryBean.save(operation); }
    @Override
    public Operation getOperationById(Integer id) { return null; }
    @Override
    public List<Operation> getOperations() { return null; }

}
