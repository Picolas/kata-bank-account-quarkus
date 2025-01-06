package org.nicolaspiplard.domain.exception;

import org.nicolaspiplard.domain.model.OperationType;

public class AmountSuperiorToZeroException extends RuntimeException {

    public AmountSuperiorToZeroException(OperationType operationType) {
        super(String.format("%s amount must be greater than zero", capitalize(operationType.name())));
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) +
                str.substring(1).toLowerCase();
    }
}
