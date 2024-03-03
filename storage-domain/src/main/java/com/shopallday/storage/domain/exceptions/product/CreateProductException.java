package com.shopallday.storage.domain.exceptions.product;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class CreateProductException extends BusinessException {
    public CreateProductException(String message, BusinessErrorCodes errorCode) {
        super(message, errorCode);
    }
}
