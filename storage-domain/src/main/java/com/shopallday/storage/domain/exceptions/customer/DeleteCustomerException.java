package com.shopallday.storage.domain.exceptions.customer;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class DeleteCustomerException extends BusinessException {
    public DeleteCustomerException(String msg) {
        super(msg, BusinessErrorCodes.CUSTOMER_COULD_NOT_BE_DELETED);
    }
}
