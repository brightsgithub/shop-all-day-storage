package com.shopallday.storage.domain.exceptions.product;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class DeleteCategoryException extends BusinessException {
    public DeleteCategoryException(String msg) {
        super(msg, BusinessErrorCodes.CATEGORY_COULD_NOT_BE_DELETED);
    }
}

