package com.shopallday.storage.app.services.products;

import com.shopallday.storage.app.models.ProductStockDto;
import com.shopallday.storage.domain.exceptions.crud.CreateException;
import com.shopallday.storage.domain.exceptions.crud.DeleteException;
import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.exceptions.crud.UpdateException;

import java.util.List;
import java.util.Map;

public interface ProductStockService {
    ProductStockDto createProductStock(ProductStockDto productStockDto) throws CreateException;
    List<ProductStockDto> getProductStocks();
    ProductStockDto getProductStockById(Long id) throws ReadException;
    ProductStockDto updateProductStock(ProductStockDto productStockDto) throws ReadException, UpdateException;
    void deleteProductStockById(Long id) throws DeleteException;
    List<ProductStockDto> getProductStocksByCategoryId(Long id) throws ReadException;
    ProductStockDto partiallyUpdateProductStock(Long id, Map<String, Object> fields)
            throws ReadException, UpdateException;
}
