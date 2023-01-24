package com.application.dtos;

import com.application.enums.OperationType;
import lombok.Data;
import java.util.Date;

@Data
public class OperationDTO {
    private Integer id;
    private Date operationDate;
    private double amount;
    private String description;
    private OperationType operationType;
}
