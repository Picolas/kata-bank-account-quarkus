package org.nicolaspiplard.infrastructure.adapter.in.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.nicolaspiplard.application.port.in.DepositUseCase;
import org.nicolaspiplard.application.port.in.GetMonthlyStatementUseCase;
import org.nicolaspiplard.application.port.in.WithdrawUseCase;
import org.nicolaspiplard.application.port.in.response.DepositResponse;
import org.nicolaspiplard.application.port.in.response.MonthlyStatementResponse;
import org.nicolaspiplard.application.port.in.response.WithdrawResponse;
import org.nicolaspiplard.infrastructure.adapter.in.rest.dto.request.DepositRequest;
import org.nicolaspiplard.infrastructure.adapter.in.rest.dto.request.WithdrawRequest;

@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    @Inject
    DepositUseCase depositUseCase;

    @Inject
    WithdrawUseCase withdrawUseCase;

    @Inject
    GetMonthlyStatementUseCase getMonthlyStatementUseCase;

    @POST
    @Path("/{accountId}/deposit")
    public DepositResponse deposit(@PathParam("accountId") Long accountId,
                            @Valid DepositRequest depositRequest) {
        return depositUseCase.deposit(accountId, depositRequest.getAmount());
    }

    @POST
    @Path("/{accountId}/withdraw")
    public WithdrawResponse withdraw(@PathParam("accountId") Long accountId, WithdrawRequest depositRequest) {
        return withdrawUseCase.withdraw(accountId, depositRequest.getAmount());
    }

    @GET
    @Path("/{accountId}/monthly-statement")
    public MonthlyStatementResponse getMonthlyStatement(@PathParam("accountId") Long accountId) {
        return getMonthlyStatementUseCase.getMonthlyStatement(accountId);
    }
}
