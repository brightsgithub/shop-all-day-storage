package com.shopallday.storage.domain.exceptions.product;

import com.shopallday.storage.domain.exceptions.BusinessException;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.PRODUCT_NOT_FOUND;

public class ReadProductException extends BusinessException {
    public ReadProductException(String ids) {
        super("Product with id/ids "+ids+" could not be found.", PRODUCT_NOT_FOUND);
    }
}
