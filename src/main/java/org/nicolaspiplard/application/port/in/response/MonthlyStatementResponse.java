package org.nicolaspiplard.application.port.in.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.nicolaspiplard.application.port.in.dto.OperationDto;

import java.math.BigDecimal;
import java.util.List;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "accountType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CurrentAccountStatementResponse.class, name = "Compte courant"),
        @JsonSubTypes.Type(value = SavingAccountStatementResponse.class, name = "Livret")
})
public sealed interface MonthlyStatementResponse permits CurrentAccountStatementResponse, SavingAccountStatementResponse {
    Long accountId();
    BigDecimal balance();
    List<OperationDto> operations();
}
