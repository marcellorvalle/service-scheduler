package com.marcellorvalle.scheduler.util.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {
    public static ResourceNotFoundException doThrow(String reason) {
        throw new ResourceNotFoundException(reason);
    }

    public ResourceNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
