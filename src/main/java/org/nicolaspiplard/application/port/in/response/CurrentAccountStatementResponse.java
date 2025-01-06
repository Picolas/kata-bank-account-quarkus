package org.nicolaspiplard.application.port.in.response;

import org.nicolaspiplard.application.port.in.dto.OperationDto;

import java.math.BigDecimal;
import java.util.List;

public record CurrentAccountStatementResponse(Long accountId, String accountType, BigDecimal balance,
                                              List<OperationDto> operations,
                                              BigDecimal overdraft) implements MonthlyStatementResponse {
}
