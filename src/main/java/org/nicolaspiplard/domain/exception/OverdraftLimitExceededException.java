package org.nicolaspiplard.domain.exception;

import java.math.BigDecimal;

public class OverdraftLimitExceededException extends RuntimeException {
        public OverdraftLimitExceededException(BigDecimal overdraft) {
            super("Overdraft limit exceeded: overdraft=" + overdraft);
        }
}
