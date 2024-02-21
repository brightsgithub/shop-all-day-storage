package com.shopallday.storage.domain.exceptions.customer;

import com.shopallday.storage.domain.exceptions.BusinessException;

import static com.shopallday.storage.domain.exceptions.BusinessErrorCodes.CUSTOMER_NOT_FOUND;

public class ReadCustomerException extends BusinessException {
    public ReadCustomerException(String ids) {
        super("Customer with id/ids "+ids+" could not be found.", CUSTOMER_NOT_FOUND);
    }

}
