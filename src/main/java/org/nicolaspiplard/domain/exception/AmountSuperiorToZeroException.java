package org.nicolaspiplard.domain.exception;

import org.nicolaspiplard.domain.model.OperationType;

public class AmountSuperiorToZeroException extends RuntimeException {

    public AmountSuperiorToZeroException(OperationType operationType) {
        super(operationType.name().substring(0,1).toUpperCase() + operationType.name().substring(1).toLowerCase() + " amount must be greater than zero");
    }
}
