package com.unibuc.demo.exceptions;

public class DuplicateUserPermissionException extends RuntimeException {

    public DuplicateUserPermissionException(String message) {
        super(message);
    }
}
