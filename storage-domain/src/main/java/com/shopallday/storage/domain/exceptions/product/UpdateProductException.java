package com.shopallday.storage.domain.exceptions.product;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class UpdateProductException  extends BusinessException {
    public UpdateProductException(String msg) {
        super(msg, BusinessErrorCodes.PRODUCT_COULD_NOT_BE_UPDATED);
    }
}
