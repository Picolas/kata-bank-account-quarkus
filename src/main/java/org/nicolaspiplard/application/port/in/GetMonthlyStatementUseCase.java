package org.nicolaspiplard.application.port.in;

import org.nicolaspiplard.application.port.in.response.MonthlyStatementResponse;

public interface GetMonthlyStatementUseCase {
    MonthlyStatementResponse getMonthlyStatement(Long accountId);
}
