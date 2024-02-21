package com.shopallday.storage.app.controllers;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class CustomErrorResponse {
    private final BusinessErrorCodes errorCode;
    private final String message;

    public CustomErrorResponse(BusinessException exception) {
        this(exception.getErrorCode(), exception.getMessage());
    }
    public CustomErrorResponse(BusinessErrorCodes errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public BusinessErrorCodes getErrorCode() {
        return errorCode;
    }
}
