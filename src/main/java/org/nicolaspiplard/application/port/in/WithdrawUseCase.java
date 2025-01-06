package org.nicolaspiplard.application.port.in;

import org.nicolaspiplard.application.port.in.response.WithdrawResponse;

import java.math.BigDecimal;

public interface WithdrawUseCase {
    WithdrawResponse withdraw(Long accountId, BigDecimal amount);
}
