package com.shopallday.storage.domain.exceptions.product;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class DeleteProductException extends BusinessException {
    public DeleteProductException(String msg) {
        super(msg, BusinessErrorCodes.PRODUCT_COULD_NOT_BE_DELETED);
    }
}
