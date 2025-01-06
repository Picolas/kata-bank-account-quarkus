package org.nicolaspiplard.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DepositRequest {
    @NotNull
    @DecimalMin(value = "0.00", message = "Le dépôt doit être supérieur ou égal à 0")
    private BigDecimal amount;

    public DepositRequest() {}

    public DepositRequest(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
