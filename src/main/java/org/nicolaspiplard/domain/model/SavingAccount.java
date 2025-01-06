package org.nicolaspiplard.domain.model;

import org.nicolaspiplard.application.port.in.response.MonthlyStatementResponse;
import org.nicolaspiplard.domain.exception.DepositCapExceededException;
import org.nicolaspiplard.domain.exception.AmountSuperiorToZeroException;
import org.nicolaspiplard.domain.exception.InsufficientFundsSavingAccountException;

import java.math.BigDecimal;

public class SavingAccount extends Account {
    private final BigDecimal depositCap;

    public SavingAccount(Long accountId, BigDecimal depositCap) {
        super(accountId, BigDecimal.ZERO);
        this.depositCap = depositCap;
    }

    public SavingAccount(Long accountId, BigDecimal balance, BigDecimal depositCap) {
        super(accountId, balance);
        this.depositCap = depositCap;
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AmountSuperiorToZeroException(OperationType.DEPOSIT);
        }

        if (this.getBalance().add(amount).compareTo(depositCap) > 0) {
            throw new DepositCapExceededException(balance, amount, depositCap);
        }

        super.deposit(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AmountSuperiorToZeroException(OperationType.WITHDRAW);
        }

        if (this.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsSavingAccountException(this.getBalance(), amount);
        }

        balance = this.balance.subtract(amount);
    }

    @Override
    public MonthlyStatementResponse accept(AccountVisitor visitor) {
        return visitor.visit(this);
    }

    public BigDecimal getDepositCap() {
        return depositCap;
    }
}
