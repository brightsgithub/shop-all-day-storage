package com.shopallday.storage.app.services.customer;

import com.shopallday.storage.app.models.CustomerShippingAddressDto;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;

import java.util.List;
import java.util.Map;

public interface CustomerShippingAddressService {
    CustomerShippingAddressDto create(CustomerShippingAddressDto dto) throws CreateException;
    List<CustomerShippingAddressDto> getGetAll();
    List<CustomerShippingAddressDto> getById(Long id) throws ReadException;
    CustomerShippingAddressDto update(CustomerShippingAddressDto dto) throws ReadException, UpdateException;
    void deleteById(Long id) throws DeleteException;
    CustomerShippingAddressDto partiallyUpdate(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException;
}
