package org.nicolaspiplard.domain.exception;

import java.math.BigDecimal;

public class DepositCapExceededException extends RuntimeException {

    public DepositCapExceededException(BigDecimal currentBalance, BigDecimal requestedAmount, BigDecimal depositCap) {
        super("Deposit cap exceeded: balance=" + currentBalance
                + ", requested=" + requestedAmount + ", depositCap=" + depositCap);
    }
}
