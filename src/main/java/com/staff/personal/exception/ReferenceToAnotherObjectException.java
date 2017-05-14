package com.staff.personal.exception;


public class ReferenceToAnotherObjectException extends GeneralServiceException {

    private int status = 409;
    private String message = "Reference to another object";

    public ReferenceToAnotherObjectException() {
    }

    public ReferenceToAnotherObjectException(String message) {
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
