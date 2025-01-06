package org.nicolaspiplard.application.port.in.dto;

import java.math.BigDecimal;

public record OperationDto(String operationType, BigDecimal amount, String date) {
}
