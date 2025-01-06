package org.nicolaspiplard.application.port.out.repository;

import org.nicolaspiplard.domain.model.Operation;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationRepository {
    void recordOperation(Operation operation);
    List<Operation> findOperationsByAccountAndPeriod(Long accountId, LocalDateTime from, LocalDateTime to);
}
