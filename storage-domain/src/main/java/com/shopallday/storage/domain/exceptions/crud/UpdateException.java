package com.shopallday.storage.domain.exceptions.crud;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class UpdateException extends BusinessException {
    public UpdateException(String message, BusinessErrorCodes errorCode) {
        super(message, errorCode);
    }
}
