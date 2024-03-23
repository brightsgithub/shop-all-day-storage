package com.shopallday.storage.domain.exceptions.crud;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class DeleteException extends BusinessException {
    public DeleteException(String message, BusinessErrorCodes errorCode) {
        super(message, errorCode);
    }
}
