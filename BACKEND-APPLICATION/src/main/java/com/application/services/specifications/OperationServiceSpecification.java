package com.application.services.specifications;

import com.application.entities.Operation;
import java.util.List;

public interface OperationServiceSpecification {
    Operation addOperation(Operation operation);
    Operation getOperationById(Integer id);
    List<Operation> getOperations();
}
