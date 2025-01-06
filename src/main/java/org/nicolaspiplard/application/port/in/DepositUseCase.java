package org.nicolaspiplard.application.port.in;

import org.nicolaspiplard.application.port.in.response.DepositResponse;

import java.math.BigDecimal;

public interface DepositUseCase {
    DepositResponse deposit(Long accountId, BigDecimal amount);
}
