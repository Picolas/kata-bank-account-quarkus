package org.nicolaspiplard.domain.model;

import org.nicolaspiplard.application.port.in.response.MonthlyStatementResponse;

public interface AccountVisitor<R> {
    R visit(CurrentAccount current);
    R visit(SavingAccount saving);
}
