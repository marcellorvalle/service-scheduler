package com.marcellorvalle.scheduler.util.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public static BadRequestException doThrow(String reason) {
        throw new BadRequestException(reason);
    }

    public BadRequestException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
