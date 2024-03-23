package com.shopallday.storage.domain.exceptions.product;

import com.shopallday.storage.domain.exceptions.BusinessException;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.CATEGORY_NOT_FOUND;

public class ReadCategoryException extends BusinessException {
    public ReadCategoryException(String ids) {
        super("Category with id/ids "+ids+" could not be found.", CATEGORY_NOT_FOUND);
    }
}

