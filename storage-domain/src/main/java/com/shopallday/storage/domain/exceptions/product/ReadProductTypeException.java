package com.shopallday.storage.domain.exceptions.product;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class ReadProductTypeException extends BusinessException {
    public ReadProductTypeException(String msg) {
        super(msg, BusinessErrorCodes.PRODUCT_TYPE_NOT_FOUND);
    }
}