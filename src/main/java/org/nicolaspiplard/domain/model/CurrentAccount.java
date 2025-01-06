package org.nicolaspiplard.domain.model;

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
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
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
    public String getAccountType() {
        return "Compte Courant";
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }
}
