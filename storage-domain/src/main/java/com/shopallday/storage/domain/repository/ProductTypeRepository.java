package com.shopallday.storage.domain.repository;

import com.shopallday.storage.domain.exceptions.product.ReadProductTypeException;
import com.shopallday.storage.domain.models.ProductType;

import java.util.List;

public interface ProductTypeRepository {

    void createProductType(ProductType productType);

    void createProductTypes(List<ProductType> productTypes);

    ProductType findProductTypeById(Long id) throws ReadProductTypeException;

    List<ProductType> findAllProductTypes();

    void updateProductType(ProductType productType);

    void deleteProductType(ProductType productType);
}
