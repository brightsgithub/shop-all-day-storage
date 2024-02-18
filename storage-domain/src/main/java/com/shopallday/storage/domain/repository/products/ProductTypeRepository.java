package com.shopallday.storage.domain.repository.products;

import com.shopallday.storage.domain.exceptions.product.ReadProductTypeException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface ProductTypeRepository {

    void createProductType(ProductType productType);
    void createProductTypes(List<ProductType> productTypes, RepositoryManager repositoryManager);

    ProductType findProductTypeById(Long id) throws ReadProductTypeException;

    List<ProductType> findAllProductTypes();

    void updateProductType(ProductType productType);

    void deleteProductType(ProductType productType);
}
