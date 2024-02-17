package com.shopallday.storage.infra.repository;

import com.shopallday.storage.infra.entities.*;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Merge {

    static void mergeProductEntity(EntityManager entityManager, List<ProductEntity> productEntities) {
        for (ProductEntity productEntity : productEntities) {
            mergeProductEntity(entityManager, productEntity);
        }
    }

    static void mergeProductEntity(EntityManager entityManager, ProductEntity productEntity) {
        BrandEntity brandEntity = entityManager.merge(productEntity.getBrandEntity());

        mergeProductTypeEntity(entityManager, productEntity.getProductTypeEntity());
        ProductTypeEntity productTypeEntity = entityManager.merge(productEntity.getProductTypeEntity());

        // Set productEntity with merged brandEntity, productTypeEntity
        productEntity.setBrandEntity(brandEntity);
        productEntity.setProductTypeEntity(productTypeEntity);
    }

    static void mergeProductStockEntity(EntityManager entityManager, List<ProductStockEntity> productStockEntities) {
        for (ProductStockEntity productStockEntity : productStockEntities) {
            mergeProductStockEntity(entityManager, productStockEntity);
        }
    }

    static void mergeProductStockEntity(EntityManager entityManager, ProductStockEntity productStockEntity) {

        mergeProductEntity(entityManager, productStockEntity.getProductEntity());
        ProductEntity mergedProductEntity = entityManager.merge(productStockEntity.getProductEntity());

        // Set productStockEntity with merged productEntity
        productStockEntity.setProductEntity(mergedProductEntity );
    }

    static void mergeProductTypeEntity(EntityManager entityManager, List<ProductTypeEntity> productTypeEntityList) {
        for (ProductTypeEntity productTypeEntity : productTypeEntityList) {
            mergeProductTypeEntity(entityManager,productTypeEntity);
        }
    }

    static void mergeProductTypeEntity(
            EntityManager entityManager,
            ProductTypeEntity productTypeEntity
    ) {
        CategoryEntity categoryEntity = entityManager.merge(productTypeEntity.getCategoryEntity());
        // Set productTypeEntity with merged categoryEntity
        productTypeEntity.setCategoryEntity(categoryEntity);
    }

    static void mergeCustomerShipAddressEntity(
            EntityManager entityManager,
            List<CustomerShippingAddressEntity> shippingAddressEntities
    ){
        for (CustomerShippingAddressEntity shippingAddressEntity : shippingAddressEntities) {
            mergeCustomerShipAddressEntity(entityManager, shippingAddressEntity);
        }
    }

    static void mergeCustomerShipAddressEntity(
            EntityManager entityManager,
            CustomerShippingAddressEntity shippingAddressEntity
    ) {
        CustomerEntity customerEntity = shippingAddressEntity.getCustomerEntity();
        CustomerEntity mergedCustomerEntity = entityManager.merge(customerEntity);
        shippingAddressEntity.setCustomerEntity(mergedCustomerEntity);
    }

    static void mergeOrders(EntityManager entityManager, List<OrderEntity> orderEntities) {
        for (OrderEntity orderEntity : orderEntities) {
            mergeOrders(entityManager, orderEntity);
        }
    }

    static void mergeOrders(EntityManager entityManager, OrderEntity orderEntity) {
        CustomerEntity customerEntity = entityManager.merge(orderEntity.getCustomerEntity());
        OrderStatusTypeEntity orderStatusTypeEntity = entityManager.merge(orderEntity.getOrderStatusTypeEntity());

        orderEntity.setCustomerEntity(customerEntity);
        orderEntity.setOrderStatusTypeEntity(orderStatusTypeEntity);
    }

    static void mergeOrderLines(EntityManager entityManager, List<OrderLineEntity> orderLineEntities) {
        for (OrderLineEntity orderLineEntity : orderLineEntities) {
            mergeOrderLines(entityManager, orderLineEntity);
        }
    }

    static void mergeOrderLines(EntityManager entityManager, OrderLineEntity orderLineEntity) {
        mergeOrders(entityManager, orderLineEntity.getOrderEntity());
        mergeProductEntity(entityManager, orderLineEntity.getProductEntity());

        OrderEntity mergedOrderEntity = orderLineEntity.getOrderEntity();
        ProductEntity mergedProductEntity = orderLineEntity.getProductEntity();

        orderLineEntity.setOrderEntity(mergedOrderEntity);
        orderLineEntity.setProductEntity(mergedProductEntity);
    }
}
