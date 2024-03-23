package com.shopallday.storage.domain.exceptions.crud;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class CreateException extends BusinessException {
    public CreateException(String message, BusinessErrorCodes errorCode) {
        super(message, errorCode);
    }
}
