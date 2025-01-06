package org.nicolaspiplard.domain.model;

import org.nicolaspiplard.application.port.in.response.MonthlyStatementResponse;
import org.nicolaspiplard.domain.exception.AmountSuperiorToZeroException;

import java.math.BigDecimal;

public abstract class Account {

    protected Long accountId;
    protected BigDecimal balance;

    public Account(Long accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }


    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AmountSuperiorToZeroException(OperationType.DEPOSIT);
        }

        balance = balance.add(amount);
    }

    public abstract void withdraw(BigDecimal amount);

    public abstract MonthlyStatementResponse accept(AccountVisitor visitor);

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
