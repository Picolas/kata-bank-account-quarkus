package org.nicolaspiplard.domain.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(BigDecimal currentBalance, BigDecimal requestedAmount) {
        super("Insufficient funds: balance=" + currentBalance
                + ", requested=" + requestedAmount);
    }
}
