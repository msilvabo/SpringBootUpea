package com.postgrado.ecommerce.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "%s with %s not found.";

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String entity, UUID id) {
        super(String.format(ERROR_MESSAGE , entity, id));
    }
}
