package com.shopallday.storage.domain.repository.products;

import com.shopallday.storage.domain.exceptions.crud.ReadException;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.RepositoryManager;

import java.util.List;

public interface ProductTypeRepository {

    ProductType createProductType(ProductType productType, RepositoryManager repositoryManager);
    List<ProductType> createProductTypes(List<ProductType> productTypes, RepositoryManager repositoryManager);

    ProductType findProductTypeById(Long id) throws ReadException;

    List<ProductType> findAllProductTypes();

    ProductType updateProductType(ProductType productType, RepositoryManager repositoryManager);

    void deleteProductTypeById(Long id);

    boolean isExists(Long id);
}
