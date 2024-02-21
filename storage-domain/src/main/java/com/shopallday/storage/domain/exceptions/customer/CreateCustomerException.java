package com.shopallday.storage.domain.exceptions.customer;

import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.BusinessException;

public class CreateCustomerException extends BusinessException {
    public CreateCustomerException(String msg, BusinessErrorCodes businessErrorCodes) {
        super(msg, businessErrorCodes);
    }
}
