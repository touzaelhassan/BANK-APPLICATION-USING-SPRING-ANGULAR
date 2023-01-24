package com.application.services.implementations;

import com.application.entities.Operation;
import com.application.services.specifications.OperationServiceSpecification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("operationServiceBean")
public class OperationServiceImplementation implements OperationServiceSpecification {
    @Override
    public Operation addOperation(Operation operation) { return null; }
    @Override
    public Operation getOperationById(Integer id) { return null; }
    @Override
    public List<Operation> getOperations() { return null; }
}
