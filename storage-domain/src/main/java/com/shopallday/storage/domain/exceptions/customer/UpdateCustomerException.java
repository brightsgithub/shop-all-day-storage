package com.shopallday.storage.domain.exceptions.customer;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class UpdateCustomerException extends BusinessException {
    public UpdateCustomerException(String msg) {
        super(msg, BusinessErrorCodes.CUSTOMER_COULD_NOT_BE_UPDATED);
    }
}
