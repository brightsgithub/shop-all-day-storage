package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.models.ProductTypeDto;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;

import java.util.List;
import java.util.Map;

public interface ProductTypeService {
    ProductTypeDto createProductType(ProductTypeDto productTypeDto) throws CreateException;
    List<ProductTypeDto> getProductTypes();
    ProductTypeDto getProductTypeById(Long id) throws ReadException;
    ProductTypeDto updateProductType(ProductTypeDto productTypeDto) throws ReadException, UpdateException;
    void deleteProductTypeById(Long id) throws DeleteException;
    ProductTypeDto partiallyUpdateProductType(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException;
}
