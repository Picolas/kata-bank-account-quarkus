package org.nicolaspiplard.infrastructure.adapter.in.rest.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.nicolaspiplard.domain.exception.DepositCapExceededException;
import org.nicolaspiplard.application.port.in.response.ErrorResponse;

@Provider
public class DepositCapExceededExceptionMapper implements ExceptionMapper<DepositCapExceededException> {

    @Override
    public Response toResponse(DepositCapExceededException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), ex.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}
