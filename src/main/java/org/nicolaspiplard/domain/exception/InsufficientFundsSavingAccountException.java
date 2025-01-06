package org.nicolaspiplard.domain.exception;

import java.math.BigDecimal;

public class InsufficientFundsSavingAccountException extends RuntimeException {

    public InsufficientFundsSavingAccountException(BigDecimal currentBalance, BigDecimal requestedAmount) {
        super("Insufficient funds on saving account: balance=" + currentBalance
                + ", requested=" + requestedAmount);
    }
}
