package org.nicolaspiplard.domain.model;

import org.nicolaspiplard.application.port.in.response.MonthlyStatementResponse;

public interface AccountVisitor {
    MonthlyStatementResponse visit(CurrentAccount current);
    MonthlyStatementResponse visit(SavingAccount saving);
}
