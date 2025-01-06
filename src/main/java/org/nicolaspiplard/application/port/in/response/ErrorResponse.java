package org.nicolaspiplard.application.port.in.response;

public record ErrorResponse(int status, String error) {
}
