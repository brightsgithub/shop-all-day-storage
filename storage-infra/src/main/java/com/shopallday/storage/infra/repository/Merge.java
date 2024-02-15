package com.shopallday.storage.infra.repository;

import com.shopallday.storage.infra.entities.*;
import jakarta.persistence.EntityManager;

public class Merge {

    static void mergeProductEntity(EntityManager entityManager, ProductEntity productEntity) {
        BrandEntity brandEntity = entityManager.merge(productEntity.getBrandEntity());

        mergeProductTypeEntity(entityManager, productEntity.getProductTypeEntity());
        ProductTypeEntity productTypeEntity = entityManager.merge(productEntity.getProductTypeEntity());

        // Set productEntity with merged brandEntity, productTypeEntity
        productEntity.setBrandEntity(brandEntity);
        productEntity.setProductTypeEntity(productTypeEntity);
    }

    static void mergeProductStockEntity(EntityManager entityManager, ProductStockEntity productStockEntity) {

        mergeProductEntity(entityManager, productStockEntity.getProductEntity());
        ProductEntity mergedProductEntity = entityManager.merge(productStockEntity.getProductEntity());

        // Set productStockEntity with merged productEntity
        productStockEntity.setProductEntity(mergedProductEntity );
    }

    static void mergeProductTypeEntity(
            EntityManager entityManager,
            ProductTypeEntity productTypeEntity
    ) {
        CategoryEntity categoryEntity = entityManager.merge(productTypeEntity.getCategoryEntity());
        // Set productTypeEntity with merged categoryEntity
        productTypeEntity.setCategoryEntity(categoryEntity);
    }

    static void mergeCustomerEntity(
            EntityManager entityManager,
            CustomerShippingAddressEntity shippingAddressEntity
    ) {
        CustomerEntity customerEntity = shippingAddressEntity.getCustomerEntity();
        CustomerEntity mergedCustomerEntity = entityManager.merge(customerEntity);
        shippingAddressEntity.setCustomerEntity(mergedCustomerEntity);
    }
}
