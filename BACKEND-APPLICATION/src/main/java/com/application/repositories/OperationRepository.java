package com.application.repositories;

import com.application.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("operationRepositoryBean")
public interface OperationRepository extends JpaRepository<Operation, Integer> {
    public List<Operation> findOperationByAccountId(Integer id);
    Page<Operation> findOperationByAccountId(Integer accountId, Pageable pageable);
}
