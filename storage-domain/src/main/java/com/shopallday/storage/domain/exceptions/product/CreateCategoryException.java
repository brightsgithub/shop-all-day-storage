package com.shopallday.storage.domain.exceptions.product;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class CreateCategoryException extends BusinessException {
    public CreateCategoryException(String message, BusinessErrorCodes errorCode) {
        super(message, errorCode);
    }
}
