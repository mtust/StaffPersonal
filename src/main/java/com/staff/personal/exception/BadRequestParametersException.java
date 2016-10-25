package com.staff.personal.exception;

public class BadRequestParametersException extends GeneralServiceException {
    private int status = 400;
    private String message = "Bad request params";

    public BadRequestParametersException() {
    }

    public BadRequestParametersException(String message) {
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
