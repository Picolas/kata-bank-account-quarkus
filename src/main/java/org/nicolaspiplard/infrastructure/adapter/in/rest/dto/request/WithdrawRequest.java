package org.nicolaspiplard.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class WithdrawRequest {
    @NotNull
    @DecimalMin(value = "0.00", message = "Le retrait doit être supérieur ou égal à 0")
    private BigDecimal amount;

    public WithdrawRequest() {}

    public WithdrawRequest(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
