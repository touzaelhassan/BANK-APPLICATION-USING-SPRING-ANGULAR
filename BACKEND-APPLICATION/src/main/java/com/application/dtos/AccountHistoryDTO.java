package com.application.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDTO {
    private Integer accountId;
    private double balance;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
    private List<OperationDTO> operationDTOS;
}
