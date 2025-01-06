package org.nicolaspiplard.application.service;

import org.nicolaspiplard.application.port.in.dto.OperationDto;
import org.nicolaspiplard.application.port.in.response.CurrentAccountStatementResponse;
import org.nicolaspiplard.application.port.in.response.MonthlyStatementResponse;
import org.nicolaspiplard.application.port.in.response.SavingAccountStatementResponse;
import org.nicolaspiplard.domain.model.AccountVisitor;
import org.nicolaspiplard.domain.model.CurrentAccount;
import org.nicolaspiplard.domain.model.SavingAccount;

import java.util.List;

public class MonthlyStatementVisitor implements AccountVisitor {

    private final List<OperationDto> operations;

    public MonthlyStatementVisitor(List<OperationDto> operations) {
        this.operations = operations;
    }

    @Override
    public MonthlyStatementResponse visit(CurrentAccount current) {
        return new CurrentAccountStatementResponse(
                current.getAccountId(),
                current.getBalance(),
                operations,
                current.getOverdraft()
        );
    }

    @Override
    public MonthlyStatementResponse visit(SavingAccount saving) {
        return new SavingAccountStatementResponse(
                saving.getAccountId(),
                saving.getBalance(),
                operations,
                saving.getDepositCap()
        );
    }
}
