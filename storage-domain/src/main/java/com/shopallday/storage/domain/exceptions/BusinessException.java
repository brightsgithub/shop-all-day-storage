package com.shopallday.storage.domain.exceptions;

public abstract class BusinessException extends Exception {
    private final String message;
    private final BusinessErrorCodes errorCode;

    public BusinessException(String message, BusinessErrorCodes errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return message;
    }

    public BusinessErrorCodes getErrorCode() {
        return errorCode;
    }
}
