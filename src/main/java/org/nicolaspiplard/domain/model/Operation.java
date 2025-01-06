package org.nicolaspiplard.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Operation(Long operationId, Long accountId, BigDecimal amount, LocalDateTime date,
                        OperationType operationType) {
}
