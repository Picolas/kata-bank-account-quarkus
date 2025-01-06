package org.nicolaspiplard.domain.model;

import org.nicolaspiplard.application.port.in.response.MonthlyStatementResponse;
import org.nicolaspiplard.domain.exception.AmountSuperiorToZeroException;
import org.nicolaspiplard.domain.exception.InsufficientFundsException;
import org.nicolaspiplard.domain.exception.OverdraftLimitExceededException;

import java.math.BigDecimal;

public class CurrentAccount extends Account {

    private final BigDecimal overdraft;

    public CurrentAccount(Long accountId, BigDecimal initialBalance, BigDecimal overdraft) {
        super(accountId, initialBalance);
        this.overdraft = overdraft;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AmountSuperiorToZeroException(OperationType.WITHDRAW);
        }

        if (overdraft == null) {
            if (this.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException(this.getBalance(), amount);
            }
        } else {
            if (this.getBalance().add(overdraft).compareTo(amount) < 0) {
                throw new OverdraftLimitExceededException(overdraft);
            }
        }

        balance = balance.subtract(amount);
    }

    @Override
    public <R> R accept(AccountVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getAccountType() {
        return "Compte courant";
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }
}
