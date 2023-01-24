package com.application.mappers;

import com.application.dtos.OperationDTO;
import com.application.entities.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service("operationMapperBean")
public class OperationMapperImplementation {

    public OperationDTO fromOperation(Operation operation){
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(operation, operationDTO);
        return operationDTO;
    }

}
