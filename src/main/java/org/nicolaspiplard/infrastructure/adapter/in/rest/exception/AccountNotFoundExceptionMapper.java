package org.nicolaspiplard.infrastructure.adapter.in.rest.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.nicolaspiplard.domain.exception.AccountNotFoundException;
import org.nicolaspiplard.application.port.in.response.ErrorResponse;

@Provider
public class AccountNotFoundExceptionMapper implements ExceptionMapper<AccountNotFoundException> {

    @Override
    public Response toResponse(AccountNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), ex.getMessage());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
