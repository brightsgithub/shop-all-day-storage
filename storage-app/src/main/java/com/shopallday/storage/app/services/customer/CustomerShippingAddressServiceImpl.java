package com.shopallday.storage.app.services.customer;

import com.shopallday.storage.app.mappers.Mapper;
import com.shopallday.storage.app.models.CustomerShippingAddressDto;
import com.shopallday.storage.app.services.BaseService;
import com.shopallday.storage.domain.exceptions.BusinessErrorCodes;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;
import com.shopallday.storage.domain.models.CustomerShippingAddress;
import com.shopallday.storage.domain.usecases.customer.shipping.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class CustomerShippingAddressServiceImpl extends BaseService implements CustomerShippingAddressService {

    private final CreateSingleCustomerShippingAddUseCase createSingleCustomerShippingAddUseCase;
    private final GetAllCustomerShippingAddressUseCase getAllCustomerShippingAddressUseCase;
    private final GetCustomerShipAddByIdUseCase getCustomerShipAddByIdUseCase;
    private final UpdateCustomerShippingAddressUseCase updateCustomerShippingAddressUseCase;
    private final DeleteCustomerShippingAddressUseCase deleteCustomerShippingAddressUseCase;
    @Qualifier("customerShipAddressMapper")
    private final Mapper<CustomerShippingAddress, CustomerShippingAddressDto> mapper;

    public CustomerShippingAddressServiceImpl(CreateSingleCustomerShippingAddUseCase createSingleCustomerShippingAddUseCase,
                                              GetAllCustomerShippingAddressUseCase getAllCustomerShippingAddressUseCase,
                                              GetCustomerShipAddByIdUseCase getCustomerShipAddByIdUseCase,
                                              UpdateCustomerShippingAddressUseCase updateCustomerShippingAddressUseCase,
                                              DeleteCustomerShippingAddressUseCase deleteCustomerShippingAddressUseCase,
                                              @Qualifier("customerShipAddressMapper") Mapper<CustomerShippingAddress, CustomerShippingAddressDto> mapper) {
        this.createSingleCustomerShippingAddUseCase = createSingleCustomerShippingAddUseCase;
        this.getAllCustomerShippingAddressUseCase = getAllCustomerShippingAddressUseCase;
        this.getCustomerShipAddByIdUseCase = getCustomerShipAddByIdUseCase;
        this.updateCustomerShippingAddressUseCase = updateCustomerShippingAddressUseCase;
        this.deleteCustomerShippingAddressUseCase = deleteCustomerShippingAddressUseCase;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CustomerShippingAddressDto create(CustomerShippingAddressDto dto) throws CreateException {
        final CustomerShippingAddress domain = mapper.mapFromDtoToDomain(dto);
        final CustomerShippingAddress justCreatedDomain = createSingleCustomerShippingAddUseCase.execute(domain);
        return mapper.mapFromDomainToDto(justCreatedDomain);
    }

    @Override
    @Transactional
    public List<CustomerShippingAddressDto> getGetAll() {
        return mapper.mapFromDomainToDto(getAllCustomerShippingAddressUseCase.execute());
    }

    @Override
    @Transactional
    public List<CustomerShippingAddressDto> getById(Long id) throws ReadException {
        final List<CustomerShippingAddress> domains = getCustomerShipAddByIdUseCase.execute(id);
        return mapper.mapFromDomainToDto(domains);
    }

    @Override
    @Transactional
    public CustomerShippingAddressDto update(CustomerShippingAddressDto dto) throws ReadException, UpdateException {
        final CustomerShippingAddress domain = mapper.mapFromDtoToDomain(dto);
        final CustomerShippingAddress updatedDomain = updateCustomerShippingAddressUseCase.execute(domain);
        return mapper.mapFromDomainToDto(updatedDomain);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws DeleteException {
        deleteCustomerShippingAddressUseCase.execute(id);
    }

    @Override
    @Transactional
    public CustomerShippingAddressDto partiallyUpdate(Long id, Map<String, Object> fields) throws ReadException, UpdateException {
        List<CustomerShippingAddress> existingDomains = getCustomerShipAddByIdUseCase.execute(id);
        final CustomerShippingAddress existingAddress;
        try {
            existingAddress = existingDomains.get(0);
        } catch (Exception e) {
            throw new ReadException("Shipping address with id "+ id + " does not exist", BusinessErrorCodes.CUSTOMER_SHIPPING_NOT_FOUND);
        }
        updateFieldsOnObject(fields, existingAddress, CustomerShippingAddress.class);
        return update(mapper.mapFromDomainToDto(existingAddress));
    }
}
